package domain.rankings;
import domain.Persistente;
import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ResultadoRanking")
@Setter
@Getter
public class ResultadoRanking extends Persistente{

  @Column (name = "id_ranking")
  private Long idRanking;

  @Column (name = "posicion")
  private Long posicion;

  @ManyToOne
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;

  @ManyToOne
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Incidente incidente;

  @Column (name = "valorPosicion")
  private float valorPosicion;

  public ResultadoRanking(){
    
  }
}