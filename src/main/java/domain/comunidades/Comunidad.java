package domain.comunidades;

import domain.Persistente;
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteBuilder;
import domain.params.AperturaIncidenteParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "Comunidad")
@Setter
@Getter
public class Comunidad extends Persistente {

  @Column(name = "nombre")
  private String nombre;

  @OneToMany(mappedBy = "comunidad")
  private List<Administrador> administradores;

  @OneToMany(mappedBy = "comunidad")
  private List<Miembro> miembros;

  @OneToMany(mappedBy = "comunidad")
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

  public long cuantosAfectados(Incidente incidente){
    return miembros.stream().filter(miembro -> miembro.estaAfectado(incidente.getPrestacionDeServicio().getServicio())).count();
  }

  private void informarIncidente(Miembro miembroExcluido, Incidente incidente) {
    miembros.stream()
        .filter(miembro -> miembro != miembroExcluido)
        .forEach(miembro -> miembro.recibirIncidente(incidente));
  }

}
