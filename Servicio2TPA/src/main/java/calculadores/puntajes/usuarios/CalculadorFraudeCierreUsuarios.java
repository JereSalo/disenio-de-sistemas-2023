package calculadores.puntajes.usuarios;

import calculadores.puntajes.Calculador;
import servicio.entidades.Datos;
import servicio.entidades.Incidente;
import servicio.entidades.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculadorFraudeCierreUsuarios implements Calculador {
    private final int MINUTOS_FRAUDE_CIERRE = 3;
    private final double PUNTAJE_A_RESTAR = 0.2;

    public void calcularPuntajes(Datos datos) {
        List<Incidente> incidentes = datos.getIncidentes();

        // Crear un map para agrupar los incidentes por comunidad y prestación de servicio
        Map<String, List<Incidente>> incidentesAgrupados = new HashMap<>();

        // Iterar a través de la lista de incidentes y agruparlos en el map
        for (Incidente incidente : incidentes) {
            // Combina los ID de comunidad y prestación de servicio en una clave única
            String clave = incidente.getComunidad_id() + "-" + incidente.getPrestacion_servicio_id();

            // Obtén la lista de incidentes asociada a la clave o crea una nueva si no existe
            List<Incidente> incidentesEnGrupo = incidentesAgrupados.getOrDefault(clave, new ArrayList<>());

            // Agrega el incidente a la lista correspondiente
            incidentesEnGrupo.add(incidente);

            // Actualiza el map con la lista de incidentes
            incidentesAgrupados.put(clave, incidentesEnGrupo);
        }

        // Itera a través de los grupos de incidentes
        for (Map.Entry<String, List<Incidente>> entry : incidentesAgrupados.entrySet()) {
            String clave = entry.getKey();
            List<Incidente> incidentesEnGrupo = entry.getValue();

            // Para cada grupo de incidentes, itera a través de la lista y compara cada incidente con los demás
            for (int i = 0; i < incidentesEnGrupo.size(); i++) {
                for (int j = i + 1; j < incidentesEnGrupo.size(); j++) {
                    Incidente incidente1 = incidentesEnGrupo.get(i);
                    Incidente incidente2 = incidentesEnGrupo.get(j);

                    LocalDateTime fechaCreacion1 = incidente1.getDateTimeCreacion();
                    LocalDateTime fechaCierre1 = incidente1.getDateTimeCierre();
                    LocalDateTime fechaCreacion2 = incidente2.getDateTimeCreacion();
                    LocalDateTime fechaCierre2 = incidente2.getDateTimeCierre();

                    long minutosDiferencia1a2 = Math.abs(fechaCierre1.until(fechaCreacion2, java.time.temporal.ChronoUnit.MINUTES));

                    long minutosDiferencia2a1 = Math.abs(fechaCierre2.until(fechaCreacion1, java.time.temporal.ChronoUnit.MINUTES));

                    Usuario usuarioFraude = null;

                    if (minutosDiferencia1a2 < MINUTOS_FRAUDE_CIERRE) {
                        usuarioFraude = incidente1.getCerrador();
                    }

                    if (minutosDiferencia2a1 < MINUTOS_FRAUDE_CIERRE) {
                        usuarioFraude = incidente2.getCerrador();
                    }

                    if(usuarioFraude != null){
                        usuarioFraude.sumarFraudeCierre();
                        usuarioFraude.restarPuntaje(PUNTAJE_A_RESTAR);

                        // Imprimir id de usuario fraude
                        System.out.println("El usuario " + usuarioFraude.getId() + " cometio fraude de cierre");
                    }
                }
            }
        }
    }
}
