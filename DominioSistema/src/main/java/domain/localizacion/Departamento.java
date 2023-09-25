package domain.localizacion;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Departamento")
@Setter
@Getter
public class Departamento extends Persistente {
   
  @Column(name = "nombre")
  private String nombre;
  
  @ManyToOne
  @JoinColumn(name = "provincia_id", referencedColumnName = "id")
  private Provincia provincia;

  public Departamento(String nombre, Provincia provincia) {
    this.nombre = nombre;
    this.provincia = provincia;
  }
}
