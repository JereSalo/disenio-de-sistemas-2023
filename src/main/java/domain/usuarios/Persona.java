package domain.usuarios;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import domain.notificaciones.formaNotificacion.FormaNotificacion;
import domain.notificaciones.notificador.Notificador;
import domain.params.RecepcionIncidenteParams;
import domain.servicios.Servicio;
import domain.localizacion.Localizacion;
import domain.ubicacion.Ubicacion;
import domain.Persistente;
import persistence.converters.ConverterFormaNotificacion;

import lombok.Getter;
import lombok.Setter;
import persistence.converters.ConverterHorarios;
import persistence.converters.ConverterNotificador;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "persona")
@Setter 
@Getter
public class Persona extends Persistente{
 
  @OneToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @ManyToMany
  private List<Localizacion> localizacionesDeInteres;

  @ManyToMany
  private List<Servicio> serviciosDeInteres;

  @ManyToMany
  private List<Entidad> entidadesDeInteres;

  @Convert(converter = ConverterHorarios.class)
  @ElementCollection
  @CollectionTable(name = "horarios_sin_apuros", joinColumns = @JoinColumn(name = "id"))
  @Column(name = "horarios")
  private List<LocalTime> horarios;

  @ManyToMany
  private LinkedHashSet<Incidente> incidentesANotificar;

  @Convert(converter = ConverterFormaNotificacion.class)
  @Column(name = "forma_notificacion")
  private FormaNotificacion formaNotificacion;

  @Convert(converter = ConverterNotificador.class)
  @Column(name = "notificador")
  private Notificador notificador;

  public Persona(Usuario usuario) {
    this.usuario = usuario;
  }

  public void recibirIncidente(Incidente incidente) {
    if(this.leInteresa(incidente)) {
      RecepcionIncidenteParams params = new RecepcionIncidenteParams(this, incidente);
      this.formaNotificacion.recibirIncidente(params);
    }
  }

  private boolean leInteresa(Incidente incidente) {
    return this.serviciosDeInteres.contains(incidente.getPrestacionDeServicio().getServicio()) ||
      this.entidadesDeInteres.contains(incidente.getEntidad());
  }

  public void notificarIncidente(Incidente... incidentes) {
    this.notificador.notificarIncidentes(this, incidentes);
  }

  public void notificarSugerencia(Incidente incidente){
    this.notificador.notificarSugerencia(this, incidente);
  }

  public Ubicacion getUbicacionActual() {
    return null;
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
