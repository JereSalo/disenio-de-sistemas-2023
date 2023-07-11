package domain.comunidades;

import domain.incidentes.Incidente;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

public class Miembro {
  @Setter @Getter
  private Persona persona;

  public Miembro(Persona persona) {
    this.persona = persona;
  }

  public void recibirIncidente(Incidente incidente) {
    this.persona.recibirIncidente(incidente);
  }
}
