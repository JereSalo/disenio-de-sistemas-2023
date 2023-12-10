package web.server;

import domain.usuarios.TipoRol;
import web.controllers.*;
import web.controllers.base.FactoryController;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
  public static void init() {

    Server.app().routes(() -> {

      // RUTAS LOGIN/REGISTER

      get("", ctx -> ctx.redirect("home"));

      get("home",  ((LoginController) FactoryController.controller("Login"))::home);

      get("login", ((LoginController) FactoryController.controller("Login"))::mostrarPantallaLogin);

      post("login", ((LoginController) FactoryController.controller("Login"))::login);

      get("logout", ((LoginController) FactoryController.controller("Login"))::logout);

      get("registrarse", ((RegistroController) FactoryController.controller("Registro"))::mostrarPantallaRegistrarse);

      post("registrarse", ((RegistroController) FactoryController.controller("Registro"))::registrarUsuario);


      // RUTAS COMUNIDADES

      get("comunidades/unirme/{id-comunidad}", ((ComunidadesController) FactoryController.controller("Comunidad"))::mostrarFormUnirseAComunidad, TipoRol.MIEMBRO);

      get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidad"))::mostrarComunidades, TipoRol.MIEMBRO);
      
      post("comunidades/unirme/{id-comunidad}", ((ComunidadesController) FactoryController.controller("Comunidad"))::unirseAComunidad, TipoRol.MIEMBRO);

      get("comunidades/{id}", ((ComunidadesController) FactoryController.controller("Comunidad"))::mostrarComunidad, TipoRol.MIEMBRO);

      post("comunidades/{id}/salir", ((ComunidadesController) FactoryController.controller("Comunidad"))::salirDeComunidad, TipoRol.MIEMBRO);



      get("organismos-de-control", ((OrganismosDeControlController) FactoryController.controller("OrganismoDeControl"))::obtenerOrganismos, TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("prestadoras-de-servicios", ((PrestadorasDeServiciosController) FactoryController.controller("PrestadoraDeServicio"))::obtenerPrestadoras, TipoRol.ADMINISTRADOR_PLATAFORMA);

      // RUTAS INCIDENTES

      get("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::mostrarFormAbrirIncidente, TipoRol.MIEMBRO);
      post("incidentes/abrir", ((IncidentesController) FactoryController.controller("Incidentes"))::abrirIncidente, TipoRol.MIEMBRO);
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::listarIncidentes);
      post("incidentes/cerrar/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::cerrarIncidente, TipoRol.MIEMBRO);


      get("establecimientos/{id-entidad}", ((IncidentesController) FactoryController.controller("Incidentes"))::getEstablecimientosDeEntidad, TipoRol.MIEMBRO);

      get("servicios/{id-establecimiento}", ((IncidentesController) FactoryController.controller("Incidentes"))::getServiciosDeEstablecimiento, TipoRol.MIEMBRO);

      get("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::mostrarFormCargaMasiva, TipoRol.ADMINISTRADOR_PLATAFORMA);

      post("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::cargar, TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("revision-incidente/{id}", ((SugerenciaRevisionController) FactoryController.controller("SugerenciaRevision"))::mostrarFormSugerenciaRevision, TipoRol.MIEMBRO);

      get("administracion/usuarios", ((AdministracionUsuariosController) FactoryController.controller("AdministracionUsuarios"))::mostrarUsuarios, TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("administracion/usuarios/{id-usuario}", ((AdministracionUsuariosController) FactoryController.controller("AdministracionUsuarios"))::mostrarDetalleUsuario, TipoRol.ADMINISTRADOR_PLATAFORMA);

      post("administracion/usuarios/{id-usuario}", ((AdministracionUsuariosController) FactoryController.controller("AdministracionUsuarios"))::actualizarDetalleUsuario, TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("administracion/comunidades", ((AdministracionComunidadesController) FactoryController.controller("AdministracionComunidades"))::mostrarComunidades, TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("administracion/comunidades/crear", ((AdministracionComunidadesController) FactoryController.controller("AdministracionComunidades"))::mostrarCreacionComunidad , TipoRol.ADMINISTRADOR_PLATAFORMA);

      post("administracion/comunidades/crear", ((AdministracionComunidadesController) FactoryController.controller("AdministracionComunidades"))::crearComunidad , TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("administracion/comunidades/{id-comunidad}", ((AdministracionComunidadesController) FactoryController.controller("AdministracionComunidades"))::mostrarDetalleComunidad , TipoRol.ADMINISTRADOR_PLATAFORMA);

      post("administracion/comunidades/{id-comunidad}", ((AdministracionComunidadesController) FactoryController.controller("AdministracionComunidades"))::actualizarDetalleComunidad , TipoRol.ADMINISTRADOR_PLATAFORMA);

      delete("administracion/comunidades/{id-comunidad}", ((AdministracionComunidadesController) FactoryController.controller("AdministracionComunidades"))::eliminarComunidad , TipoRol.ADMINISTRADOR_PLATAFORMA);

      get("rankings", ((RankingsController) FactoryController.controller("Ranking"))::mostrarRankings, TipoRol.ORGANISMO_DE_CONTROL, TipoRol.PRESTADORA_DE_SERVICIO);

    });
  };

}
