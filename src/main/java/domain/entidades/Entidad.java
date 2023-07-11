package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.informes.GeneradorDeInforme;
import domain.informes.Informe;
import domain.informes.RepoInforme;
import domain.localizacion.Departamento;
import domain.localizacion.Municipio;
import domain.localizacion.Provincia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Entidad {
  @Getter @Setter
  private String nombre;
  private List<Establecimiento> establecimientos;
  private TipoEntidad tipoEntidad;
  private Provincia provincia;
  private Departamento departamento;
  private Municipio municipio;

  /*
  private Informe verInforme(){
    Informe informe = RepoInforme.getInforme();
    // TODO


    return informe;
  }
   */
}
