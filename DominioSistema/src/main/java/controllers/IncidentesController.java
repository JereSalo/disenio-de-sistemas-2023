package controllers;

import domain.comunidades.Comunidad;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;
import server.utils.ICrudViewsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesController implements ICrudViewsHandler {
  private Repositorio<Incidente> repositorioDeIncidentes;
  public IncidentesController (Repositorio<Incidente> repositorioDeIncidentes){
    this.repositorioDeIncidentes = repositorioDeIncidentes;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    LoginController.modificarModelSiEstaLogueado(context, model);

    Repositorio<Comunidad> repoDeComunidades = FactoryRepositorios.get(Comunidad.class);

    List<Comunidad> comunidadesDelUsuario = repoDeComunidades.obtenerTodos().stream().filter(comunidad -> usuarioPerteneceAComunidad(comunidad, context.sessionAttribute("current-user"))).toList();

    List<Incidente> listaDeIncidentes = new ArrayList<Incidente>();

    comunidadesDelUsuario.forEach(comunidad -> listaDeIncidentes.addAll(comunidad.getIncidentes()));

    model.put("incidentes", listaDeIncidentes);

    context.render("incidentes/lista-incidentes.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

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

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }

  private boolean usuarioPerteneceAComunidad(Comunidad comunidad, String username){
    return comunidad.getMiembros().stream().anyMatch(miembro -> miembro.getPersona().getUsuario().getUsername() == username);
  }
}
