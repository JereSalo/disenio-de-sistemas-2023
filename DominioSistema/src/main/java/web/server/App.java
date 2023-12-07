package web.server;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.entidades.TipoEntidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.establecimientos.TipoEstablecimiento;
import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.ent_est_inc_serv_ubi.servicios.EstadoServicio;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import domain.ent_est_inc_serv_ubi.servicios.Servicio;
import domain.notificaciones.formaNotificacion.CuandoSuceden;
import domain.notificaciones.notificador.CorreoElectronico;
import domain.usuarios.Persona;
import domain.usuarios.TipoRol;
import domain.usuarios.Usuario;

import domain.usuarios.Rol;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;

public class App {
  public static void main(String[] args) {
    // Si el repo de roles está vacío (cosa que no debería pasar si hay datos en el sistema) entonces cargo los datos, sino no hago nada
    if (FactoryRepositorios.get(Rol.class).obtenerTodos().isEmpty()) cargarDatosParaTestear();
    else System.out.println("No voy a cargar datos pq repo no ta vacio");

    Server.init();
  }

  private static void cargarDatosParaTestear() {
    System.out.println("Cargando datos para testear...");

    cargarUsuariosDePrueba();

    // Servicio
    Servicio servicioAscensor = new Servicio("ascensor");

    FactoryRepositorios.get(Servicio.class).agregar(servicioAscensor);

    // Entidad, establecimiento, prestacion de servicio

    Entidad entidadBanco = new Entidad("Banco Nación", TipoEntidad.ORGANIZACION);

    Establecimiento establecimientoSucursal = new Establecimiento("Sucursal Belgrano", TipoEstablecimiento.SUCURSAL, entidadBanco);

    entidadBanco.addEstablecimiento(establecimientoSucursal);

    PrestacionServicio prestacionServicio = new PrestacionServicio(servicioAscensor, EstadoServicio.DISPONIBLE, establecimientoSucursal);

    establecimientoSucursal.addPrestacionServicio(prestacionServicio);

    FactoryRepositorios.get(Entidad.class).agregar(entidadBanco);

    FactoryRepositorios.get(Establecimiento.class).agregar(establecimientoSucursal);

    FactoryRepositorios.get(PrestacionServicio.class).agregar(prestacionServicio);

    Comunidad comunidad = new Comunidad("Amantes del ascensor");
    FactoryRepositorios.get(Comunidad.class).agregar(comunidad);
  }


  private static void cargarUsuariosDePrueba() {
    Usuario usuarioAdminComunidad = new Usuario("comunidad", "1234", "comunidad@gmail.com");
    Rol rolAdminComunidad = new Rol("Administrador comunidad", TipoRol.ADMINISTRADOR_COMUNIDAD);
    usuarioAdminComunidad.setRol(rolAdminComunidad);

    Usuario usuarioDesignado = new Usuario("designado", "1234", "1234@gmail.com");
    Rol rolDesignado = new Rol("Designado", TipoRol.DESIGNADO);
    usuarioDesignado.setRol(rolDesignado);

    Usuario usuarioMiembro = new Usuario("miembro", "1234", "1234@gmail.com");
    Rol rolMiembro = new Rol("Miembro", TipoRol.MIEMBRO);
    usuarioMiembro.setRol(rolMiembro);

    Usuario usuarioAdminPlat = new Usuario("admin", "admin", "admin@gmail.com");
    Rol rolAdminPlat = new Rol("Administrador plataforma", TipoRol.ADMINISTRADOR_PLATAFORMA);
    usuarioAdminPlat.setRol(rolAdminPlat);

    Usuario usuarioOrganismo = new Usuario("organismo", "organismo", "organismo@gmail.com");
    Rol rolOrganismo = new Rol("Organismo de control", TipoRol.ORGANISMO_DE_CONTROL);
    usuarioOrganismo.setRol(rolOrganismo);

    Usuario usuarioPrestadora = new Usuario("prestadora", "prestadora", "prestadora@gmail.com");
    Rol rolPrestadora = new Rol("Prestadora de servicio", TipoRol.PRESTADORA_DE_SERVICIO);
    usuarioPrestadora.setRol(rolPrestadora);

    Repositorio<Rol> repositorioDeRoles = FactoryRepositorios.get(Rol.class);
    repositorioDeRoles.agregar(rolAdminComunidad);
    repositorioDeRoles.agregar(rolDesignado);
    repositorioDeRoles.agregar(rolMiembro);
    repositorioDeRoles.agregar(rolAdminPlat);
    repositorioDeRoles.agregar(rolOrganismo);
    repositorioDeRoles.agregar(rolPrestadora);


    Repositorio<Usuario> repositorioDeUsuarios = FactoryRepositorios.get(Usuario.class);
    repositorioDeUsuarios.agregar(usuarioAdminComunidad);
    repositorioDeUsuarios.agregar(usuarioDesignado);
    repositorioDeUsuarios.agregar(usuarioMiembro);
    repositorioDeUsuarios.agregar(usuarioAdminPlat);
    repositorioDeUsuarios.agregar(usuarioOrganismo);
    repositorioDeUsuarios.agregar(usuarioPrestadora);

    // Persona, Miembro y comunidad
    Persona persona = new Persona(usuarioMiembro);

    persona.setFormaNotificacion(CuandoSuceden.obtenerInstancia());

    persona.setNotificador(CorreoElectronico.obtenerInstancia());

    Comunidad comunidad = new Comunidad("Fundamentalistas de la escalera mecánica");

    Miembro miembro = new Miembro(persona, comunidad, true);

    FactoryRepositorios.get(Comunidad.class).agregar(comunidad);

    FactoryRepositorios.get(Persona.class).agregar(persona);

    FactoryRepositorios.get(Miembro.class).agregar(miembro);
  }
}
