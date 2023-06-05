package domain.localizacion;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
  public String nombre;
  public List<Departamento> departamentos;

  public Provincia(String nombre, List<Departamento> departamentos){
    this.nombre = nombre;
    this.departamentos = new ArrayList<Departamento>();
    this.departamentos.addAll(departamentos);
  }
}
