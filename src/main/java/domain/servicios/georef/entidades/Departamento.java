package domain.servicios.georef.entidades;

public class Departamento {
  public int id;
  public String nombre;
  private Provincia provincia;
  private class Provincia {
    public int id;
  }

  public int idProvincia(){
    return provincia.id;
  }
}
