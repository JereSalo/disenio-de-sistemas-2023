package domain.repositorios;

import domain.notificaciones.SinApuros;
import lombok.Getter;

import java.util.List;

public class RepositorioSinApuros {
  @Getter
  private static List<SinApuros> todosLosSinApuros;
}
