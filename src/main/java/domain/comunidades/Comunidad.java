package domain.comunidades;

import domain.servicios.PrestacionServicio;
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
  private List<PrestacionServicio> servicios;
}
