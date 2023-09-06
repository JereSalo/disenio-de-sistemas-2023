package domain.localizacion;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Table (name = "Localizacion")
@Getter @Setter
public class Localizacion extends Persistente{
  
  @ManyToOne
  @JoinColumn(name = "municipio_id", referencedColumnName = "id")
  private Municipio municipio;

  @ManyToOne
  @JoinColumn(name = "departamento_id", referencedColumnName = "id")
  private Departamento departamento;

  @ManyToOne
  @JoinColumn(name = "provincia_id", referencedColumnName = "id")
  private Provincia provincia;

  public Localizacion (Municipio municipio, Departamento departamento, Provincia provincia) {
    this.municipio = municipio;
    this.departamento = departamento;
    this.provincia = provincia;
  }
}
