package web.server;

import domain.usuarios.TipoRol;
import domain.usuarios.Usuario;

import domain.usuarios.Rol;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;

public class App {
  public static void main(String[] args) {
    cargarDatosParaTestear();
    Server.init();
  }

  private static void cargarDatosParaTestear(){


    Rol rolMiembro = new Rol("miembro", TipoRol.MIEMBRO);
    Usuario usuarioMiembro = new Usuario("salacas", "1234");

    usuarioMiembro.setRol(rolMiembro);

    Repositorio<Rol> repositorioDeRoles = FactoryRepositorios.get(Rol.class);

    repositorioDeRoles.agregar(rolMiembro);

    Repositorio<Usuario> repositorioDeUsuarios = FactoryRepositorios.get(Usuario.class);

    repositorioDeUsuarios.agregar(usuarioMiembro);
  }
}
