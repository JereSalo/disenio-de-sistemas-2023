package domain.rankings.rankings;

import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.rankings.SujetosRanking;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;

import java.util.List;

public class PromedioTiempoCierre implements Ranking{
  public void calcularValoresAsociados(List<ValorRanking> listaValorRanking, SujetosRanking sujetosRanking){
    sujetosRanking.getEntidades().forEach(entidad -> listaValorRanking.add(new ValorRankingEntidad(entidad, calcularValorEntidad(entidad, sujetosRanking.getIncidentes()))));
  }

  private float calcularValorEntidad(Entidad entidad, List<Incidente> listaIncidentes){
    List<Incidente> listaIncidentesDeEntidad = listaIncidentes.stream().filter(incidente -> incidente.abiertoYCerradoEnSemanaAnterior() && (incidente.getEntidad() == entidad)).toList();

    return sumarTiemposCierre(listaIncidentesDeEntidad) / listaIncidentesDeEntidad.size();
  }

  private int sumarTiemposCierre(List<Incidente> listaIncidentes){
    return listaIncidentes.stream().mapToInt(incidente -> incidente.calcularTiempoCierreIncidenteEnHoras()).sum();
  }

}
