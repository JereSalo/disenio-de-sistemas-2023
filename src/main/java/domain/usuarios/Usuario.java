package domain.usuarios;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table (name = "Usuario")
@Setter@Getter
public class Usuario extends Persistente {

  @Column(name = "mail")
  private String mail;

  @Column(name = "telefono")
  private String telefono;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  public Usuario(String nombreusuario, String contrasenia) {
    this.username = nombreusuario;
    this.password = contrasenia;
  }
}
