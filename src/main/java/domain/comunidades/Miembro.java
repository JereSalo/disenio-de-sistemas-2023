package domain.comunidades;

import domain.incidentes.Incidente;
import domain.servicios.Servicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Miembro {
  @Setter @Getter
  private Persona persona;
  @Setter @Getter
  private List<RolMiembroServicio> rolesPorServicio;
  public Miembro(Persona persona) {
    this.persona = persona;
  }

  public void recibirIncidente(Incidente incidente) {
    this.persona.recibirIncidente(incidente);
  }

  public boolean estaAfectado(Servicio servicio) {
    return rolesPorServicio.stream().
                            anyMatch(rolMiembroServicio -> rolMiembroServicio.getServicio().equals(servicio)
                                    && rolMiembroServicio.getAfectado()); }
}
