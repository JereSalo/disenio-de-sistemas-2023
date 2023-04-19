package domain.algoritmo;

import domain.usuarios.Usuario;

public class GestorContrasenias {

  public Boolean usuarioTieneContraSegura(Usuario usuario){
    String contrasenia = usuario.getPassword();
    return esValida(contrasenia);
  }

  public Boolean esValida(String contrasenia){
    //TODO
    // Hay varias cosas para chequear pero por ahora solo pongo longitud y si está en las peores 10.000
    boolean esValida;
    esValida = cumpleMinimoDeCaracteres(contrasenia) && !estaEnLasPeores10000Contrasenias(contrasenia);

    return esValida;
  }

  public Boolean cumpleMinimoDeCaracteres(String contrasenia){
    int minimoCaracteres = 8;
    int longitudContrasenia = contrasenia.length();

    return longitudContrasenia>=minimoCaracteres;
  }

  public Boolean estaEnLasPeores10000Contrasenias(String contrasenia){
    //TODO
    // ver como implementar lo de 10.000 peores contraseñas
    return false;
  }

}
