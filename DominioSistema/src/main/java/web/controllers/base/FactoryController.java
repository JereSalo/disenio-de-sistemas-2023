package web.controllers.base;

import domain.comunidades.Comunidad;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.rankings.rankings.Ranking;
import domain.usuarios.OrganismoDeControl;
import domain.usuarios.PrestadoraDeServicio;
import domain.usuarios.Usuario;
import persistence.repositories.FactoryRepositorios;
import web.controllers.*;

public class FactoryController {
  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(FactoryRepositorios.get(Incidente.class)); break;
      case "Login": controller = new LoginController(FactoryRepositorios.get(Usuario.class)); break;
      case "Registro": controller = new RegistroController(FactoryRepositorios.get(Usuario.class)); break;
      case "CargaMasiva": controller = new CargaMasivaController(FactoryRepositorios.get(PrestadoraDeServicio.class), FactoryRepositorios.get(OrganismoDeControl.class)); break;
      case "SugerenciaRevision": controller = new SugerenciaRevisionController(FactoryRepositorios.get(Incidente.class)); break;
      case "AdministracionUsuarios": controller = new AdministracionUsuariosController(FactoryRepositorios.get(Usuario.class)); break;
      case "Ranking" : controller = new RankingsController(FactoryRepositorios.get(Ranking.class)); break;
      case "OrganismoDeControl": controller = new OrganismosDeControlController(FactoryRepositorios.get(OrganismoDeControl.class)); break;
      case "PrestadoraDeServicio": controller = new PrestadorasDeServiciosController(FactoryRepositorios.get(PrestadoraDeServicio.class)); break;
      case "Comunidad": controller = new ComunidadesController(FactoryRepositorios.get(Comunidad.class)); break;
      case "AdministracionComunidades": controller = new AdministracionComunidadesController(FactoryRepositorios.get(Comunidad.class)); break;
    }
    return controller;
  }
}
