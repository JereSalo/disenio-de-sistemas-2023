package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.informes.*;
import domain.localizacion.Localizacion;
import domain.Persistente;

import domain.servicios.PrestacionServicio;
import domain.usuarios.OrganismoDeControl;
import domain.usuarios.PrestadoraDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Entidad")
@Setter
@Getter
public class Entidad extends Persistente{

  @Column(name = "nombre")
  private String nombre;

  @OneToMany(mappedBy = "entidad")
  private List<Establecimiento> establecimientos;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoEntidad tipoEntidad;
  
  @OneToOne
  @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
  private Localizacion localizacion;

  @ManyToOne
  @JoinColumn(name = "prestadora_de_servicio_id", referencedColumnName = "id")
  private PrestadoraDeServicio prestadoraDeServicio;

  @ManyToOne
  @JoinColumn(name = "organismo_de_control_id", referencedColumnName = "id")
  private OrganismoDeControl organismoDeControl;

}
