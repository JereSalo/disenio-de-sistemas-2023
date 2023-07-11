package domain.notificaciones.formaNotificacion;

import domain.repositorios.RepositorioSinApuros;

import java.time.LocalTime;
import java.util.*;

public class ProgramadorNotificacionesSinApuros {
  public static void main(String[] args) {
    TimerTask tarea = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Tarea ejecutada a las " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
        List<SinApuros> sinApurosANotificar = RepositorioSinApuros.getTodosLosSinApuros();

        sinApurosANotificar.removeIf(sinApuros -> !sinApuros.getHorarios().contains(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute())));

        sinApurosANotificar.forEach(sinApuros -> sinApuros.notificarIncidentes());
      }
    };

    Timer timer = new Timer();

    LocalTime horaActual = LocalTime.now();

    // Calcular el tiempo inicial hasta la siguiente media hora
    int minutosRestantes = 29 - (horaActual.getMinute() % 30);
    int segundosRestantes = 60 - horaActual.getSecond();
    long tiempoInicial = 1000 * (minutosRestantes * 60 + segundosRestantes);

    System.out.println("Tiempo inicial: " + tiempoInicial);
    System.out.println("Minutos Restantes: " + minutosRestantes);
    System.out.println("Segundos Restantes: " + segundosRestantes);

    // La tarea se ejecuta cada media hora
    timer.scheduleAtFixedRate(tarea, tiempoInicial, 30 * 60 * 1000);
  }
}
