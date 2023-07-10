package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

public class CuandoSuceden implements FormaNotificacion{
  public void recibirIncidente(RecepcionIncidenteParams params) {
    Incidente incidente = params.getIncidente();
    Persona persona = params.getPersona();

    persona.notificarIncidente(incidente);
  }
}
