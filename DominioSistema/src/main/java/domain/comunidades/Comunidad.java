package domain.comunidades;

import domain.Persistente;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.incidentes.IncidenteBuilder;
import domain.ent_est_inc_serv_ubi.incidentes.params.AperturaIncidenteParams;
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

  @OneToMany(mappedBy = "comunidad", fetch = FetchType.EAGER)
  private List<Miembro> miembros;

  @OneToMany(mappedBy = "comunidad", fetch = FetchType.EAGER)
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

  public void cerrarIncidente(Miembro cerrador, Incidente incidente) {
    incidente.cerrar(cerrador);
    informarIncidente(cerrador, incidente);
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
