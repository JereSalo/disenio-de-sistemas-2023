package domain.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import domain.establecimientos.Establecimiento;
import domain.params.AperturaIncidenteParams;
import domain.servicios.PrestacionServicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
  private Comunidad comunidad;

  @Getter @Setter
  private LocalDateTime fechaDeCreacion;

  @Getter @Setter
  private LocalDateTime fechaDeCierre;

  @Getter @Setter
  private String observaciones;

  public Incidente(){
    this.fechaDeCreacion = LocalDateTime.now();
    this.fechaDeCierre = null;
  }

  public void cerrar() {
    this.fechaDeCierre = LocalDateTime.now();
  }

  public boolean abierto(){
    return this.fechaDeCierre == null;
  }
}
