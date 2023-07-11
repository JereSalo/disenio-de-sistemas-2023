package domain.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import domain.establecimientos.Establecimiento;
import domain.servicios.PrestacionServicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class Incidente {
  @Setter
  @Getter
  private Entidad entidad;

  @Getter
  @Setter
  private Establecimiento establecimiento;

  @Getter
  @Setter
  private PrestacionServicio prestacionDeServicio;

  @Getter
  @Setter
  private Miembro creador;

  @Getter
  @Setter
  private Comunidad comunidad;

  @Getter
  @Setter
  private LocalDateTime fechaDeCreacion;

  @Getter
  @Setter
  private LocalDateTime fechaDeCierre;

  @Getter
  @Setter
  private String observaciones;

  public Incidente() {
    this.fechaDeCreacion = LocalDateTime.now();
    this.fechaDeCierre = null;
  }

  public void cerrar() {
    this.fechaDeCierre = LocalDateTime.now();
  }

  public boolean abierto() {
    return this.fechaDeCierre == null;
  }

  public Boolean abiertoDentroDeLasUltimas24Horas(){
    int diferenciaConFechaActualEnHoras = Math.round(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - this.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC)) / 3600;

    return diferenciaConFechaActualEnHoras < 24;
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