package web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesController extends Controller{
  private Repositorio<Incidente> repositorioDeIncidentes;
  public IncidentesController (Repositorio<Incidente> repositorioDeIncidentes){
    this.repositorioDeIncidentes = repositorioDeIncidentes;
  }

  public void listarIncidentes(Context context) {
    Map<String, Object> model = new HashMap<>();

    modificarModelSiEstaLogueado(context, model);

    List<Incidente> listaDeIncidentes = this.repositorioDeIncidentes.obtenerTodos();

    listaDeIncidentes.removeIf(incidente -> !incidente.getComunidad().esMiembro(super.getUsuario(context)));

    this.filtrarIncidentes(listaDeIncidentes, context);

    model.put("incidentes", listaDeIncidentes);

    if (context.queryParamMap().containsKey("estado") && context.queryParam("estado").equals("abierto")){
      model.put("abierto", true);
    }
    else if(context.queryParamMap().containsKey("estado") && context.queryParam("estado").equals("cerrado")){
      model.put("cerrado", true);
    }
    else{
      model.put("ninguno", true);
    }

    context.render("incidentes/lista-incidentes.hbs", model);
  }

  private void filtrarIncidentes(List<Incidente> listaDeIncidentes, Context context){

    if (context.queryParamMap().containsKey("estado") && context.queryParam("estado").equals("abierto")){
      listaDeIncidentes.removeIf(incidente -> !incidente.abierto());
    }
    else if (context.queryParamMap().containsKey("estado") && context.queryParam("estado").equals("cerrado")) {
      listaDeIncidentes.removeIf(incidente -> incidente.abierto());
    }
  }

  public void cerrarIncidente(Context context){

    Map<String, Object> model = new HashMap<>();

    Incidente incidente = this.repositorioDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("id")));

    if (incidente.abierto()){

        incidente.cerrar(obtenerMiembro(super.getCurrentUserName(context), incidente.getComunidad()));

        this.repositorioDeIncidentes.modificar(incidente);

        modificarModelSiEstaLogueado(context, model);

        model.put("mensaje", "El incidente ha sido cerrado con éxito");

        context.render("mensaje.hbs", model);
    }
    else{
      modificarModelSiEstaLogueado(context, model);

      model.put("mensaje", "El incidente ya está cerrado");

      context.render("mensaje.hbs", model);
    }
  }

  public void mostrarFormAbrirIncidente(Context context) {

    Map<String, Object> model = new HashMap<>();

    super.modificarModelSiEstaLogueado(context, model);

    Repositorio<Comunidad> repoDeComunidades = FactoryRepositorios.get(Comunidad.class);
    Repositorio<Entidad> repoDeEntidades = FactoryRepositorios.get(Entidad.class);

    model.put("comunidades", repoDeComunidades.obtenerTodos().stream().filter(comunidad -> comunidad.esMiembro(super.getUsuario(context))).toList());
    model.put("entidades", repoDeEntidades.obtenerTodos());

    context.render("incidentes/abrir-incidente.hbs", model);
  }

  public void abrirIncidente(Context context){

    Incidente nuevoIncidente = new Incidente();

    Repositorio<Comunidad> repoDeComunidades = FactoryRepositorios.get(Comunidad.class);
    Repositorio<Entidad> repoDeEntidades = FactoryRepositorios.get(Entidad.class);
    Repositorio<Establecimiento> repoDeEstablecimientos = FactoryRepositorios.get(Establecimiento.class);
    Repositorio<PrestacionServicio> repoDePrestacionServicio = FactoryRepositorios.get(PrestacionServicio.class);

    nuevoIncidente.setCreador(this.obtenerMiembro(super.getCurrentUserName(context), repoDeComunidades.buscarPorId(Long.parseLong(context.formParam("comunidad")))));

    nuevoIncidente.setComunidad(repoDeComunidades.buscarPorId(Long.parseLong(context.formParam("comunidad"))));

    nuevoIncidente.setEntidad(repoDeEntidades.buscarPorId(Long.parseLong(context.formParam("entidad"))));

    nuevoIncidente.setEstablecimiento(repoDeEstablecimientos.buscarPorId(Long.parseLong(context.formParam("establecimiento"))));

    nuevoIncidente.setPrestacionDeServicio(repoDePrestacionServicio.buscarPorId(Long.parseLong(context.formParam("servicio"))));

    nuevoIncidente.setObservaciones(context.formParam("observaciones"));

    this.repositorioDeIncidentes.agregar(nuevoIncidente);

    Map<String, Object> model = new HashMap<>();

    modificarModelSiEstaLogueado(context, model);

    model.put("mensaje", "El incidente ha sido abierto con éxito");

    context.render("mensaje.hbs", model);
  }

  public void getEstablecimientosDeEntidad(Context context){

    Repositorio<Entidad> repoDeEntidades = FactoryRepositorios.get(Entidad.class);

    String jsonResponse = this.armarJSONEstablecimientos(repoDeEntidades.buscarPorId(Long.parseLong(context.pathParam("id-entidad"))).getEstablecimientos());

    context.contentType("application/json");

    context.result(jsonResponse);
  }

  private String armarJSONEstablecimientos(List<Establecimiento> listaDeEstablecimientos){
    List<Map<String, Object>> jsonEstablecimientos = new ArrayList<>();

    listaDeEstablecimientos.forEach(establecimiento -> {
      Map<String, Object> jsonMap = new HashMap<>();

      jsonMap.put("id", establecimiento.getId());
      jsonMap.put("nombre", establecimiento.getNombre());

      jsonEstablecimientos.add(jsonMap);
    });

    try {

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      return objectMapper.writeValueAsString(jsonEstablecimientos);

    } catch (Exception e) {
      e.printStackTrace();
      return "[]";
    }
  }

  private String armarJSONServicios(List<PrestacionServicio> listaDePrestacionServicio){

    //TODO: RESOLVER REPETICION DE LOGICA CON armarJsonEstablecimientos
    List<Map<String, Object>> jsonServicio = new ArrayList<>();

    listaDePrestacionServicio.forEach(prestacionServicio -> {
      Map<String, Object> jsonMap = new HashMap<>();

      jsonMap.put("id", prestacionServicio.getId());
      jsonMap.put("nombre", prestacionServicio.getServicio().getDescripcion());

      jsonServicio.add(jsonMap);
    });

    try {

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      return objectMapper.writeValueAsString(jsonServicio);

    } catch (Exception e) {
      e.printStackTrace();
      return "[]";
    }
  }

  public void getServiciosDeEstablecimiento(Context context){

    Repositorio<Establecimiento> repoDeEstablecimientos = FactoryRepositorios.get(Establecimiento.class);

    String jsonResponse = this.armarJSONServicios(repoDeEstablecimientos.buscarPorId(Long.parseLong(context.pathParam("id-establecimiento"))).getPrestaciones());

    context.contentType("application/json");

    context.result(jsonResponse);

  }

  private Miembro obtenerMiembro(String username, Comunidad comunidad){
    Repositorio<Miembro> repoDeMiembros = FactoryRepositorios.get(Miembro.class);

    return repoDeMiembros.obtenerTodos().stream().filter(miembro -> miembro.getUsername().equals(username) && miembro.getComunidad() == comunidad).findFirst().get();
  }
}
