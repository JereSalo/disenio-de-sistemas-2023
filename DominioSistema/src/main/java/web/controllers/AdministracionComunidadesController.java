package web.controllers;

import domain.comunidades.Comunidad;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static web.controllers.base.Controller.modificarModelSiEstaLogueado;

public class AdministracionComunidadesController {

    private Repositorio<Comunidad> repoDeComunidades;

    public AdministracionComunidadesController(Repositorio<Comunidad> repoDeComunidades) {
        this.repoDeComunidades = repoDeComunidades;
    }

    public void mostrarComunidades(Context context) {

        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        List<Comunidad> listaComunidades = this.repoDeComunidades.obtenerTodos();

        listaComunidades.removeIf(comunidad -> !comunidad.isActiva());

        model.put("comunidades", listaComunidades);

        context.render("comunidades/administracionComunidades.hbs", model);
    }

    public void mostrarDetalleComunidad(Context context) {

        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.parseLong(context.pathParam("id-comunidad")));

        model.put("comunidad", comunidad);

        context.render("comunidades/administracionComunidad.hbs",model);
    }

    public void actualizarDetalleComunidad(Context context) {

        Map<String, Object> model = new HashMap<>();

        Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.parseLong(context.pathParam("id-comunidad")));

        comunidad.setNombre(context.formParam("nombre"));

        this.repoDeComunidades.modificar(comunidad);

        context.redirect("/administracion/comunidades");
    }

    public void mostrarCreacionComunidad(Context context) {

        System.out.println("Mostrando formulario para crear comunidad...");
        // Aca creo que solo tengo que mostrar el HTML formulario para crear comunidad...

        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        context.render("comunidades/crearComunidad.hbs", model);
    }

    public void crearComunidad(Context context) {
        // Aca recibo un POST con los datos de la comunidad a crear...

        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        String nombre = context.formParam("nombre");

        Comunidad comunidad = new Comunidad(nombre);

        this.repoDeComunidades.agregar(comunidad);

        context.redirect("/administracion/comunidades");
    }

    public void eliminarComunidad(Context context) {
        Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.parseLong(context.pathParam("id-comunidad")));

        // poner activa en false, si ya estaba en false entonces tirar error
        if (!comunidad.isActiva()) throw new RuntimeException("La comunidad ya está desactivada");
        else {
            comunidad.setActiva(false);

            this.repoDeComunidades.modificar(comunidad);
        }
    }
}
