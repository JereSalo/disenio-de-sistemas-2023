package domain.informes;

import domain.rankings.CalculadorRanking;
import domain.rankings.rankings.CantidadDeIncidentes;
import domain.rankings.rankings.GradoDeImpacto;
import domain.rankings.rankings.PromedioTiempoCierre;
import domain.rankings.rankings.Ranking;
import domain.rankings.valorRanking.ValorRanking;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GeneradorDeInforme {

    public GeneradorDeInforme(){}
    public void generarInforme(List<List<ValorRanking>> resultadosRankings){
        Informe informe = new Informe();

        // Guardamos asi en la lista de resultadosRankings:
            // 1. Mayor promedio
            // 2. Mayor cant incidentes
            // 3. Mayor impacto

        this.crearParrafo(informe, "Ranking mayor promedio", resultadosRankings.get(0));
        this.crearParrafo(informe, "Ranking mayor cantidad de incidentes", resultadosRankings.get(1));
        this.crearParrafo(informe, "Ranking mayor impacto", resultadosRankings.get(2));

        RepoInformes.setInforme(informe);
    }

    private void crearParrafo(Informe informe, String titulo, List<ValorRanking> ranking){
        Parrafo parrafo = new Parrafo(titulo, ranking);
        informe.agregarParrafo(parrafo);
    }

    public static void main(String[] args) {

        List<Ranking> listaRankings = new ArrayList<Ranking>();
        listaRankings.add(new PromedioTiempoCierre());
        listaRankings.add(new CantidadDeIncidentes());
        listaRankings.add(new GradoDeImpacto());

        GeneradorDeInforme generadorDeInforme = new GeneradorDeInforme();

        CalculadorRanking calculadorRanking = CalculadorRanking.getInstance(listaRankings);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {

                generadorDeInforme.generarInforme(calculadorRanking.generarTodosLosRankings());

                System.out.println("Generar Informe: Tarea ejecutada a las " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
            }
        };

        Timer timer = new Timer();

        // La tarea se ejecuta cada 1 semana
        timer.scheduleAtFixedRate(tarea, 0, 7 * 24 * 60 * 60);
    }
}
