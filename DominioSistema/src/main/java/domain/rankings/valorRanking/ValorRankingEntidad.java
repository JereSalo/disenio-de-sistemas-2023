package domain.rankings.valorRanking;

import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

public class ValorRankingEntidad implements ValorRanking{

  @Getter @Setter
  private Entidad entidad;
  @Getter @Setter
  private float valor;

  public ValorRankingEntidad(Entidad entidad, float valor) {
    this.entidad = entidad;
    this.valor = valor;
  }

  public Entidad getSujeto(){
    return this.entidad;
  }

  public String getNombreSujeto(){
    return this.entidad.getNombre();
  }
}
