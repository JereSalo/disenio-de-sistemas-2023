package web.controllers;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;

import java.util.HashMap;
import java.util.Map;

public class SugerenciaRevisionController extends Controller{
    private Repositorio<Incidente> repositorioDeIncidentes;
    public SugerenciaRevisionController (Repositorio<Incidente> repositorioDeIncidentes){
        this.repositorioDeIncidentes = repositorioDeIncidentes;
    }

    public void mostrarFormSugerenciaRevision(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Incidente incidente = this.repositorioDeIncidentes.buscarPorId(Integer.parseInt(context.pathParam("id")));

        model.put("incidente", incidente);

        context.render("sugerencia-revision.hbs", model);
    }
}
