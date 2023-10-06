package web.controllers;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class SugerenciaRevisionController extends Controller{
    public void mostrarFormSugerenciaRevision(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        context.render("sugerencia-revision.hbs", model);
    }
}
