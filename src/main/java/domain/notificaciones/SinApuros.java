package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class SinApuros implements FormaNotificacion {
  private LinkedHashSet<Incidente> incidentesANotificar;
  private Set<LocalTime> horarios;

  public void recibirIncidente(RecepcionIncidenteParams params) {
    Incidente incidente = params.getIncidente();
    incidentesANotificar.add(incidente);
  }

  public void notificarIncidentes(Persona persona) {
    Incidente[] incidentesArray = incidentesANotificar.toArray(new Incidente[0]);
    persona.notificarIncidente(incidentesArray);
  }
}
