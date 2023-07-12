package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.informes.*;
import domain.localizacion.Departamento;
import domain.localizacion.Municipio;
import domain.localizacion.Provincia;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.List;

public class Entidad {
  @Getter @Setter
  private String nombre;
  private List<Establecimiento> establecimientos;
  private TipoEntidad tipoEntidad;
  private Provincia provincia;
  private Departamento departamento;
  private Municipio municipio;

}
