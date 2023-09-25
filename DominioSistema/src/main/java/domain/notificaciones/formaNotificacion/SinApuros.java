package domain.notificaciones.formaNotificacion;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.rankings.CalculadorRanking;
import domain.rankings.rankings.Ranking;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.*;


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
