package domain.usuarios;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "permiso")
@Setter
@Getter
public class Permiso extends Persistente {
  @Column(name = "nombre")
  private String nombre;

  @Column(name = "nombreInterno")
  private String nombreInterno;

  public boolean coincideConNombreInterno(String nombre) {
    return this.nombreInterno.equals(nombre);
  }
}