package domain.comunidades;

import domain.Persistente;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.incidentes.IncidenteBuilder;
import domain.ent_est_inc_serv_ubi.incidentes.params.AperturaIncidenteParams;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

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
  private Set<Miembro> miembros;

  @OneToMany(mappedBy = "comunidad", fetch = FetchType.EAGER)
  private Set<Incidente> incidentes;

  // poner un atributo que indique si está activa o no
  @Column(name = "activa")
  private boolean activa;

  public Comunidad(String nombre) {
    this.nombre = nombre;
    this.activa = true;
  }

  public Comunidad() {

  }

  public boolean esMiembro(Usuario usuario){
    return miembros.stream().anyMatch(miembro -> miembro.getUsuario() == usuario);
  }

  public long cuantosAfectados(Incidente incidente){
    return miembros.stream().filter(miembro -> miembro.estaAfectado(incidente.getPrestacionDeServicio().getServicio())).count();
  }

  public long cantMiembros(){
    return miembros.size();
  }

}
