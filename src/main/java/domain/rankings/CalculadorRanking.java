package domain.rankings;

import domain.rankings.ranking.Ranking;

import java.util.List;

public class CalculadorRanking {
  private RepoDeResultadosRankings repoDeResultadosRankings;
  private List<Ranking> rankings;

  private RepoDeSujetosRanking repoDeSujetosRanking;

  public void generarRanking(){
    rankings.forEach(ranking -> {repoDeResultadosRankings.resultadosRankings.add(ranking.calcularRanking(repoDeSujetosRanking));});
  }

}
