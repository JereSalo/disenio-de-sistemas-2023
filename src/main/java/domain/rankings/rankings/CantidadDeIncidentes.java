package domain.rankings.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import domain.rankings.RepoDeSujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.List;

public class CantidadDeIncidentes implements Ranking{
  public void calcularValoresAsociados(List<ValorRanking> listaValorRanking, RepoDeSujetosRanking repoDeSujetosRanking){
    repoDeSujetosRanking.entidades.forEach(entidad -> listaValorRanking.add(new ValorRankingEntidad(entidad, calcularValorEntidad(entidad, repoDeSujetosRanking.getIncidentes()))));
  }

  private float calcularValorEntidad(Entidad entidad, List<Incidente> listaIncidentes){
      return listaIncidentes.stream().filter(incidente -> (incidente.abiertoEnSemanaAnterior() && (incidente.getEntidad() == entidad))).count();
  }
}

