package domain.usuarios;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import domain.notificaciones.formaNotificacion.FormaNotificacion;
import domain.notificaciones.formaNotificacion.HorarioSinApuros;
import domain.notificaciones.formaNotificacion.SinApuros;
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
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Persona")
@Setter 
@Getter
public class Persona extends Persistente{
 
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @OneToMany
  @JoinTable(
      name="InteresadosPorLocalizacion",
      joinColumns = @JoinColumn( name="persona_id"),
      inverseJoinColumns = @JoinColumn( name="localizacion_id")
  )
  private List<Localizacion> localizacionesDeInteres;

  @ManyToMany
  private List<Servicio> serviciosDeInteres;

  @ManyToMany
  private List<Entidad> entidadesDeInteres;

  @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
  private List<HorarioSinApuros> horariosSinApuros;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Incidente> incidentesANotificar;

  @Convert(converter = ConverterFormaNotificacion.class)
  @Column(name = "forma_notificacion")
  private FormaNotificacion formaNotificacion;

  @Convert(converter = ConverterNotificador.class)
  @Column(name = "notificador")
  private Notificador notificador;

  public Persona(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<LocalTime> getHorarios(){
    return this.horariosSinApuros.stream().map(h -> h.getHorario()).toList();
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

  public void notificarIncidentesPendientes() {
    formaNotificacion.notificarIncidentesPendientes(this);
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

}
