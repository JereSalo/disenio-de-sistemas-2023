package domain.notificaciones.formaNotificacion;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

public interface FormaNotificacion {
  void recibirIncidente(RecepcionIncidenteParams params);
  void notificarIncidentesPendientes(Persona persona);
}
