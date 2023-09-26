package controllers;

import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import persistence.repositories.FactoryRepositorios;

public class FactoryController {
  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(FactoryRepositorios.get(Incidente.class)); break;
    }
    return controller;
  }
}
