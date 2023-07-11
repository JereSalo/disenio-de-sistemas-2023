package domain.rankings;

import domain.rankings.rankings.Ranking;
import domain.rankings.valorRanking.ValorRanking;

import java.util.ArrayList;
import java.util.List;

public class CalculadorRanking {
  private RepoDeResultadosRankings repoDeResultadosRankings;
  private List<Ranking> rankings;
  private RepoDeSujetosRanking repoDeSujetosRanking;

  private static CalculadorRanking instancia = null;

  public static CalculadorRanking getInstance(RepoDeResultadosRankings repoDeResultadosRankings, List<Ranking> rankings, RepoDeSujetosRanking repoDeSujetosRanking){
    if (instancia == null){
      instancia = new CalculadorRanking(repoDeResultadosRankings, rankings, repoDeSujetosRanking);
    }
    return instancia;
  }

  private CalculadorRanking (RepoDeResultadosRankings repoDeResultadosRankings, List<Ranking> rankings, RepoDeSujetosRanking repoDeSujetosRanking){
    this.repoDeResultadosRankings = repoDeResultadosRankings;
    this.rankings = rankings;
    this.repoDeSujetosRanking = repoDeSujetosRanking;
  }
  public void generarTodosLosRankings(){
    rankings.forEach(ranking -> {repoDeResultadosRankings.resultadosRankings.add(calcularRanking(ranking));});
  }

  private List<ValorRanking> calcularRanking(Ranking ranking){
    ArrayList<ValorRanking> valorRankingResultante = new ArrayList<ValorRanking>();

    ranking.calcularValoresAsociados(valorRankingResultante, repoDeSujetosRanking);

    ordenarLista(valorRankingResultante);

    return valorRankingResultante;
  }
  private void ordenarLista(List<ValorRanking> listaValorRanking){
    listaValorRanking.sort((v1, v2) -> {return comparadorValorRanking(v1, v2);});
  }

  private int comparadorValorRanking( ValorRanking v1, ValorRanking v2){
    if(v1.getValor()==v2.getValor())
      return 0;
    else if(v1.getValor()>v2.getValor())
      return 1;
    else
      return -1;
  }


}
