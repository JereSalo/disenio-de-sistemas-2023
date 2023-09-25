package domain.rankings.valorRanking;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
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
  
  public Incidente getSujeto() {
    return this.incidente;
  }

  public String getNombreSujeto(){
    return "incidente";
  }
}
