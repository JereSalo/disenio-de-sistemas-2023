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
    //TODO
    boolean esValida;
    esValida = cumpleCondicionesDeLongitud(contrasenia) && 
                cumpleCondicionesDeCaracteres(contrasenia) &&
                noEsFrecuente(contrasenia);
    // Juntar las que tienen que ver con Longitud, Caracteres Contenidos, Si están en archivos específicos...

    // esValida = cumpleCondicionesDeLongitud() && cumpleCondicionesDeCaracteres() && CumpleCondicionesDeSeguridad()

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

  public String obtenerCaracterRepetidoNVeces(char caracter, int n) {
    return String.valueOf(caracter).repeat(n);
  }

   public boolean stringContieneAlgunCaracter(String s1, String s2) {
    
    for (int i = 0; i < s2.length(); i++){
      if (s1.contains(String.valueOf(s2.charAt(i)))) return true;
 
    }
    return false;
  }
  
  // CONDICIONES DE CONTRASEÑA FRECUENTE

  public boolean perteneceAArchivo(String ruta, String contrasenia) {
    var listaContrasenias = biblioteca.obtenerLista(ruta);
    return listaContrasenias.contains(contrasenia);
  }
  
  public boolean noEsFrecuente(String contrasenia) {
    var rutaContraseniasEspaniol = Configuracion.getRutaContraseniasEspaniol();
    var rutaPeoresContrasenias = Configuracion.getRutaPeoresContrasenias();

    return !perteneceAArchivo(rutaContraseniasEspaniol,contrasenia) && !perteneceAArchivo(rutaPeoresContrasenias,contrasenia);
  }
}
