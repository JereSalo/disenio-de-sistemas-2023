package domain.comunidades;

import domain.incidentes.Incidente;
import domain.params.AperturaIncidenteParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Comunidad {
  @Setter @Getter
  private String nombre;
  @Setter @Getter
  private List<Administrador> administradores;
  @Setter @Getter
  private List<Miembro> miembros;

  @Setter @Getter
  private List<Incidente> incidentes;

  public Comunidad(String nombre) {
    this.nombre = nombre;
  }

  public void abrirIncidente(AperturaIncidenteParams params) {
    Incidente incidente = new Incidente(params);
    incidentes.add(incidente);
    for(Miembro miembro : miembros) {
      miembro.recibirIncidente(incidente);
    }
  }

  public void cerrarIncidente(Incidente incidente) {
    incidente.cerrar();
    for(Miembro miembro : miembros) {
      miembro.recibirIncidente(incidente);
    }
  }

}
