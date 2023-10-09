package domain.usuarios;

import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.notificaciones.formaNotificacion.FormaNotificacion;
import domain.notificaciones.formaNotificacion.HorarioSinApuros;
import domain.notificaciones.notificador.Notificador;
import domain.ent_est_inc_serv_ubi.incidentes.params.RecepcionIncidenteParams;
import domain.ent_est_inc_serv_ubi.servicios.Servicio;
import domain.localizacion.Localizacion;
import domain.ent_est_inc_serv_ubi.ubicacion.Ubicacion;
import domain.Persistente;
import persistence.converters.ConverterFormaNotificacion;

import lombok.Getter;
import lombok.Setter;
import persistence.converters.ConverterNotificador;

import java.time.LocalTime;
import java.util.ArrayList;
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

  @OneToMany(mappedBy = "persona")
  private List<HorarioSinApuros> horariosSinApuros;

  @ManyToMany
  private List<Incidente> incidentesANotificar;

  @Convert(converter = ConverterFormaNotificacion.class)
  @Column(name = "forma_notificacion")
  private FormaNotificacion formaNotificacion;

  @Convert(converter = ConverterNotificador.class)
  @Column(name = "notificador")
  private Notificador notificador;

  public Persona(Usuario usuario) {
    this.usuario = usuario;
    this.localizacionesDeInteres = new ArrayList<>();
    this.serviciosDeInteres = new ArrayList<>();
    this.entidadesDeInteres = new ArrayList<>();
    this.horariosSinApuros = new ArrayList<>();
    this.incidentesANotificar = new ArrayList<>();

  }

  public Persona() {
    this.localizacionesDeInteres = new ArrayList<>();
    this.serviciosDeInteres = new ArrayList<>();
    this.entidadesDeInteres = new ArrayList<>();
    this.horariosSinApuros = new ArrayList<>();
    this.incidentesANotificar = new ArrayList<>();
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

  public String getUsername() {return this.getUsuario().getUsername();}

}
