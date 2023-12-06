package web.controllers;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;
import web.controllers.base.Controller;

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
                this.repoComunidades.obtenerTodos().stream().filter(c -> c.esMiembro(usuarioActual)).toList();
        List<Comunidad> comunidadesALasQueNoPertenece =
                this.repoComunidades.obtenerTodos().stream().filter(c -> !c.esMiembro(usuarioActual)).toList();

        model.put("comunidadesQuePertenece", comunidadesALasQuePertenece);
        model.put("comunidadesQueNoPertenece", comunidadesALasQueNoPertenece);
        context.render("comunidades/listaComunidades.hbs", model);
    }


    public void unirseAComunidad(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Long idComunidad = Long.valueOf(context.pathParam("id-comunidad"));
        Comunidad comunidadAfectada = this.repoComunidades.buscarPorId(idComunidad);

        Usuario user = this.getUsuario(context);

        List<Persona> listaPersonas = FactoryRepositorios.get(Persona.class).obtenerTodos();
        Persona persona = listaPersonas.stream().filter(p -> p.getUsername().equals(user.getUsername())).findFirst().get();

        Miembro nuevoMiembro = new Miembro(persona, comunidadAfectada);
        comunidadAfectada.getMiembros().add(nuevoMiembro);

        Repositorio<Miembro> repoMiembro = FactoryRepositorios.get(Miembro.class);
        repoMiembro.agregar(nuevoMiembro);

        this.repoComunidades.modificar(comunidadAfectada);

        model.put("mensaje", "Se unio con exito a la comunidad");
        context.render("mensaje.hbs", model);
    }
}
