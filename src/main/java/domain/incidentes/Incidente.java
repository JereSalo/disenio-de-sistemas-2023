package domain.incidentes;

import domain.entidades.Entidad;
import domain.establecimientos.Establecimiento;
import domain.servicios.PrestacionServicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Incidente {
    @Getter @Setter
    private PrestacionServicio prestacion;

    @Getter @Setter
    private Establecimiento establecimiento;

    @Getter @Setter
    private Persona creador;
    @Getter @Setter
    private Entidad entidad;

    @Getter @Setter
    private LocalDateTime fechaApertura;

    @Getter @Setter
    private LocalDateTime fechaCierre;

    @Getter @Setter
    private String observaciones;

    public Incidente(PrestacionServicio prestacion, Establecimiento establecimiento, Persona creador){
        this.prestacion = prestacion;
        this.establecimiento = establecimiento;
        this.creador = creador;
        this.fechaCierre = null;
    }

    public Boolean abiertoDentroDeLasUltimas24Horas(){
        int diferenciaConFechaActualEnHoras = Math.round(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - this.getFechaApertura().toEpochSecond(ZoneOffset.UTC)) / 3600;

        return diferenciaConFechaActualEnHoras < 24;
    }


    public Boolean abiertoEnSemanaAnterior(){
        LocalDateTime fechaLunesAnterior = obtenerLunesAnterior();

        LocalDateTime fechaUltimoLunes = fechaLunesAnterior.plusWeeks(1);

        return (this.getFechaApertura().isAfter(fechaLunesAnterior)) && (this.getFechaApertura().isBefore(fechaUltimoLunes));
    }

    public Boolean abiertoYCerradoEnSemanaAnterior(){
        LocalDateTime fechaLunesAnterior = obtenerLunesAnterior();

        LocalDateTime fechaUltimoLunes = fechaLunesAnterior.plusWeeks(1);

        return (!this.abierto() && this.getFechaApertura().isAfter(fechaLunesAnterior)) && (this.getFechaCierre().isBefore(fechaUltimoLunes));
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
