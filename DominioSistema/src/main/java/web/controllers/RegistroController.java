package web.controllers;

import domain.contrasenias.ValidadorContrasenias;
import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;
import web.controllers.base.Controller;

import java.util.HashMap;
import java.util.Map;

public class RegistroController extends Controller {
    // Aca no hay model

    private Repositorio<Usuario> repoDeUsuarios;

    public RegistroController(Repositorio<Usuario> repoDeUsuarios){
        this.repoDeUsuarios = repoDeUsuarios;
    }

    public void mostrarPantallaRegistrarse(Context context){
        if (yaEstaLogeado(context)) context.redirect("home");
        else context.render("registrarse.hbs");
    }

    public void registrarUsuario(Context context) {
        if (this.usuarioYaExiste(context.formParam("username"))) {
            mensajeUsuarioYaExiste(context);
        }
        else if (!contraseniaEsSegura(context)) {
            mensajeContraseniaNoSegura(context);
        }
        else{
            crearUsuario(context);
            context.sessionAttribute("current-user", context.formParam("username"));
            context.redirect("home");
        }
    }

    private void mensajeUsuarioYaExiste(Context context){
        Map<String, Object> model = new HashMap<>();
        model.put("mensaje","ERROR: Usuario ya existente");
        context.render("mensaje.hbs", model);
    }

    private void mensajeContraseniaNoSegura(Context context){
        Map<String, Object> model = new HashMap<>();
        model.put("mensaje","ERROR: La contraseña no es segura");
        context.render("mensaje.hbs", model);
    }

    private void crearUsuario(Context context){
        // Hashing contraseña aca ?
        Usuario usuario = new Usuario(context.formParam("username"),
                context.formParam("password"), context.formParam("email"));

        // Cambiar por query más sencilla "SELECT * FROM rol WHERE nombre = "miembro""
        usuario.setRol(FactoryRepositorios.get(Rol.class).obtenerTodos().stream().filter(r -> r.getNombre().equals("Miembro")).findFirst().get());


        this.repoDeUsuarios.agregar(usuario);

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
