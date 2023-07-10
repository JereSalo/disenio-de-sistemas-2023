package domain.incidentes;

import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import domain.establecimientos.Establecimiento;
import domain.params.AperturaIncidenteParams;
import domain.servicios.PrestacionServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;

public class Incidente {
  @Setter @Getter
  private Entidad entidad;

  @Getter @Setter
  private Establecimiento establecimiento;

  @Getter @Setter
  private PrestacionServicio prestacionDeServicio;

  @Getter @Setter
  private Miembro creador;

  @Getter @Setter
  private LocalDate fechaDeCreacion;

  @Getter @Setter
  private LocalDate fechaDeCierre;

  @Getter @Setter
  private String observaciones;

  public Incidente (AperturaIncidenteParams params) {
    this.entidad = params.getEntidad();
    this.establecimiento = params.getEstablecimiento();
    this.prestacionDeServicio = params.getPrestacionDeServicio();
    this.creador = params.getCreador();
    this.fechaDeCreacion = LocalDate.now();
    this.fechaDeCierre = null;
    this.observaciones = params.getObservaciones();
  }

  public void cerrar() {
    this.fechaDeCierre = LocalDate.now();
  }
}
