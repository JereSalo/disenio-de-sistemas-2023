package web.controllers;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.usuarios.Usuario;
import persistence.repositories.FactoryRepositorios;

public class FactoryController {
  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(FactoryRepositorios.get(Incidente.class)); break;
      case "Login": controller = new LoginController(FactoryRepositorios.get(Usuario.class)); break;
      case "Registro": controller = new RegistroController(FactoryRepositorios.get(Usuario.class)); break;
      case "CargaMasiva": controller = new CargaMasivaController(); break;
      case "SugerenciaRevision": controller = new SugerenciaRevisionController(FactoryRepositorios.get(Incidente.class)); break;
    }
    return controller;
  }
}
