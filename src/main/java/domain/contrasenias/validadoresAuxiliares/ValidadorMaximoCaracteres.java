package domain.contrasenias.validadoresAuxiliares;

public class ValidadorMaximoCaracteres implements EstrategiaValidacion {
  int MAXIMO_CARACTERES = 64;

  @Override
  public Boolean esValida(String contrasenia) {
    return contrasenia.length() <= MAXIMO_CARACTERES;
  }
}
