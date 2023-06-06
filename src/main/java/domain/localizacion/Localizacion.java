package domain.localizacion;

import lombok.Getter;

public class Localizacion {
  @Getter
  private Municipio municipio;
  @Getter
  private Departamento departamento;
  @Getter
  private Provincia provincia;

  public Localizacion (Municipio municipio, Departamento departamento, Provincia provincia) {
    this.municipio = municipio;
    this.departamento = departamento;
    this.provincia = provincia;
  }
}
