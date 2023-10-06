package domain.rankings.rankings;

import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.rankings.SujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.List;

import static domain.ent_est_inc_serv_ubi.incidentes.CalculadorTiempos.abiertoEnSemanaAnterior;
import static domain.ent_est_inc_serv_ubi.incidentes.CalculadorTiempos.diferenciaMenor24HorasMientrasSeguiaAbierto;

public class CantidadDeIncidentes implements Ranking{
  public void calcularValoresAsociados(List<ValorRanking> listaValorRanking, SujetosRanking sujetosRanking){
    sujetosRanking.getEntidades().forEach(entidad -> listaValorRanking.add(new ValorRankingEntidad(entidad, calcularValorEntidad(entidad, sujetosRanking.getIncidentes()))));
  }

  private float calcularValorEntidad(Entidad entidad, List<Incidente> listaIncidentes){
      return listaIncidentes.stream().filter(incidente -> (abiertoEnSemanaAnterior(incidente) && (incidente.getEntidad() == entidad))).filter(incidente -> !incidenteDuplicado(incidente, listaIncidentes)).count();
  }
  private Boolean incidenteDuplicado(Incidente incidente, List<Incidente> listaIncidentes){
    return listaIncidentes.stream().anyMatch(incidenteActual -> diferenciaMenor24HorasMientrasSeguiaAbierto(incidente,incidenteActual));
  }
}

