package domain.localizacion;

import java.util.ArrayList;
import java.util.List;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table (name = "Provincia")
@Setter@Getter
public class Provincia extends Persistente {

  @Column(name = "nombre")
  private String nombre;

  public Provincia(String nombre) {
    this.nombre = nombre;
  }
}
