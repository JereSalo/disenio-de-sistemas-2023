package domain.usuarios;

import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrganismoDeControl {
  @Setter @Getter
  private String nombre;
  @Setter @Getter
  private Usuario usuario;
  @Setter @Getter
  private Designado designado;
  @Setter @Getter
  private List<Entidad> entidades;

  public OrganismoDeControl(String nombre) {
    this.nombre = nombre;
  }
}
