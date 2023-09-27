package server;

import controllers.FactoryController;
import controllers.LoginController;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
  public static void init() {

    Server.app().routes(() -> {

      get("", ctx -> ctx.redirect("index.html"));
      post("iniciar-sesion", ((LoginController) FactoryController.controller("Login"))::logearse);
    });
  };
}
