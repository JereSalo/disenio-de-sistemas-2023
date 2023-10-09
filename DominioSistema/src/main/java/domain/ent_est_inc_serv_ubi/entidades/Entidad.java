package domain.ent_est_inc_serv_ubi.entidades;

import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.localizacion.Localizacion;
import domain.Persistente;

import domain.usuarios.OrganismoDeControl;
import domain.usuarios.PrestadoraDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

  public Entidad(){
    this.establecimientos = new ArrayList<>();
  }

  public Entidad(String nombre, TipoEntidad tipoEntidad) {
    this.nombre = nombre;
    this.tipoEntidad = tipoEntidad;
    this.establecimientos = new ArrayList<>();
  }

  public void addEstablecimiento(Establecimiento nuevoEstablecimiento){
    this.establecimientos.add(nuevoEstablecimiento);
  }
}
