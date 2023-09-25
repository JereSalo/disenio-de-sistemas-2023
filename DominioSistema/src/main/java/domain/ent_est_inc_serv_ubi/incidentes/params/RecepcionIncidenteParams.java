package domain.ent_est_inc_serv_ubi.incidentes.params;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.usuarios.Persona;
import lombok.Getter;

public class RecepcionIncidenteParams {
  @Getter private Persona persona;
  @Getter private Incidente incidente;

  public RecepcionIncidenteParams(Persona persona, Incidente incidente) {
    this.persona = persona;
    this.incidente = incidente;
  }
}
