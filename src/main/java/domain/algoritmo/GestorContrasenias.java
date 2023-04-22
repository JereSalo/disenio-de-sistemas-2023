package domain.algoritmo;

import domain.usuarios.Usuario;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GestorContrasenias {
  private List<String> peoresContrasenias;

  public GestorContrasenias(String archivoPeoresContrasenias) {
    leerPeoresContrasenias(archivoPeoresContrasenias);
  }

  public Boolean usuarioTieneContraSegura(Usuario usuario) {
    String contrasenia = usuario.getPassword();
    return esValida(contrasenia);
  }

  public Boolean esValida(String contrasenia) {
    //TODO
    // Hay varias cosas para chequear pero por ahora solo pongo longitud y si está en las peores 10.000
    boolean esValida;
    esValida = cumpleMinimoDeCaracteres(contrasenia)
            && !estaEnLasPeores10000Contrasenias(contrasenia);

    return esValida;
  }

  public Boolean cumpleMinimoDeCaracteres(String contrasenia) {
    int minimoCaracteres = 8;
    int longitudContrasenia = contrasenia.length();

    return longitudContrasenia >= minimoCaracteres;
  }

  public Boolean estaEnLasPeores10000Contrasenias(String contrasenia) {
    return peoresContrasenias.contains(contrasenia);
  }

  public void leerPeoresContrasenias(String archivo) {
    InputStream archivoComoStream = this.getClass().getClassLoader().getResourceAsStream(archivo);
    peoresContrasenias = new BufferedReader(new InputStreamReader(archivoComoStream)).lines().toList();
  }
}
