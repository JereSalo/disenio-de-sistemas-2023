package servicio;

import servicio.entidades.Comunidad;
import servicio.entidades.Incidente;
import servicio.entidades.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sincronizador {
    public void sincronizar() {
        Datos datos = Datos.getInstance();

        // Sincronizo Creador y Cerrador para que apunten directamente a usuarios y también las fechas de creación y cierre para que sean LocalDateTime
        for (Incidente incidente : datos.getIncidentes()) {
            int idCreador = incidente.getCreadorUsuarioId();
            int idCerrador = incidente.getCerradorUsuarioId();

            // Busco el usuario con ese ID
            Usuario creador = datos.obtenerUsuarioPorID(idCreador);
            Usuario cerrador = datos.obtenerUsuarioPorID(idCerrador);

            incidente.setCreador(creador);
            incidente.setCerrador(cerrador);

            // Ahora también tengo que cambiar las fecha de creación y cierre de los incidentes a LocalDateTime
            // Tengo que convertir de String en formato ISO 8601 a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

            LocalDateTime localDateTimeCreacion = LocalDateTime.parse(incidente.getDatetimeCreacion(), formatter);
            incidente.setFechaCreacion(localDateTimeCreacion);

            LocalDateTime localDateTimeCierre = LocalDateTime.parse(incidente.getDatetimeCierre(), formatter);
            incidente.setFechaCierre(localDateTimeCierre);
        }

        // Ahora tengo que sincronizar que comunidades apunten directamente a las clases de usuarios en vez de a los IDs
        for (Comunidad comunidad : datos.getComunidades()) {
            for(int idUsuario : comunidad.getUsuariosQueLaComponen()){
                Usuario usuario = datos.obtenerUsuarioPorID(idUsuario);
                comunidad.agregarUsuario(usuario);
            }
        }

    }
}
