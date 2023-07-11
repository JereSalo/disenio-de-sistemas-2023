package domain.rankings.ranking;

import domain.entidades.Entidad;
import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.List;

public class MayorPromedioTiempoCierre extends Ranking{
  protected void calcularListaValorRanking (List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking){
    repoDeSujetosRanking.entidades.forEach(entidad -> listaValorRanking.add(new ValorRankingEntidad(entidad, calcularValorEntidad(entidad))));
  }

  private float calcularValorEntidad(Entidad entidad){

  }
}
