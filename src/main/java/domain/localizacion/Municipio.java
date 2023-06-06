package domain.localizacion;

import lombok.Getter;

public class Municipio {
  @Getter
  private int id;
  @Getter
  private String nombre;
  @Getter
  private Departamento departamento;

  public Municipio(int id, String nombre, Departamento departamento) {
    this.nombre = nombre;
    this.id = id;
    this.departamento = departamento;
  }
}
