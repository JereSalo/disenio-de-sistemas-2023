package domain;

import lombok.Getter;
import lombok.Setter;

public class Usuario {
  //@Setter @Getter private String nombre;

  //@Setter @Getter private String apellido;

  //@Setter @Getter private String mail;

  @Setter @Getter private String username;

  @Setter @Getter private String password;

  public Usuario(String nombreusuario, String contrasenia) {
    this.username = nombreusuario;
    this.password = contrasenia;
  }
}
