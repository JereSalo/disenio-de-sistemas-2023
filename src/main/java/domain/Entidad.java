package domain;

import domain.localizacion.Departamento;
import domain.localizacion.Municipio;
import domain.localizacion.Provincia;

import java.util.List;

public class Entidad {
  private String nombre;
  private List<Establecimiento> establecimientos;
  private TipoEntidad tipoEntidad;
  private Provincia provincia;
  private Departamento departamento;
  private Municipio municipio;
}
