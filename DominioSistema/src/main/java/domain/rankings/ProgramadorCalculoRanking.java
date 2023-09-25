package domain.rankings;

import domain.rankings.rankings.CantidadDeIncidentes;
import domain.rankings.rankings.GradoDeImpacto;
import domain.rankings.rankings.PromedioTiempoCierre;
import domain.rankings.rankings.Ranking;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramadorCalculoRanking {

    public static void main(String[] args) {

      /*
       MODIFICAR ESTO PARA QUE ANDE CON LA PERSISTENCIA

      List<Ranking> listaRankings = new ArrayList<Ranking>();
      listaRankings.add(new PromedioTiempoCierre());
      listaRankings.add(new CantidadDeIncidentes());
      listaRankings.add(new GradoDeImpacto());

      RepoDeSujetosRanking repoDeSujetosRanking = new RepoDeSujetosRanking();

      RepoDeResultadosRankings repoDeResultadosRankings = new RepoDeResultadosRankings();

      CalculadorRanking calculadorRanking = CalculadorRanking.getInstance(repoDeResultadosRankings, listaRankings, repoDeSujetosRanking);

      TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
          calculadorRanking.generarTodosLosRankings();

          System.out.println("CalculoRanking: Tarea ejecutada a las " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
        }
      };

      Timer timer = new Timer();

      // La tarea se ejecuta cada 1 semana
      timer.scheduleAtFixedRate(tarea, 0, 7 * 24 * 60 * 60);
    }
    */
    }
  }
