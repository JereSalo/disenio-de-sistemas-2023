package test.domain.usuarios;

import domain.usuarios.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsuariosMainTest {
  private Usuario usuarioGenerico;

  @BeforeEach
  public void init(){
    this.usuarioGenerico = new Usuario("Juan123","123","");
  }

  @Test
  @DisplayName("Usuario algo")
  public void usuarioTest(){
    Assertions.assertTrue(usuarioGenerico.getPassword() == "123");
  }

}
