package domain.localizacion;

import javax.persistence.*;
import lombok.Getter;

@Entity
@Table (name = "Municipio")
@Setter@Getter
public class Municipio extends Persistente{
  
  @Column(name = "nombre")
  private String nombre;

  @ManyToOne
  @JoinColumn(name = "departamento_id", referencedColumnName = "id")
  private Departamento departamento;

  public Municipio(int id, String nombre, Departamento departamento) {
    this.nombre = nombre;
    this.id = id;
    this.departamento = departamento;
  }
}
