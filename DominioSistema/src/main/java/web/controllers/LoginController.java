package web.controllers;

import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginController extends Controller{

  private Repositorio<Usuario> repoDeUsuarios;

  public LoginController(Repositorio<Usuario> repoDeUsuarios){
    this.repoDeUsuarios = repoDeUsuarios;
  }

  public void home(Context context){
      Map<String, Object> model = new HashMap<>();

      modificarModelSiEstaLogueado(context, model);

      context.render("index.hbs", model);
  }

  public void mostrarPantallaLogin(Context context){
    Map<String, Object> model = new HashMap<>();

    modificarModelSiEstaLogueado(context, model);

    if (yaEstaLogeado(context)) context.redirect("home");
    else context.render("login.hbs", model);

  }

  public void login(Context context){

    if (this.sonCredencialesValidas(context)){
      context.sessionAttribute("current-user", context.formParam("username"));

      context.sessionAttribute("tipo-rol", super.getUsuario(context).getRol().getTipo().name());

      context.redirect("home");
    }
    else{
      context.result("Las credenciales ingresadas son incorrectas");
    }
  }

  public void logout(Context context){
    context.req().getSession().invalidate();
    context.redirect("home");
  }

  public boolean sonCredencialesValidas(Context context){
    return this.repoDeUsuarios.obtenerTodos().stream().anyMatch(usuario -> usuario.getUsername().equals(context.formParam("username")) && usuario.getPassword().equals(context.formParam("password")));
  }
}
