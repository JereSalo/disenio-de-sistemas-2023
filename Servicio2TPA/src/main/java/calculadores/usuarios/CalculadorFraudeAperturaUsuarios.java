package calculadores.usuarios;

import calculadores.Calculador;
import servicio.Datos;
import servicio.entidades.Incidente;
import servicio.entidades.Usuario;

import java.time.Duration;
import java.util.List;

public class CalculadorFraudeAperturaUsuarios implements Calculador {
    private final int MINUTOS_FRAUDE_APERTURA = 3;
    private final double PUNTAJE_A_RESTAR = 0.2;

    public void calcularPuntajes() {
        List<Incidente> incidentes = Datos.getInstance().getIncidentes();

        // Filtro incidentes que tengan diferencia de creacion y cerrado menor a X minutos.
        List<Incidente> incidentes_fraudulentos = incidentes.stream()
                .filter(incidente -> Duration.between(incidente.getFechaCreacion(), incidente.getFechaCierre()).toMinutes() < MINUTOS_FRAUDE_APERTURA).toList();

        // Para cada creador de esos incidentes filtrados sumar un fraude y ademas restarle el puntaje correspondiente.
        incidentes_fraudulentos.forEach(incidente -> {
            Usuario creadorFraude = incidente.getCreador();
            creadorFraude.sumarFraudeApertura();
            creadorFraude.restarPuntaje(PUNTAJE_A_RESTAR);
            // Imprimir id del creador fraudulento
            System.out.println("El usuario " + creadorFraude.getId() + " cometio fraude de creacion");
        });

    }
}
