package domain.notificaciones.formaNotificacion;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.incidentes.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

import java.time.LocalDateTime;


public final class SinApuros implements FormaNotificacion{

  private static SinApuros instancia = null;

  public static SinApuros obtenerInstancia(){

    if (instancia == null){
      instancia = new SinApuros();
    }

    return instancia;
  }

  public void recibirIncidente(RecepcionIncidenteParams params) {
    Incidente incidente = params.getIncidente();
    params.getPersona().getIncidentesANotificar().add(incidente);
  }

  public void notificarIncidentesPendientes(Persona persona) {
    Incidente[] incidentesFiltrados = filtrarIncidentes(persona);
    if(incidentesFiltrados.length == 0)
      return;

    persona.notificarIncidente(incidentesFiltrados);
    persona.getIncidentesANotificar().clear();
  }


  private Incidente[] filtrarIncidentes(Persona persona) {
    return persona.getIncidentesANotificar().stream()
        .filter(incidente -> incidente.getFechaDeCreacion().isAfter(LocalDateTime.now().minusHours(24)))
        .filter(Incidente::abierto)
        .toArray(Incidente[]::new);
  }
}
