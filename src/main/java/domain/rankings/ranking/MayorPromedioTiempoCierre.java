package domain.rankings.ranking;

import domain.entidades.Entidad;
import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.ArrayList;
import java.util.List;

public class MayorPromedioTiempoCierre implements Ranking{
  public List<ValorRanking> calcularRanking(RepoDeSujetosRanking repoDeSujetosRanking){

    ArrayList<ValorRanking> valorRankingResultante = new ArrayList<ValorRanking>();
    repoDeSujetosRanking.entidades.forEach(entidad -> valorRankingResultante.add(calcularValorRanking(entidad)));
  }

  private ValorRanking calcularValorRanking(Entidad entidad){
    new ValorRankingEntidad(entidad, promedioTiempodeCierre(entidad));
  }
  private float promedioTiempodeCierre (Entidad entidad){

  }
}
