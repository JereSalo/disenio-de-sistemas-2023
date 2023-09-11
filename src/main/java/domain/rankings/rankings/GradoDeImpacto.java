package domain.rankings.rankings;

import domain.comunidades.Comunidad;
import domain.incidentes.Incidente;
import domain.rankings.SujetosRanking;
import domain.repositorios.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingIncidente;

import java.util.List;

public class GradoDeImpacto implements Ranking{
  public void calcularValoresAsociados(List<ValorRanking> listaValorRanking, SujetosRanking sujetosRanking){
    sujetosRanking.getIncidentes().forEach(incidente -> listaValorRanking.add(new ValorRankingIncidente(incidente, calcularGradoDeImpacto(incidente))));
  }

  private float calcularGradoDeImpacto(Incidente incidente){
    return incidente.getComunidad().cuantosAfectados(incidente);
  }
}
