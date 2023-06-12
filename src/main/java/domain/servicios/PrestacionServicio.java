package domain.servicios;

import lombok.Getter;
import lombok.Setter;

public class PrestacionServicio {
  @Setter @Getter
  private Servicio servicio;
  @Setter @Getter
  private EstadoServicio estado;

  public PrestacionServicio(Servicio servicio, EstadoServicio estado) {
    this.servicio = servicio;
    this.estado = estado;
  }
}
