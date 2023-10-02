package web.controllers;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;
import web.server.utils.ICrudViewsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesController {
  private Repositorio<Incidente> repositorioDeIncidentes;
  public IncidentesController (Repositorio<Incidente> repositorioDeIncidentes){
    this.repositorioDeIncidentes = repositorioDeIncidentes;
  }

  public void listarIncidentes(Context context) {
    Map<String, Object> model = new HashMap<>();

    LoginController.modificarModelSiEstaLogueado(context, model);

    armarModelListaIncidentes(context, model);

    context.render("incidentes/lista-incidentes.hbs", model);
  }

  private void armarModelListaIncidentes(Context context, Map<String, Object> model){
    Repositorio<Comunidad> repoDeComunidades = FactoryRepositorios.get(Comunidad.class);

    List<Comunidad> comunidadesDelUsuario = repoDeComunidades.obtenerTodos().stream().filter(comunidad -> usuarioPerteneceAComunidad(comunidad, context.sessionAttribute("current-user"))).toList();

    List<Incidente> listaDeIncidentes = new ArrayList<Incidente>();

    comunidadesDelUsuario.forEach(comunidad -> listaDeIncidentes.addAll(comunidad.getIncidentes()));

    model.put("incidentes", listaDeIncidentes);
  }

  public void listarIncidentesParaCerrar(Context context){
    Map<String, Object> model = new HashMap<>();

    LoginController.modificarModelSiEstaLogueado(context, model);

    armarModelListaIncidentes(context, model);

    model.put("cerrar", true);

    context.render("incidentes/lista-incidentes.hbs", model);
  }

  public void cerrarIncidente(Context context){
    Map<String, Object> model = new HashMap<>();

    LoginController.modificarModelSiEstaLogueado(context, model);

    Incidente incidente = this.repositorioDeIncidentes.buscarPorId(Integer.parseInt(context.pathParam("id")));

    incidente.cerrar(obtenerMiembro(LoginController.getCurrentUserName(context)));

    context.redirect("home");
  }

  public void abrirIncidente(Context context) {

    //TODO: IR FILTRANDO CON JS A MEDIDA QUE SE VA SELECCIONANDO

    Map<String, Object> model = new HashMap<>();

    LoginController.modificarModelSiEstaLogueado(context, model);

    Repositorio<Comunidad> repoDeComunidades = FactoryRepositorios.get(Comunidad.class);
    Repositorio<Entidad> repoDeEntidades = FactoryRepositorios.get(Entidad.class);
    Repositorio<Establecimiento> repoDeEstablecimientos = FactoryRepositorios.get(Establecimiento.class);
    Repositorio<PrestacionServicio> repoDePrestacionServicio = FactoryRepositorios.get(PrestacionServicio.class);

    model.put("comunidades", repoDeComunidades.obtenerTodos().stream().filter(comunidad -> usuarioPerteneceAComunidad(comunidad, context.sessionAttribute("current-user"))).toList());

    context.render("incidentes/abrir-incidente.hbs", model);
  }

  private boolean usuarioPerteneceAComunidad(Comunidad comunidad, String username){
    return comunidad.getMiembros().stream().anyMatch(miembro -> miembro.getUsername() == username);
  }

  private Miembro obtenerMiembro(String username){
    Repositorio<Miembro> repoDeMiembros = FactoryRepositorios.get(Miembro.class);

    return repoDeMiembros.obtenerTodos().stream().filter(miembro -> miembro.getUsername() == username).findFirst().get();
  }
}
