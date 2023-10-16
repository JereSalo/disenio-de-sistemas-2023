package web.controllers;

import domain.contrasenias.ValidadorContrasenias;
import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;

import java.util.HashMap;
import java.util.Map;

public class RegistroController extends Controller{

    private Repositorio<Usuario> repoDeUsuarios;

    public RegistroController(Repositorio<Usuario> repoDeUsuarios){
        this.repoDeUsuarios = repoDeUsuarios;
    }

    public void mostrarPantallaRegistrarse(Context context){
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        if (yaEstaLogeado(context)) context.redirect("home");
        else context.render("registrarse.hbs", model);
    }

    public void registrarUsuario(Context context) {
        if (this.usuarioYaExiste(context.formParam("username"))) {
            Map<String, Object> model = new HashMap<>();

            model.put("mensaje","ERROR: Usuario ya existente");
            context.render("mensaje.hbs", model);
        }
        else if (contraseniaEsSegura(context)) {

            Usuario usuario = new Usuario(context.formParam("username"), context.formParam("password"), context.formParam("email"));
            usuario.setRol(FactoryRepositorios.get(Rol.class).obtenerTodos().stream().filter(r -> r.getNombre().equals("Miembro")).findFirst().get());
            this.repoDeUsuarios.agregar(usuario);

            context.sessionAttribute("current-user", context.formParam("username"));
            context.redirect("home");
        }
        else{
            Map<String, Object> model = new HashMap<>();
            model.put("mensaje","ERROR: La contraseña no es segura");
            context.render("mensaje.hbs", model);
        }
    }

    private boolean usuarioYaExiste(String nombreUsuario){
        return this.repoDeUsuarios.obtenerTodos().stream().anyMatch(usuario -> usuario.getUsername().equals(nombreUsuario));
    }

    public boolean contraseniaEsSegura(Context context){
        ValidadorContrasenias validador = new ValidadorContrasenias();
        String nombreUsuario = context.formParam("username");
        String contrasenia = context.formParam("password");

        return validador.usuarioTieneContraSegura(nombreUsuario, contrasenia);
    }
}
