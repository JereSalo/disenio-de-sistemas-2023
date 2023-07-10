package domain.repositorios;

import domain.notificaciones.SinApuros;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSinApuros {
  @Getter
  private static List<SinApuros> todosLosSinApuros;

  static {
    todosLosSinApuros = new ArrayList<>();
  }

  public static void agregarSinApuros(SinApuros sinApuros) {
    todosLosSinApuros.add(sinApuros);
  }
}
