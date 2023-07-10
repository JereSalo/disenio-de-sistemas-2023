package domain.rankings.valorRanking;

import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

public class ValorRankingIncidente implements ValorRanking{
  private Incidente incidente;
  @Getter @Setter
  public float valor;

  public ValorRankingIncidente(Incidente incidente, float valor){
    this.incidente = incidente;
    this.valor = valor;
  }
  public String getNombreSujeto(){
    return "incidente";
  }
}
