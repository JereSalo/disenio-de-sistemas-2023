package domain.localizacion;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Provincia {

  @Getter
  private int id;
  @Getter
  private String nombre;

  public Provincia(int id, String nombre) {
    this.nombre = nombre;
    this.id = id;
  }
}
