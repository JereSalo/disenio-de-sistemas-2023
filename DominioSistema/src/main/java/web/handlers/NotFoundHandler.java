package web.handlers;

import io.javalin.Javalin;
import web.exceptions.AccessDeniedException;
import web.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

import static web.controllers.Controller.modificarModelSiEstaLogueado;

public class NotFoundHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(NotFoundException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();

            modificarModelSiEstaLogueado(context, model);

            model.put("mensaje", "ERROR 404: recurso no encontrado");

            context.render("mensaje.hbs", model);
        });
    }
}
