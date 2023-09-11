package domain.notificaciones.formaNotificacion;

import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;

import java.time.LocalTime;
import java.util.*;

public class ProgramadorNotificacionesSinApuros {

  private static boolean coincideConHorarioActual(LocalTime horario){
    return (horario.getHour() == LocalTime.now().getHour() && horario.getMinute() == LocalTime.now().getMinute());
  }
  public static void main(String[] args) {
    TimerTask tarea = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Tarea ejecutada a las " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());

        Repositorio<HorarioSinApuros> horarioSinApurosRepositorio = FactoryRepositorios.get(HorarioSinApuros.class);

        horarioSinApurosRepositorio.obtenerTodos().forEach(horarioSinApuros -> {
          if (coincideConHorarioActual(horarioSinApuros.getHorario())){
            horarioSinApuros.getPersona().notificarIncidentesPendientes();
          }
        });
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
