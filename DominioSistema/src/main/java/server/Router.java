package server;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
  public static void init() {

    Server.app().routes(() -> {

      get("", ctx -> {
        Map<String, Object> model = new HashMap<>();
        model.put("logeado", false);
        model.put("username", "salacas");
        ctx.render("index.hbs", model);
      });

      get("login", ctx -> {
        Map<String, Object> model = new HashMap<>();
        model.put("logeado", false);
        model.put("username", "salacas");

        if (yaEstaLogeado(ctx)) ctx.redirect("");
        else ctx.render("login/login.hbs", model);

      });

    });
  };
  private static boolean yaEstaLogeado(Context ctx){
    return false;
  }
}
