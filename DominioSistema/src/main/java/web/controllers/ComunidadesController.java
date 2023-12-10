package web.controllers;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;
import web.controllers.base.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController extends Controller {

    Repositorio<Comunidad> repoComunidades;

    public ComunidadesController(Repositorio<Comunidad> repoComunidades){
        this.repoComunidades = repoComunidades;
    }
    
    public void mostrarComunidades(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Usuario usuarioActual = this.getUsuario(context);

        List<Comunidad> comunidadesALasQuePertenece =
                this.repoComunidades.obtenerTodos().stream().
                        filter(c -> c.esMiembro(usuarioActual) && c.estaActivo(usuarioActual) && c.isActiva()).toList();
        List<Comunidad> comunidadesALasQueNoPertenece =
                this.repoComunidades.obtenerTodos().stream().
                        filter(c -> (!c.esMiembro(usuarioActual) || !c.estaActivo(usuarioActual)) && c.isActiva()).toList();

        model.put("comunidadesQuePertenece", comunidadesALasQuePertenece);
        model.put("comunidadesQueNoPertenece", comunidadesALasQueNoPertenece);
        context.render("comunidades/listaComunidades.hbs", model);
    }

    public void mostrarFormUnirseAComunidad(Context context){
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Long idComunidad = Long.valueOf(context.pathParam("id-comunidad"));
        Comunidad comunidadAfectada = this.repoComunidades.buscarPorId(idComunidad);

        model.put("id-comunidad", idComunidad);
        model.put("nombre-comunidad", comunidadAfectada.getNombre());

        context.render("comunidades/unirseComunidad.hbs", model);
    }

    public void unirseAComunidad(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Long idComunidad = Long.valueOf(context.pathParam("id-comunidad"));
        Comunidad comunidadAfectada = this.repoComunidades.buscarPorId(idComunidad);

        boolean afectado = context.formParam("rol").equals("Afectado");

        Usuario user = this.getUsuario(context);

        List<Persona> listaPersonas = FactoryRepositorios.get(Persona.class).obtenerTodos();
        Persona persona = listaPersonas.stream().filter(p -> p.getUsername().equals(user.getUsername())).findFirst().get();

        Miembro nuevoMiembro = new Miembro(persona, comunidadAfectada, afectado);

        FactoryRepositorios.get(Miembro.class).agregar(nuevoMiembro);

        model.put("mensaje", "Se unio con exito a la comunidad");
        context.render("mensaje.hbs", model);
    }

    public void mostrarComunidad(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Comunidad comunidad = this.repoComunidades.buscarPorId(Long.parseLong(context.pathParam("id")));

        if (context.queryParamMap().containsKey("ver") && context.queryParam("ver").equals("incidentes")){
            model.put("mostrarIncidentes", true);
            List<Incidente> incidentes = new ArrayList<>(comunidad.getIncidentes().stream().toList());
            this.filtrarIncidentes(incidentes, context);
            model.put("incidentes", incidentes);
        }
        else if(context.queryParamMap().containsKey("ver") && context.queryParam("ver").equals("miembros")){
            model.put("mostrarMiembros", true);
            List<Miembro> miembros = new ArrayList<>(comunidad.getMiembros().stream().filter(Miembro::isActivo).toList());
            model.put("miembros", miembros);
        }

        model.put("comunidad", comunidad);
        context.render("comunidades/mostrarComunidad.hbs", model);
    }

    public void filtrarIncidentes(List<Incidente> incidentes, Context context) {
        if (context.queryParamMap().containsKey("estado") && context.queryParam("estado").equals("abierto")) {
            incidentes.removeIf(incidente -> !incidente.abierto());
        } else if (context.queryParamMap().containsKey("estado") && context.queryParam("estado").equals("cerrado")) {
            incidentes.removeIf(incidente -> incidente.abierto());
        }
    }

    public void salirDeComunidad(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Usuario usuario = this.getUsuario(context);
        Comunidad comunidad = this.repoComunidades.buscarPorId(Long.parseLong(context.pathParam("id")));
        Miembro miembro = comunidad.getMiembros().stream().
                filter(m -> m.getUsuario().equals(usuario)).findFirst().get();

        if (!miembro.isActivo()) throw new RuntimeException("El miembro ya fue desactivado");
        else {
            miembro.setActivo(false);
            FactoryRepositorios.get(Miembro.class).modificar(miembro);

            comunidad.getMiembros().remove(miembro);
            this.repoComunidades.modificar(comunidad);
        }

        model.put("mensaje", "Has abandonado la comunidad con éxito");

        context.render("mensaje.hbs", model);
    }
}


