package domain.notificaciones.formaNotificacion;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;

public interface FormaNotificacion {
  void recibirIncidente(RecepcionIncidenteParams params);
}
