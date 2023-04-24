package domain.algoritmo;

import domain.usuarios.Usuario;
import java.util.List;

public class GestorContrasenias {

  private int MINIMO_CARACTERES = 8;
  private int MAXIMO_CARACTERES = 64;
  private int MAXIMO_CARACTERES_REPETIDOS = 3;
  private BibliotecaAuxiliar biblioteca = new BibliotecaAuxiliar();

  
  public Boolean usuarioTieneContraSegura(Usuario usuario) {
    String contrasenia = usuario.getPassword();
    return esValida(contrasenia) && contraseniaNoContieneUsername(usuario);
  }

  public Boolean contraseniaNoContieneUsername(Usuario usuario) {
    String contrasenia = usuario.getPassword(); 
    String nombreUsuario = usuario.getUsername();
    
    return !contrasenia.contains(nombreUsuario);
  }

  public Boolean esValida(String contrasenia) {
    boolean esValida;
    esValida = cumpleCondicionesDeLongitud(contrasenia) && 
                cumpleCondicionesDeCaracteres(contrasenia) &&
                noEsFrecuente(contrasenia);

    return esValida;
  }

  public boolean cumpleCondicionesDeLongitud(String contrasenia) {
    return cumpleMinimoDeCaracteres(contrasenia) && cumpleMaximoDeCaracteres(contrasenia);
  }

  public boolean cumpleCondicionesDeCaracteres(String contrasenia) {  
    return tieneNumeros(contrasenia) && 
     tieneCaracteresEspeciales(contrasenia) && 
      this.biblioteca.noTieneSecuencias(contrasenia) && 
      !tieneRepetidosSeguidos(contrasenia);
  }

  public boolean noEsFrecuente(String contrasenia) {
    var rutaContraseniasEspaniol = Configuracion.getRutaContraseniasEspaniol();
    var rutaPeoresContrasenias = Configuracion.getRutaPeoresContrasenias();

    return !perteneceAArchivo(rutaContraseniasEspaniol,contrasenia) && !perteneceAArchivo(rutaPeoresContrasenias,contrasenia);
  }


  // CONDICIONES DE LONGITUD
  public Boolean cumpleMinimoDeCaracteres(String contrasenia) {
    
    int longitudContrasenia = contrasenia.length();

    return longitudContrasenia >= this.MINIMO_CARACTERES;
  }

  public boolean cumpleMaximoDeCaracteres(String contrasenia) {
    int longitudContrasenia = contrasenia.length();
    
    return longitudContrasenia <= this.MAXIMO_CARACTERES;
  }
  
  // CONDICIONES DE CARACTARES
  public boolean tieneNumeros(String contrasenia) {
    return this.stringContieneAlgunCaracter(contrasenia,Configuracion.getListaNumeros());
  }

  public boolean tieneCaracteresEspeciales(String contrasenia) {
    return stringContieneAlgunCaracter(contrasenia, Configuracion.getListaCaracteresEspeciales());
  }

  public boolean tieneRepetidosSeguidos(String contrasenia) {
      for (int i = 0; i < contrasenia.length(); i++){
        char caracterActual = contrasenia.charAt(i);
        if (contrasenia.contains(obtenerCaracterRepetidoNVeces(caracterActual, this.MAXIMO_CARACTERES_REPETIDOS)))
          return true;
      }
      return false;
  }

  // METODOS AUXILIARES

  public String obtenerCaracterRepetidoNVeces(char caracter, int n) {
    return String.valueOf(caracter).repeat(n);
  }

  public boolean stringContieneAlgunCaracter(String string, String caracteres) {
    for (int i = 0; i < caracteres.length(); i++){
      if (string.contains(String.valueOf(caracteres.charAt(i)))) return true;
    }
    return false;
  }

  public boolean perteneceAArchivo(String ruta, String contrasenia) {
    var listaContrasenias = biblioteca.obtenerLista(ruta);
    return listaContrasenias.contains(contrasenia);
  }
}
