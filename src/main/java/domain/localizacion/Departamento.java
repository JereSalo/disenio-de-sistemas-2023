package domain.localizacion;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Departamento")
@Setter@Getter
public class Departamento extends Persistente {
   
  @Column(name = "nombre")
  private String nombre;
  
  @ManyToOne
  @JoinColumn(name = "provincia_id", referencedColumnName = "id")
  private Provincia provincia;

  public Departamento(int id, String nombre, Provincia provincia) {
    this.id = id;
    this.nombre = nombre;
    this.provincia = provincia;
  }
}
