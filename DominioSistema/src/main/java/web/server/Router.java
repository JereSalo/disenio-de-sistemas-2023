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

      get("home", ctx -> {
        Map<String, Object> model = new HashMap<>();

        LoginController.modificarModelSiEstaLogueado(ctx, model);

        ctx.render("index.hbs", model);
      });

      get("login", ctx -> {
        Map<String, Object> model = new HashMap<>();

        LoginController.modificarModelSiEstaLogueado(ctx, model);

        if (LoginController.yaEstaLogeado(ctx)) ctx.redirect("home");
        else ctx.render("login.hbs", model);

      });

      post("login", ctx -> {
        if (LoginController.sonCredencialesValidas(ctx)){
          ctx.sessionAttribute("current-user", ctx.formParam("usuario"));
          ctx.redirect("home");
        }
        else{
          ctx.result("Las credenciales ingresadas son incorrectas");
        }
      });

      get("logout", ctx -> {
        ctx.req().getSession().invalidate();
        ctx.redirect("home");
      });

      ApiBuilder.get("abrir-incidente", ((IncidentesController) FactoryController.controller("Incidentes"))::create);

      get("lista-incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
    });
  };

}
