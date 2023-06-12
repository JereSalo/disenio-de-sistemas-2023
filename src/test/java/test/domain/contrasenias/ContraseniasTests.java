package test.domain.contrasenias;

import domain.contrasenias.ValidadorContrasenias;
import domain.contrasenias.validadoresAuxiliares.ValidadorFrecuencia;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContraseniasTests {
  private Usuario usuarioGenerico;
  private ValidadorContrasenias validadorContrasenias;
  @BeforeEach
  public void init() {
    validadorContrasenias = new ValidadorContrasenias();
  }

  @Test
  @DisplayName("Usuario tiene contraseña larga y variada, por ende, segura")
  public void usuarioTieneContraseniaSegura() {
    this.usuarioGenerico = new Usuario("Juan","+bqJKcD5ftyxJNq3");

    var nombreUsuario = usuarioGenerico.getUsername();
    var contrasenia = usuarioGenerico.getPassword();

    Assertions.assertTrue(validadorContrasenias.usuarioTieneContraSegura(nombreUsuario, contrasenia));
  }

  @Test
  @DisplayName("Usuario tiene contraseña corta, por ende, insegura")
  public void usuarioTieneContraseniaInsegura() {
    this.usuarioGenerico = new Usuario("Juancito","bqJKc");

    var nombreUsuario = usuarioGenerico.getUsername();
    var contrasenia = usuarioGenerico.getPassword();

    Assertions.assertFalse(validadorContrasenias.usuarioTieneContraSegura(nombreUsuario, contrasenia));
  }

  @Test
  @DisplayName("Usuario tiene una contraseña de las peores 10000")
  public void usuarioTieneContraseniaComun() {
    this.usuarioGenerico = new Usuario("Enzo","chelsea");

    Assertions.assertFalse(new ValidadorFrecuencia().esValida(usuarioGenerico.getPassword()));
  }

  @Test
  @DisplayName("Usuario tiene una contraseña que no está en las peores 10000")
  public void usuarioTieneContraseniaPocoComun() {
    this.usuarioGenerico = new Usuario("Enzo","fernandezz");

    Assertions.assertTrue(new ValidadorFrecuencia().esValida(usuarioGenerico.getPassword()));
  }

  @Test
  @DisplayName("Usuario tiene contraseña que incluye su nombre, por ende, insegura")
  public void usuarioTieneContraseniaQueIncluyeSuNombre() {
    this.usuarioGenerico = new Usuario("Juancito","Juancito2003");

    var nombreUsuario = usuarioGenerico.getUsername();
    var contrasenia = usuarioGenerico.getPassword();

    Assertions.assertFalse(validadorContrasenias.usuarioTieneContraSegura(nombreUsuario, contrasenia));
  }

  @Test
  @DisplayName("Usuario tiene contraseña que incluye una secuencia de caracteres seguidos, por ende, insegura")
  public void usuarioTieneContraseniaQueIncluyeSecuenciaDeCaracteres() {
    this.usuarioGenerico = new Usuario("Juancito","afegABCvDholaX@1");

    var nombreUsuario = usuarioGenerico.getUsername();
    var contrasenia = usuarioGenerico.getPassword();

    Assertions.assertFalse(validadorContrasenias.usuarioTieneContraSegura(nombreUsuario, contrasenia));
  }

}
