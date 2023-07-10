package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


public class SinApuros implements FormaNotificacion {
  private LinkedHashSet<Incidente> incidentesANotificar;
  private List<LocalTime> horarios;
  private Persona persona;

  public SinApuros(Persona persona) {
    this.persona = persona;
    this.incidentesANotificar = new LinkedHashSet<>();
    this.horarios = new ArrayList<>();
  }

  public void recibirIncidente(RecepcionIncidenteParams params) {
    Incidente incidente = params.getIncidente();
    incidentesANotificar.add(incidente);
  }

  public void notificarIncidentes() {
    Incidente[] incidentesFiltrados = filtrarIncidentes();

    this.persona.notificarIncidente(incidentesFiltrados);
    incidentesANotificar.clear();
  }

  private Incidente[] filtrarIncidentes() {
    return incidentesANotificar.stream()
        .filter(incidente -> incidente.getFechaDeCreacion().isAfter(LocalDateTime.now().minusHours(24)))
        .filter(incidente -> incidente.abierto())
        .toArray(Incidente[]::new);
  }

  public void agregarHorario(Integer hora, Integer minutos) {
    if(hora < 0 || hora > 23 || minutos < 0 || minutos > 59)
      throw new RuntimeException("Horario invalido");
    if(horarios.contains(LocalTime.of(hora,minutos)))
      throw new RuntimeException("Horario ya agregado");
    if(minutos == 0 || minutos == 30)
      throw new RuntimeException("Solo se pueden agregar horarios en punto o y media");

    horarios.add(LocalTime.of(hora,minutos));
  }
}
