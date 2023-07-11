package domain.rankings.rankings;

import domain.repositorios.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;

import java.util.List;

public interface Ranking {
  void calcularValoresAsociados(List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking);

}
