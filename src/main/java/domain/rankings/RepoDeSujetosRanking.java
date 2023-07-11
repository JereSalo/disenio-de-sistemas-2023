package domain.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RepoDeSujetosRanking {
  @Getter @Setter
  public List<Entidad> entidades;
  @Getter @Setter
  public List<Incidente> incidentes;
}
