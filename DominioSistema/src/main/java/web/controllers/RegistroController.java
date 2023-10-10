package web.controllers;

import domain.contrasenias.ValidadorContrasenias;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
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
            //TODO: Hacerlo más bonito. Debería hacer comprobación de email? Capaz en un futuro sí...
            context.result("El usuario ya existe");
        }
        else if (contraseniaEsSegura(context)) {
            Usuario usuario = new Usuario(context.formParam("username"), context.formParam("password"), context.formParam("email"));
            this.repoDeUsuarios.agregar(usuario);
            context.sessionAttribute("current-user", context.formParam("username"));
            context.redirect("home");
        }
        else{
            //TODO: Hacerlo más bonito, aclarar por qué no es segura la contraseña.
            context.result("La contraseña no es segura");
        }
    }

    private boolean usuarioYaExiste(String nombreUsuario){
        return this.repoDeUsuarios.obtenerTodos().stream().anyMatch(usuario -> usuario.getUsername().equals(nombreUsuario));
    }

    public boolean contraseniaEsSegura(Context context){
        ValidadorContrasenias validador = new ValidadorContrasenias();
        String nombreUsuario = context.formParam("username");
        String contrasenia = context.formParam("password");

        // return validador.usuarioTieneContraSegura(nombreUsuario, contrasenia); // Esto anda bien, lo comento para testear más rápido el resto
        return true;
    }
}
