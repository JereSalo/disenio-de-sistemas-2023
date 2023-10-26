package web.handlers;

import io.javalin.Javalin;
import web.exceptions.AccessDeniedException;

import java.util.HashMap;
import java.util.Map;

import static web.controllers.base.Controller.modificarModelSiEstaLogueado;

public class AccessDeniedHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(AccessDeniedException.class, (e, context) -> {
      Map<String, Object> model = new HashMap<>();

      modificarModelSiEstaLogueado(context, model);

      model.put("mensaje", "ERROR 401: acceso denegado");

      context.render("mensaje.hbs", model);
    });
  }
}
