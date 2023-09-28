package server;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
  public static void init() {

    Server.app().routes(() -> {

      get("", ctx -> ctx.redirect("home"));

      get("home", ctx -> {
        Map<String, Object> model = new HashMap<>();

        model.put("logeado", yaEstaLogeado(ctx));
        model.put("username", ctx.sessionAttribute("current-user"));

        ctx.render("index.hbs", model);
      });

      get("login", ctx -> {
        Map<String, Object> model = new HashMap<>();

        model.put("logeado", yaEstaLogeado(ctx));
        model.put("username", ctx.sessionAttribute("current-user"));

        if (yaEstaLogeado(ctx)) ctx.redirect("home");
        else ctx.render("login.hbs", model);

      });

      post("login", ctx -> {
        if (sonCredencialesValidas(ctx)){
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

    });
  };
  private static boolean yaEstaLogeado(Context ctx){

    return ctx.sessionAttribute("current-user") != null;
  }

  private static boolean sonCredencialesValidas(Context ctx){
    return true;
  }
}
