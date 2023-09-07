package domain.usuarios;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table (name = "Designado")
@Setter@Getter
public class Designado extends Persistente {
  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  public Designado(Usuario usuario) {
    this.usuario = usuario;
  }
}
