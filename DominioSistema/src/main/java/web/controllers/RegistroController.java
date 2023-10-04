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
            context.result("El usuario ya existe");
        }
        else if (contraseniaEsSegura(context)) {
            Usuario usuario = new Usuario(context.formParam("username"), context.formParam("password"), context.formParam("email"));
            // this.repoDeUsuarios.agregar(usuario); // Hay que arreglar problemas con la base de datos para que esto funcione
            context.sessionAttribute("current-user", context.formParam("username"));
            context.redirect("home");
        }
        else{
            context.result("La contraseña no es segura");
        }
    }

    private boolean usuarioYaExiste(String nombreUsuario){
        return false;
        // return this.repoDeUsuarios.obtenerTodos().stream().anyMatch(usuario -> usuario.getUsername().equals(nombreUsuario));
    }

    public boolean contraseniaEsSegura(Context context){
        ValidadorContrasenias validador = new ValidadorContrasenias();
        String nombreUsuario = context.formParam("username");
        String contrasenia = context.formParam("password");

        return validador.usuarioTieneContraSegura(nombreUsuario, contrasenia);


        // return true;
        // Aca hay que chequear si el usuario ya existe o no, y si la contrasenia es valida

        //return this.repoDeUsuarios.obtenerTodos().stream().anyMatch(usuario -> usuario.getUsername().equals(context.formParam("username")) && usuario.getPassword().equals(context.formParam("password")));
        // Esto ta robado del login, tiene que ser distinto para registrar
    }
}
