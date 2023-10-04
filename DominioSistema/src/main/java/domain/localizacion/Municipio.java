package domain.localizacion;

import javax.persistence.*;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "Municipio")
@Setter
@Getter
public class Municipio extends Persistente {
  
  @Column(name = "nombre")
  private String nombre;

  @ManyToOne
  @JoinColumn(name = "departamento_id", referencedColumnName = "id")
  private Departamento departamento;

  public Municipio(String nombre, Departamento departamento) {
    this.nombre = nombre;
    this.departamento = departamento;
  }

  public Municipio() {

  }
}
