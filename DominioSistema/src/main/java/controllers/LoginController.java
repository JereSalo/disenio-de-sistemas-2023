package controllers;

import domain.usuarios.Usuario;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;


public class LoginController {
  private Repositorio<Usuario> repositorioDeUsuario;

  public LoginController(Repositorio<Usuario> repositorioDeUsuario){
    this.repositorioDeUsuario = repositorioDeUsuario;
  }
  public void logearse(Context context){
    if(yaEstaLogeado(context)){
      context.result("Ya está logeado");
    }
    else if(sonCredencialesValidas(context)){
      context.redirect("index.html");
    }
    else{
      context.result("Los datos ingresados no son correctos");
    }
  }

  public boolean yaEstaLogeado(Context context){
    return true;
  }

  public boolean sonCredencialesValidas(Context context){
    return true;
  }

}
