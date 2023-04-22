package test.domain.contrasenias;

import domain.algoritmo.Configuracion;
import domain.algoritmo.GestorContrasenias;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContraseniasTests {
  private Usuario usuarioGenerico;
  private GestorContrasenias gestorContrasenias;
  @BeforeEach
  public void init() {
    gestorContrasenias = new GestorContrasenias();
  }

  @Test
  @DisplayName("Usuario tiene contraseña larga y variada, por ende, segura")
  public void usuarioTieneContraseniaSegura() {
    this.usuarioGenerico = new Usuario("Juan","+bqJKcD5ftyxJNq3");

    Assertions.assertTrue(gestorContrasenias.usuarioTieneContraSegura(usuarioGenerico));
  }

  @Test
  @DisplayName("Usuario tiene contraseña corta, por ende, insegura")
  public void usuarioTieneContraseniaInsegura() {
    this.usuarioGenerico = new Usuario("Juancito","bqJKc");

    Assertions.assertFalse(gestorContrasenias.usuarioTieneContraSegura(usuarioGenerico));
  }

  @Test
  @DisplayName("Usuario tiene una contraseña de las peores 10000")
  public void usuarioTieneContraseniaComun() {
    this.usuarioGenerico = new Usuario("Enzo","chelsea");
    String archivo = Configuracion.getRutaPeoresContrasenias();
    
    Assertions.assertTrue(gestorContrasenias.perteneceAArchivo(archivo, usuarioGenerico.getPassword()));
  }

  @Test
  @DisplayName("Usuario tiene una contraseña que no está en las peores 10000")
  public void usuarioTieneContraseniaPocoComun() {
    this.usuarioGenerico = new Usuario("Enzo","fernandezz");
    String archivo = Configuracion.getRutaPeoresContrasenias();

    Assertions.assertFalse(gestorContrasenias.perteneceAArchivo(archivo, usuarioGenerico.getPassword()));
  }


}
