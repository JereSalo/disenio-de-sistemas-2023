package web.server;

import web.controllers.FactoryController;
import web.controllers.IncidentesController;
import web.controllers.LoginController;
import io.javalin.apibuilder.ApiBuilder;

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

      get("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::mostrarFormAbrirIncidente);

      post("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::abrirIncidente);

      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::listarIncidentes);

      post("incidente/cerrar/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::cerrarIncidente);

    });
  };

}
