package domain.informes;

import domain.repositorios.RepoDeResultadosRankings;
import domain.rankings.valorRanking.ValorRanking;

import java.util.List;


public class GeneradorDeInforme {

    public Informe generarInforme(){
        Informe informe = new Informe();
        List<List<ValorRanking>> resultadosRankings =   RepoDeResultadosRankings.getResultadosRankings();

        // Guardamos asi en la lista de resultadosRankings:
            // 1. Mayor promedio
            // 2. Mayor cant incidentes
            // 3. Mayor impacto
        this.crearParrafo(informe, "Ranking mayor promedio", resultadosRankings.get(0));
        this.crearParrafo(informe, "Ranking mayor cantidad de incidentes", resultadosRankings.get(1));
        this.crearParrafo(informe, "Ranking mayor impacto", resultadosRankings.get(2));


        return informe;
    }


    private void crearParrafo(Informe informe, String titulo, List<ValorRanking> ranking){
        Parrafo parrafo = new Parrafo(titulo);

        ranking.forEach(valor -> parrafo.agregarAParrafo(valor));

        informe.agregarParrafo(parrafo);
    }
}
