package domain.usuarios;
import io.javalin.security.RouteRole;
public enum TipoRol implements RouteRole{
  MIEMBRO,
  ADMINISTRADOR_COMUNIDAD,
  DELEGADO,
  ADMINISTRADOR_PLATAFORMA,
}
