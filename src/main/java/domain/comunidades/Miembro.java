package domain.comunidades;

import domain.incidentes.Incidente;
import domain.servicios.Servicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "Miembro")
@Setter
@Getter
public class Miembro extends Persistente{
  
  @ManyToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona persona;
  
  @OneToMany(mappedBy = "miembro")
  private List<RolMiembroServicio> rolesPorServicio;

  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

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
