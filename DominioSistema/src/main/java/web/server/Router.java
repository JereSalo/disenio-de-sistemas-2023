package web.server;

import domain.usuarios.TipoRol;
import web.controllers.*;
import io.javalin.apibuilder.ApiBuilder;
import web.middlewares.AuthMiddleware;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
  public static void init() {

    Server.app().routes(() -> {

      get("", ctx -> ctx.redirect("home"));

      get("home",  ((LoginController) FactoryController.controller("Login"))::home);

      get("login", ((LoginController) FactoryController.controller("Login"))::mostrarPantallaLogin);

      post("login", ((LoginController) FactoryController.controller("Login"))::login);

      get("logout", ((LoginController) FactoryController.controller("Login"))::logout);

      get("registrarse", ((RegistroController) FactoryController.controller("Registro"))::mostrarPantallaRegistrarse);

      post("registrarse", ((RegistroController) FactoryController.controller("Registro"))::registrarUsuario);


      get("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::mostrarFormAbrirIncidente, TipoRol.MIEMBRO);

      post("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::abrirIncidente, TipoRol.MIEMBRO);

      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::listarIncidentes);

      post("incidentes/cerrar/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::cerrarIncidente, TipoRol.MIEMBRO);

      get("establecimientos/{id-entidad}", ((IncidentesController) FactoryController.controller("Incidentes"))::getEstablecimientosDeEntidad, TipoRol.MIEMBRO);

      get("servicios/{id-establecimiento}", ((IncidentesController) FactoryController.controller("Incidentes"))::getServiciosDeEstablecimiento, TipoRol.MIEMBRO);

      get("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::mostrarFormCargaMasiva, TipoRol.ADMINISTRADOR_PLATAFORMA);

      post("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::cargar, TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("revision-incidente/{id}", ((SugerenciaRevisionController) FactoryController.controller("SugerenciaRevision"))::mostrarFormSugerenciaRevision, TipoRol.MIEMBRO);

    });
  };

}
