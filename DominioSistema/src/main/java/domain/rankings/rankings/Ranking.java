package domain.rankings.rankings;

import domain.rankings.SujetosRanking;
import domain.rankings.valorRanking.ValorRanking;

import java.util.List;

public interface Ranking {
  void calcularValoresAsociados(List<ValorRanking> listaValorRanking, SujetosRanking sujetosRanking);

}
