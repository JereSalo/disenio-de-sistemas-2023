package domain.usuarios;

import lombok.Getter;
import lombok.Setter;

public class Designado {
  @Setter @Getter
  private Usuario usuario;

  public Designado(Usuario usuario) {
    this.usuario = usuario;
  }
}
