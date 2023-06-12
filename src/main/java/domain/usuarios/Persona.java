package domain.usuarios;

import domain.entidades.Entidad;
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

  public Persona(Usuario usuario, List<Localizacion> localizacionesDeInteres, List<Servicio> serviciosDeInteres, List<Entidad> entidadesDeInteres) {
    this.usuario = usuario;
    this.localizacionesDeInteres = localizacionesDeInteres;
    this.serviciosDeInteres = serviciosDeInteres;
    this.entidadesDeInteres = entidadesDeInteres;
  }
}
