package web.controllers;

import io.javalin.http.Context;

import java.util.Map;

public abstract class Controller {
  public static boolean yaEstaLogeado(Context ctx){

    return ctx.sessionAttribute("current-user") != null;
  }

  public static void modificarModelSiEstaLogueado(Context ctx, Map<String, Object> model){
    model.put("logeado", yaEstaLogeado(ctx));
    model.put("username", getCurrentUserName(ctx));
  }

  public static String getCurrentUserName(Context ctx){
    return ctx.sessionAttribute("current-user");
  }

}