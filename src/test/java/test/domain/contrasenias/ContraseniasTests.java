package test.domain.contrasenias;

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
  public void init(){
    gestorContrasenias = new GestorContrasenias();
  }

  @Test
  @DisplayName("Usuario tiene contraseña larga y variada, por ende, segura")
  public void usuarioTieneContraseniaSegura(){
    this.usuarioGenerico = new Usuario("Juan","+bqJKcD5ftyxJNq3");

    Assertions.assertTrue(gestorContrasenias.usuarioTieneContraSegura(usuarioGenerico));
  }

  @Test
  @DisplayName("Usuario tiene contraseña corta, por ende, insegura")
  public void usuarioTieneContraseniaInsegura(){
    this.usuarioGenerico = new Usuario("Juancito","bqJKc");

    Assertions.assertFalse(gestorContrasenias.usuarioTieneContraSegura(usuarioGenerico));
  }

}
