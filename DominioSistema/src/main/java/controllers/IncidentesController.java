package controllers;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;
import server.utils.ICrudViewsHandler;

public class IncidentesController implements ICrudViewsHandler {
  private Repositorio<Incidente> repositorioDeIncidentes;
  public IncidentesController (Repositorio<Incidente> repositorioDeIncidentes){
    this.repositorioDeIncidentes = repositorioDeIncidentes;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

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
}
