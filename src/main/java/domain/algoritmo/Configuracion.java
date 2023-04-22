package domain.algoritmo;

import lombok.Getter;
import lombok.Setter;

// Patrón de diseño singleton. Para tener una única instancia de la clase Configuración.
// No se si es el adecuado para un Config porque si quiero puedo cambiar en tiempo de ejecución un atributo y capaz ese no es el propósito de una clase de Configuración.
// No se cual sería la decisión de diseño correcta para la clase Configuracion.

public class Configuracion {
  private static Configuracion instanciaUnica; // instancia única de la clase
  @Setter @Getter
  private String rutaPeoresContrasenias;

  // Constructor privado para evitar instanciación externa
  private Configuracion() {
    rutaPeoresContrasenias = "10k-worst-passwords.txt";
  }

  // Método público para obtener la instancia única de la clase
  public static Configuracion obtenerInstancia() {
    if (instanciaUnica == null) {
      instanciaUnica = new Configuracion();
    }
    return instanciaUnica;
  }
}