package domain.params;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import domain.establecimientos.Establecimiento;
import domain.servicios.PrestacionServicio;
import lombok.Getter;

public class AperturaIncidenteParams {
  @Getter private Entidad entidad;
  @Getter private Establecimiento establecimiento;
  @Getter private PrestacionServicio prestacionDeServicio;
  @Getter private Miembro creador;
  @Getter private String observaciones;
  @Getter private Comunidad comunidad;

  public AperturaIncidenteParams(Entidad entidad, Establecimiento establecimiento, PrestacionServicio prestacionDeServicio, Miembro creador, Comunidad comunidad, String observaciones) {
    this.entidad = entidad;
    this.establecimiento = establecimiento;
    this.prestacionDeServicio = prestacionDeServicio;
    this.creador = creador;
    this.comunidad = comunidad;
    this.observaciones = observaciones;
  }
}
