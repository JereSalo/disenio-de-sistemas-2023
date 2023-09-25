package domain.notificaciones.formaNotificacion;

import domain.ent_est_inc_serv_ubi.incidentes.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

public interface FormaNotificacion {
  void recibirIncidente(RecepcionIncidenteParams params);
  void notificarIncidentesPendientes(Persona persona);
}
