package domain.ent_est_inc_serv_ubi.servicios;

import lombok.Getter;
import lombok.Setter;
import domain.Persistente;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;

import javax.persistence.*;

@Entity
@Table(name = "PrestacionServicio")
@Setter 
@Getter
public class PrestacionServicio extends Persistente{

  @ManyToOne
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private EstadoServicio estado;

  @ManyToOne
  @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
  private Establecimiento establecimiento; 

  public PrestacionServicio(Servicio servicio, EstadoServicio estado) {
    this.servicio = servicio;
    this.estado = estado;
  }

  public PrestacionServicio() {

  }
}
