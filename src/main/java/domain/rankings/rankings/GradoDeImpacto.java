package domain.rankings.rankings;

import domain.incidentes.Incidente;
import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingIncidente;

import java.util.List;

public class GradoDeImpacto implements Ranking{
  public void calcularValoresAsociados(List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking){
    repoDeSujetosRanking.incidentes.forEach(incidente -> listaValorRanking.add(new ValorRankingIncidente(incidente, calcularGradoDeImpacto(incidente))));
  }

  private float calcularGradoDeImpacto(Incidente incidente){

  }
}
