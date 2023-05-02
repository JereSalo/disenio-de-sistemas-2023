package domain.contrasenias.validadoresAuxiliares;

public class ValidadorRepetidos implements EstrategiaValidacion {
  private int MAXIMO_CARACTERES_REPETIDOS = 3;
  @Override
  public Boolean superaValidacion(String contrasenia) {
    int contador = 0;
    for (int i = 0; i < contrasenia.length(); i++) {
      for (int j = 0; j < contrasenia.length(); j++) {
        if (contrasenia.charAt(i) == contrasenia.charAt(j)) {
          contador++;
        }
      }
      if (contador >= MAXIMO_CARACTERES_REPETIDOS) {
        return false;
      }
      contador = 0;
    }
    return true;
  }
}
