package domain.rankings.ranking;

import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;

import java.util.List;

public interface Ranking {
  List<ValorRanking> calcularRanking(RepoDeSujetosRanking repoDeSujetosRanking);
}
