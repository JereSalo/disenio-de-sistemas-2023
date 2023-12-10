package domain.comunidades;

import domain.Persistente;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.servicios.Servicio;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "Miembro")
@Setter
@Getter
public class Miembro extends Persistente {
  
  @ManyToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona persona;

  @Column(name="afectado")
  private boolean afectado;

  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

  @Column(name = "activo")
  private boolean activo;

  public Miembro(Persona persona) {
    this.persona = persona;
  }

  public Miembro(Persona persona, Comunidad comunidad, boolean afectado) {
    this.persona = persona;
    this.comunidad = comunidad;
    this.afectado = afectado;
    this.activo = true;
  }

  public Miembro() {

  }

  public void recibirIncidente(Incidente incidente) {
    this.persona.recibirIncidente(incidente);
  }

  public String getUsername() {return this.persona.getUsername();}

  public Usuario getUsuario(){
    return this.persona.getUsuario();
  }

  public boolean estaAfectado() {
    return this.afectado;
  }
}
