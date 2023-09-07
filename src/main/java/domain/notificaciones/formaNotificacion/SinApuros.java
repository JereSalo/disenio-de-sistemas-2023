package domain.notificaciones.formaNotificacion;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.repositorios.RepositorioSinApuros;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "SinApuros")
@Setter
@Getter
public class SinApuros implements FormaNotificacion, extends Persistente{
  
  //TODO
  private LinkedHashSet<Incidente> incidentesANotificar;

  //TODO
  private List<LocalTime> horarios;

  @OneToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona persona;

  private SinApuros(Persona persona) {
    this.persona = persona;
    this.incidentesANotificar = new LinkedHashSet<>();
    this.horarios = new ArrayList<>();
  }

  
  /*
  // No iria aca, este metodo iria en el repo
  public static SinApuros crear(Persona persona) {
    SinApuros sinApuros = new SinApuros(persona);
    RepositorioSinApuros.agregarSinApuros(sinApuros);
    return sinApuros;
  }
  */
  public void recibirIncidente(RecepcionIncidenteParams params) {
    Incidente incidente = params.getIncidente();
    incidentesANotificar.add(incidente);
  }

  public void notificarIncidentes() {
    Incidente[] incidentesFiltrados = filtrarIncidentes();
    if(incidentesFiltrados.length == 0)
      return;

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
