package web.controllers.base;

import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;

import java.util.Map;

public abstract class Controller {
  public static boolean yaEstaLogeado(Context ctx){

    return ctx.sessionAttribute("current-user") != null;
  }

  public static void modificarModelSiEstaLogueado(Context ctx, Map<String, Object> model){
    // Este método solo hace falta usarlo en páginas que se puedan ver tanto si estás logueado como si no
    if (yaEstaLogeado(ctx)) {
      modificarModelLogueado(ctx, model);
    }
  }

  public static void modificarModelLogueado(Context ctx, Map<String, Object> model){
    model.put("logeado", true);
    model.put("username", getCurrentUserName(ctx));
    model.put(getUsuario(ctx).getRol().getTipo().name(), true);
  }

  public static String getCurrentUserName(Context ctx){
    return ctx.sessionAttribute("current-user");
  }

  public static Usuario getUsuario(Context ctx){
    return FactoryRepositorios.get(Usuario.class).obtenerTodos().stream().filter(u -> u.getUsername().equals(getCurrentUserName(ctx))).findFirst().get();

  }

}
