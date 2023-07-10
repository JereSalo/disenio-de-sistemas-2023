package domain.rankings;

import domain.rankings.valorRanking.ValorRanking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RepoDeResultadosRankings {
  @Getter @Setter
  public static List<List<ValorRanking>> resultadosRankings;
}
