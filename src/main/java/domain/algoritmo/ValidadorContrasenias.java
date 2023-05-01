package domain.algoritmo;

import domain.validadoresAuxiliares.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidadorContrasenias {
  private List<EstrategiaValidacion> estrategias; // Estrategia es una interfaz que implementan todas las validaciones

  public ValidadorContrasenias() {
      this.estrategias = new ArrayList<>();
      Collections.addAll(this.estrategias,
          new ValidadorMinimoCaracteres(),
          new ValidadorMaximoCaracteres(),
          new ValidadorNumeros(),
          new ValidadorCaracteresEspeciales(),
          new ValidadorSecuencias(),
          new ValidadorRepetidos(),
          new ValidadorFrecuencia());
    }

  public Boolean usuarioTieneContraSegura(String nombreUsuario, String contrasenia) {
    return esValida(contrasenia) && !contrasenia.contains(nombreUsuario);
  }

  public Boolean esValida(String contrasenia) {
    boolean esValida = this.estrategias.stream().allMatch(estrategia -> estrategia.superaValidacion(contrasenia));

    return esValida;
  }
}
