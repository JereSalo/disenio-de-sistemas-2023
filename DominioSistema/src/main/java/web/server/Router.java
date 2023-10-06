package web.server;

import web.controllers.*;
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

      get("registrarse", ((RegistroController) FactoryController.controller("Registro"))::mostrarPantallaRegistrarse);

      post("registrarse", ((RegistroController) FactoryController.controller("Registro"))::registrarUsuario);


      get("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::mostrarFormAbrirIncidente);

      post("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::abrirIncidente);

      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::listarIncidentes);

      post("incidente/cerrar/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::cerrarIncidente);

      get("incidentes/abierto-exito", ((IncidentesController) FactoryController.controller("Incidentes"))::mostrarMensajeDeIncidenteAbierto);


      get("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::mostrarFormCargaMasiva);

      post("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::cargar);

      get("revision-incidente/{id}", ((SugerenciaRevisionController) FactoryController.controller("SugerenciaRevision"))::mostrarFormSugerenciaRevision);

    });
  };

}
