package domain.localizacion;

public class Municipio {
  public String nombre;
  public Departamento departamento;
  public Provincia provincia;

  public Municipio(String nombre){
    this.nombre = nombre;
  }
}
