package domain.usuarios;

import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrganismoDeControl {
  @Setter @Getter
  private Usuario usuario;
  @Setter @Getter
  private Designado designado;
  @Setter @Getter
  private List<Entidad> entidades;

  public OrganismoDeControl(Usuario usuario, Designado designado, List<Entidad> entidades) {
    this.usuario = usuario;
    this.designado = designado;
    this.entidades = entidades;
  }
}
