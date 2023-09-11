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
public final class SinApuros implements FormaNotificacion{

  private static final SinApuros instancia = new SinApuros();

  private SinApuros(){}

  public static SinApuros obtenerInstancia(){
    return instancia;
  }
  //private LinkedHashSet<Incidente> incidentesANotificar;

  //private List<LocalTime> horarios;

  /*@OneToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")*/
  //private Persona persona;

  /*private SinApuros(Persona persona) {
    this.persona = persona;
    this.incidentesANotificar = new LinkedHashSet<>();
    this.horarios = new ArrayList<>();
  }*/

  
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
    params.getPersona().getIncidentesANotificar().add(incidente);
  }

  public void notificarIncidentes(Persona persona) {
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
