package domain.ent_est_inc_serv_ubi.incidentes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CalculadorTiempos {
    public static Boolean diferenciaMenor24HorasMientrasSeguiaAbierto(Incidente unIncidente, Incidente otroIncidente){
        int diferenciaFechasDeCreacionEnHoras = Math.round(unIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC) - otroIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC)) / 3600;

        int diferenciaFechaCreacionConFechaDeCierreDelOtro;
        if(!otroIncidente.abierto())
            diferenciaFechaCreacionConFechaDeCierreDelOtro = Math.round(unIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC) - otroIncidente.getFechaDeCierre().toEpochSecond(ZoneOffset.UTC));
        else
            diferenciaFechaCreacionConFechaDeCierreDelOtro = 1;



        return diferenciaFechasDeCreacionEnHoras > 0 && diferenciaFechasDeCreacionEnHoras < 24 && (diferenciaFechaCreacionConFechaDeCierreDelOtro > 0 || otroIncidente.abierto());
    }

    public static int calcularTiempoCierreIncidenteEnHoras(Incidente unIncidente){
        return Math.round(((unIncidente.getFechaDeCierre().toEpochSecond(ZoneOffset.UTC) - unIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC)) / 3600));
    }
    public static Boolean abiertoEnSemanaAnterior(Incidente unIncidente){
        LocalDateTime fechaLunesAnterior = obtenerLunesAnterior();

        LocalDateTime fechaUltimoLunes = fechaLunesAnterior.plusWeeks(1);

        return (unIncidente.getFechaDeCreacion().isAfter(fechaLunesAnterior)) && (unIncidente.getFechaDeCreacion().isBefore(fechaUltimoLunes));
    }

    public static Boolean abiertoYCerradoEnSemanaAnterior(Incidente unIncidente){
        LocalDateTime fechaLunesAnterior = obtenerLunesAnterior();

        LocalDateTime fechaUltimoLunes = fechaLunesAnterior.plusWeeks(1);

        return (!unIncidente.abierto() && unIncidente.getFechaDeCreacion().isAfter(fechaLunesAnterior)) && (unIncidente.getFechaDeCierre().isBefore(fechaUltimoLunes));
    }

    private static LocalDateTime obtenerLunesAnterior(){
        LocalDateTime fechaLunesAnterior = LocalDateTime.now();

        while(fechaLunesAnterior.getDayOfWeek() != DayOfWeek.MONDAY){
            fechaLunesAnterior = fechaLunesAnterior.minusDays(1);
        }

        return fechaLunesAnterior.toLocalDate().atStartOfDay();
    }
}
