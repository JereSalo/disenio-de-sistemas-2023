package domain.rankings.ranking;

import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;

import java.util.List;

public interface Ranking {
  void calcularValoresAsociados(List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking);

}
