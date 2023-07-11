package domain.comunidades;

<<<<<<< HEAD
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteBuilder;
import domain.params.AperturaIncidenteParams;
=======
import domain.servicios.PrestacionServicio;
>>>>>>> Rankings
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
<<<<<<< HEAD

  @Setter @Getter
  private List<Incidente> incidentes;

  public Comunidad(String nombre) {
    this.nombre = nombre;
  }

  public void abrirIncidente(AperturaIncidenteParams params) {
    Incidente incidente = new IncidenteBuilder()
                            .withComunidad(this).withCreador(params.getCreador())
                            .withEntidad(params.getEntidad()).withEstablecimiento(params.getEstablecimiento()).withPrestacionDeServicio(params.getPrestacionDeServicio())
                            .withObservaciones(params.getObservaciones())
                            .build();
    incidentes.add(incidente);
    informarIncidente(incidente.getCreador(), incidente);
  }

  public void cerrarIncidente(Miembro miembro, Incidente incidente) {
    incidente.cerrar();
    informarIncidente(miembro, incidente);
  }

  private void informarIncidente(Miembro miembroExcluido, Incidente incidente) {
    miembros.stream()
        .filter(miembro -> miembro != miembroExcluido)
        .forEach(miembro -> miembro.recibirIncidente(incidente));
  }

=======
  @Setter @Getter
  private List<PrestacionServicio> servicios;
>>>>>>> Rankings
}
