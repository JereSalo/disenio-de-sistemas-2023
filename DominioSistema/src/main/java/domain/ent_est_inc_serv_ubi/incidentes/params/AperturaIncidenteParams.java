package domain.ent_est_inc_serv_ubi.incidentes.params;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
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
