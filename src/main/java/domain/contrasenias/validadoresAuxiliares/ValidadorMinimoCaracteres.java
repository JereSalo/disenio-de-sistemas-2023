package domain.contrasenias.validadoresAuxiliares;

public class ValidadorMinimoCaracteres implements EstrategiaValidacion {
  int MINIMO_CARACTERES = 8;

  @Override
  public Boolean superaValidacion(String contrasenia) {
    return contrasenia.length() >= MINIMO_CARACTERES;
  }
}
