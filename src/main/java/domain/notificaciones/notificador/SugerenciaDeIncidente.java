package domain.notificaciones.notificador;

import domain.notificaciones.formaNotificacion.SinApuros;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SugerenciaDeIncidente {
  public static void main(String[] args) {
    TimerTask tarea = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Tarea ejecutada a las " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());

        // Disparar el proceso de sugerencia de incidentes. Obtener ubicacion actual de los miembros de las comunidades y ver de notificarlos si están cerca de incidente

        // Posible solución: (No es la mejor...)
        // Para todas las comunidades
        // Para todos los miembros de la comunidad
        // Obtener ubicacion del miembro y compararla con la ubicación de los incidentes abiertos de la comunidad
        // Si la diferencia es pequeña, enviarle directamente una notificación de sugerencia de revisión.
      }
    };

    Timer timer = new Timer();

    // La tarea se ejecuta cada 5 minutos
    timer.scheduleAtFixedRate(tarea, 0, 5 * 60 * 1000);
  }
}
