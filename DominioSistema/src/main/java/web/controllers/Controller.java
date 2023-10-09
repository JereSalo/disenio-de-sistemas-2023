package web.controllers;

import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;

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

  public static Usuario getUsuario(Context ctx){
    return FactoryRepositorios.get(Usuario.class).obtenerTodos().stream().filter(u -> u.getUsername().equals(getCurrentUserName(ctx))).findFirst().get();

  }

}
