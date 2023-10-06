package domain.ent_est_inc_serv_ubi.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import domain.Persistente;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneOffset;
import javax.persistence.*;

@Entity
@Table(name = "Incidente")
@Setter
@Getter
public class Incidente extends Persistente{
  
  @ManyToOne
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;

  @ManyToOne
  @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
  private Establecimiento establecimiento;

  @ManyToOne
  @JoinColumn(name = "prestacion_servicio_id", referencedColumnName = "id")
  private PrestacionServicio prestacionDeServicio;

  @ManyToOne
  @JoinColumn(name = "creador_id", referencedColumnName = "id")
  private Miembro creador;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cerrador_id", referencedColumnName = "id")
  private Miembro cerrador;

  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

  @Column(name = "fecha_creacion", columnDefinition = "datetime")
  private LocalDateTime fechaDeCreacion;

  @Column(name = "fecha_cierre", columnDefinition = "datetime")
  private LocalDateTime fechaDeCierre;

  @Column(name = "observaciones", columnDefinition = "text")
  private String observaciones;

  public Incidente() {
    this.fechaDeCreacion = LocalDateTime.now();
    this.fechaDeCierre = null;
    this.cerrador = null;
  }

  public void cerrar(Miembro cerrador) {

    this.cerrador = cerrador;
    this.fechaDeCierre = LocalDateTime.now();
  }

  public boolean abierto() {
    return this.fechaDeCierre == null;
  }
}