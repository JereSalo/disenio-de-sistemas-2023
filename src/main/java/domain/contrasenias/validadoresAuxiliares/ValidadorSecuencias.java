package domain.contrasenias.validadoresAuxiliares;

public class ValidadorSecuencias implements EstrategiaValidacion {
  private int MAXIMO_CARACTERES_SECUENCIA = 3;
  @Override
  public Boolean superaValidacion(String contrasenia) {
    for (int i = 0; i < contrasenia.length() - 2; i++) {
      if (Character.isDigit(contrasenia.charAt(i)) || Character.isLetter(contrasenia.charAt(i))) {
        if (contrasenia.charAt(i) == (contrasenia.charAt(i+1) - 1)
            && contrasenia.charAt(i + 1) == (contrasenia.charAt( i + 2) - 1)){
          return false;
        }
      }
    }
    return true;
  }
}
