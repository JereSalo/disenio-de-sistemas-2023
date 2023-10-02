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
  }

  public void cerrar(Miembro cerrador) {

    this.cerrador = cerrador;
    this.fechaDeCierre = LocalDateTime.now();
  }

  public boolean abierto() {
    return this.fechaDeCierre == null;
  }

  public Boolean diferenciaMenor24HorasMientrasSeguiaAbierto(Incidente otroIncidente){
    int diferenciaFechasDeCreacionEnHoras = Math.round(this.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC) - otroIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC)) / 3600;

    int diferenciaFechaCreacionConFechaDeCierreDelOtro = Math.round(this.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC) - otroIncidente.getFechaDeCierre().toEpochSecond(ZoneOffset.UTC));

    return diferenciaFechasDeCreacionEnHoras > 0 && diferenciaFechasDeCreacionEnHoras < 24 && (diferenciaFechaCreacionConFechaDeCierreDelOtro > 0 || otroIncidente.abierto());
  }

  public int calcularTiempoCierreIncidenteEnHoras(){
    return Math.round(((this.getFechaDeCierre().toEpochSecond(ZoneOffset.UTC) - this.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC)) / 3600));
  }
  public Boolean abiertoEnSemanaAnterior(){
    LocalDateTime fechaLunesAnterior = obtenerLunesAnterior();

    LocalDateTime fechaUltimoLunes = fechaLunesAnterior.plusWeeks(1);

    return (this.getFechaDeCreacion().isAfter(fechaLunesAnterior)) && (this.getFechaDeCreacion().isBefore(fechaUltimoLunes));
  }

  public Boolean abiertoYCerradoEnSemanaAnterior(){
    LocalDateTime fechaLunesAnterior = obtenerLunesAnterior();

    LocalDateTime fechaUltimoLunes = fechaLunesAnterior.plusWeeks(1);

    return (!this.abierto() && this.getFechaDeCreacion().isAfter(fechaLunesAnterior)) && (this.getFechaDeCierre().isBefore(fechaUltimoLunes));
  }

  private LocalDateTime obtenerLunesAnterior(){
    LocalDate fechaLunesAnterior = LocalDate.now();

    int i = 0;

    if (fechaLunesAnterior.getDayOfWeek().getValue() == 1) i += 1;

    for(; i < 2 ; fechaLunesAnterior.minusDays(1) ){

      if (fechaLunesAnterior.getDayOfWeek().getValue() == 1) i += 1;
    }

    return fechaLunesAnterior.atStartOfDay();
  }
}