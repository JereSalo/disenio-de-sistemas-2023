package web.controllers;

import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;
import web.controllers.base.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministracionUsuariosController extends Controller {

    private Repositorio<Usuario> repoDeUsuarios;
    public AdministracionUsuariosController(Repositorio<Usuario> repoDeUsuarios) {
        this.repoDeUsuarios = repoDeUsuarios;
    }

    public void mostrarUsuarios(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        List<Usuario> listaUsuarios = this.repoDeUsuarios.obtenerTodos();

        model.put("usuarios", listaUsuarios);

        context.render("usuarios/administracionUsuarios.hbs", model);
    }

    public void mostrarDetalleUsuario(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Usuario usuario = this.repoDeUsuarios.buscarPorId(Long.parseLong(context.pathParam("id-usuario")));

        model.put("usuario", usuario);

        List<Rol> listaRoles = FactoryRepositorios.get(Rol.class).obtenerTodos();

        model.put("roles", listaRoles);

        context.render("usuarios/administracionUsuario.hbs",model);
    }

    public void actualizarDetalleUsuario(Context context) {
        Map<String, Object> model = new HashMap<>();

        Usuario usuario = this.repoDeUsuarios.buscarPorId(Long.parseLong(context.pathParam("id-usuario")));

        Boolean esElUsuarioLogueado = super.getUsuario(context).getUsername().equals(usuario.getUsername());

        usuario.setMail(context.formParam("mail"));
        usuario.setUsername(context.formParam("nombreUsuario"));
        usuario.setRol(FactoryRepositorios.get(Rol.class).buscarPorId(Long.valueOf(context.formParam("rol"))));

        this.repoDeUsuarios.modificar(usuario);

        if(esElUsuarioLogueado)
            context.redirect("/logout");
        else
            context.redirect("/administracion/usuarios");
    }
}
