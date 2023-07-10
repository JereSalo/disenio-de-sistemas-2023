package domain.rankings.valorRanking;

import domain.entidades.Entidad;

public class ValorRankingEntidad implements ValorRanking{
  private Entidad entidad;
  public float valor;

  public ValorRankingEntidad(Entidad entidad, float valor) {
    this.entidad = entidad;
    this.valor = valor;
  }

  public Object getSujeto(){
    return entidad;
  }
}
