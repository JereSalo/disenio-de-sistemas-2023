package domain.rankings.ranking;

import domain.entidades.Entidad;
import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.ArrayList;
import java.util.List;

public abstract class Ranking {
  public List<ValorRanking> calcularRanking(RepoDeSujetosRanking repoDeSujetosRanking){
    ArrayList<ValorRanking> valorRankingResultante = new ArrayList<ValorRanking>();

    calcularListaValorRanking(valorRankingResultante, repoDeSujetosRanking);

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

  abstract protected void calcularListaValorRanking (List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking);

}
