package web.server;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.entidades.TipoEntidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.establecimientos.TipoEstablecimiento;
import domain.ent_est_inc_serv_ubi.servicios.EstadoServicio;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import domain.ent_est_inc_serv_ubi.servicios.Servicio;
import domain.notificaciones.formaNotificacion.CuandoSuceden;
import domain.notificaciones.formaNotificacion.FormaNotificacion;
import domain.notificaciones.notificador.CorreoElectronico;
import domain.usuarios.Persona;
import domain.usuarios.TipoRol;
import domain.usuarios.Usuario;

import domain.usuarios.Rol;
import org.hibernate.query.criteria.internal.MapJoinImplementor;
import persistence.repositories.FactoryRepositorios;
import persistence.repositories.Repositorio;

public class App {
  public static void main(String[] args) {
    // Si el repo de roles está vacío (cosa que no debería pasar si hay datos en el sistema) entonces cargo los datos, sino no hago nada
    if(FactoryRepositorios.get(Rol.class).obtenerTodos().isEmpty()) cargarDatosParaTestear();
    else System.out.println("No voy a cargar datos pq repo no ta vacio");

    Server.init();
  }

  private static void cargarDatosParaTestear(){
    System.out.println("Cargando datos para testear...");

    Rol rolMiembro = new Rol("miembro", TipoRol.MIEMBRO);
    Usuario usuarioMiembro = new Usuario("salacas", "1234", "1234@gmail.com");

    usuarioMiembro.setRol(rolMiembro);

    Repositorio<Rol> repositorioDeRoles = FactoryRepositorios.get(Rol.class);

    repositorioDeRoles.agregar(rolMiembro);

    Repositorio<Usuario> repositorioDeUsuarios = FactoryRepositorios.get(Usuario.class);

    repositorioDeUsuarios.agregar(usuarioMiembro);

    // Admin

    Rol rolAdmin = new Rol("admin", TipoRol.ADMINISTRADOR_PLATAFORMA);
    Usuario usuarioAdmin = new Usuario("admin", "admin");

    usuarioAdmin.setRol(rolAdmin);

    repositorioDeRoles.agregar(rolAdmin);
    repositorioDeUsuarios.agregar(usuarioAdmin);


    // Persona, Miembro y comunidad

    Persona persona = new Persona(usuarioMiembro);

    persona.setFormaNotificacion(CuandoSuceden.obtenerInstancia());

    persona.setNotificador(CorreoElectronico.obtenerInstancia());

    Comunidad comunidad = new Comunidad("Comunidad 1");

    Miembro miembro = new Miembro(persona, comunidad);

    FactoryRepositorios.get(Comunidad.class).agregar(comunidad);

    FactoryRepositorios.get(Persona.class).agregar(persona);

    FactoryRepositorios.get(Miembro.class).agregar(miembro);

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
  }
}
