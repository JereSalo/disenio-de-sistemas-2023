package domain.validadoresAuxiliares;

import domain.algoritmo.EstrategiaValidacion;

public class ValidadorMaximoCaracteres implements EstrategiaValidacion {
  int MAXIMO_CARACTERES = 64;

  @Override
  public Boolean superaValidacion(String contrasenia) {
    return contrasenia.length() <= MAXIMO_CARACTERES;
  }
}
