package domain.rankings.valorRanking;

public class ValorRankingIncidente implements ValorRanking{
  private Incidente incidente;
  public float valor;

  public ValorRankingIncidente(Incidente incidente, float valor){
    this.incidente = incidente;
    this.valor = valor;
  }
  public Object getSujeto(){
    return incidente;
  }
}
