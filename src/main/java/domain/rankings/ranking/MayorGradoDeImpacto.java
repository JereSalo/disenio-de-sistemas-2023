package domain.rankings.ranking;

import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.List;

public class MayorGradoDeImpacto extends Ranking{
  protected void calcularListaValorRanking (List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking){
    repoDeSujetosRanking.incidentes.forEach(incidente -> listaValorRanking.add(new ValorRankingEntidad(entidad, calcularValorIncidente(incidente))));
  }

  private float calcularValorIncidente(Incidente incidente){

  }
}
