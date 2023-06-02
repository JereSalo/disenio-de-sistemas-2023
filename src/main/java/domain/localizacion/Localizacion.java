package domain.localizacion;

public class Localizacion {
  public Municipio municipio;
  public Provincia provincia;
  public Departamento departamento;

  public Localizacion(Municipio municipio, Provincia provincia, Departamento departamento){
    this.municipio = municipio;
    this.provincia = provincia;
    this.departamento = departamento;
  }

}
