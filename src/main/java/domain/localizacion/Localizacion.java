package domain.localizacion;

public class Localizacion {
  public Municipio municipio;
  public Departamento departamento;
  public Provincia provincia;

  public Localizacion (Municipio municipio, Departamento departamento, Provincia provincia) {
    this.municipio = municipio;
    this.departamento = departamento;
    this.provincia = provincia;
  }
}
