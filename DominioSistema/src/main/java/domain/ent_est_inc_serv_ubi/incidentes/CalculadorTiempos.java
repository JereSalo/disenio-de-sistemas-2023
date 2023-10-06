package domain.ent_est_inc_serv_ubi.incidentes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CalculadorTiempos {
    public static Boolean diferenciaMenor24HorasMientrasSeguiaAbierto(Incidente unIncidente, Incidente otroIncidente){
        int diferenciaFechasDeCreacionEnHoras = Math.round(unIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC) - otroIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC)) / 3600;

        int diferenciaFechaCreacionConFechaDeCierreDelOtro = Math.round(unIncidente.getFechaDeCreacion().toEpochSecond(ZoneOffset.UTC) - otroIncidente.getFechaDeCierre().toEpochSecond(ZoneOffset.UTC));

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
        LocalDate fechaLunesAnterior = LocalDate.now();

        int i = 0;

        if (fechaLunesAnterior.getDayOfWeek().getValue() == 1) i += 1;

        for(; i < 2 ; fechaLunesAnterior.minusDays(1) ){

            if (fechaLunesAnterior.getDayOfWeek().getValue() == 1) i += 1;
        }

        return fechaLunesAnterior.atStartOfDay();
    }
}
