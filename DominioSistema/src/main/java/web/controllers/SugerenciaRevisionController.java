package web.controllers;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;
import web.exceptions.AccessDeniedException;
import web.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SugerenciaRevisionController extends Controller{
    private Repositorio<Incidente> repositorioDeIncidentes;
    public SugerenciaRevisionController (Repositorio<Incidente> repositorioDeIncidentes){
        this.repositorioDeIncidentes = repositorioDeIncidentes;
    }

    public void mostrarFormSugerenciaRevision(Context context) {
        Map<String, Object> model = new HashMap<>();

        Incidente incidente = this.repositorioDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("id")));

        if (incidente == null || !incidente.abierto()) {
            throw new NotFoundException();
        }
        else if(!incidente.getComunidad().esMiembro(super.getUsuario(context))){
            throw new AccessDeniedException();
        }

        modificarModelSiEstaLogueado(context, model);



        model.put("incidente", incidente);

        context.render("sugerencia-revision.hbs", model);
    }
}
