package domain.localizacion;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
  @Getter
  private int id;
  @Getter
  private String nombre;
  @Getter
  private Provincia provincia;

  public Departamento(int id, String nombre, Provincia provincia) {
    this.id = id;
    this.nombre = nombre;
    this.provincia = provincia;
  }
}
