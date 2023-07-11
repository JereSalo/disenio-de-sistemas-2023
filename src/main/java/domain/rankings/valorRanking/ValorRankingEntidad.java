package domain.rankings.valorRanking;

import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

public class ValorRankingEntidad implements ValorRanking{
  private Entidad entidad;
  @Getter @Setter
  public float valor;

  public ValorRankingEntidad(Entidad entidad, float valor) {
    this.entidad = entidad;
    this.valor = valor;
  }

  public String getNombreSujeto(){
    return this.entidad.getNombre();
  }
}
