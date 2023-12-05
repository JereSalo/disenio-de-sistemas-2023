package web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import org.eclipse.jetty.security.UserAuthentication;
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
    
    public void mostrarComunidadesQueNoPertenece(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Usuario usuarioActual = this.getUsuario(context);

        List<Comunidad> comunidadesALasQueNoPertenece =
                this.repoComunidades.obtenerTodos().stream().filter(c -> !c.esMiembro(usuarioActual)).toList();
        model.put("titulo", "Lista de comunidades a las que no pertenece");
        model.put("comunidades", comunidadesALasQueNoPertenece);
        context.render("comunidades/listaComunidades.hbs", model);
    }



    public void mostrarComunidadesQuePertenece(Context context){
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Usuario usuarioActual = super.getUsuario(context);

        List<Comunidad> comunidadesALasQuePertenece =
                this.repoComunidades.obtenerTodos().stream().filter(c -> c.esMiembro(usuarioActual)).toList();
        model.put("titulo", "Lista de comunidades a las que pertenece");
        model.put("comunidades", comunidadesALasQuePertenece);
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
}
