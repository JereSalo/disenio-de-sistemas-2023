package domain.localizacion;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
  public String nombre;
  public List<Municipio> municipios;

  public Departamento(String nombre, List<Municipio> municipios){
    this.nombre = nombre;
    this.municipios = new ArrayList<Municipio>();
    this.municipios.addAll(municipios);
  }
}
