package domain.usuarios;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import domain.notificaciones.formaNotificacion.FormaNotificacion;
import domain.notificaciones.notificador.Notificador;
import domain.params.RecepcionIncidenteParams;
import domain.servicios.Servicio;
import domain.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Persona {
  @Setter @Getter
  private Usuario usuario;
  @Setter @Getter
  private List<Localizacion> localizacionesDeInteres;
  @Setter @Getter
  private List<Servicio> serviciosDeInteres;
  @Setter @Getter
  private List<Entidad> entidadesDeInteres;
  @Setter @Getter
  private FormaNotificacion formaNotificacion;
  @Setter @Getter
  private Notificador notificador;

  public Persona(Usuario usuario) {
    this.usuario = usuario;
  }

  public void recibirIncidente(Incidente incidente) {
    RecepcionIncidenteParams params = new RecepcionIncidenteParams(this, incidente);
    this.formaNotificacion.recibirIncidente(params);
  }

  public void notificarIncidente(Incidente... incidentes) {
    this.notificador.notificarIncidentes(this, incidentes);
  }
}
