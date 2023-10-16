package domain.usuarios;
import io.javalin.security.RouteRole;
public enum TipoRol implements RouteRole{
  MIEMBRO,
  ADMINISTRADOR_COMUNIDAD,
  DESIGNADO,
  ADMINISTRADOR_PLATAFORMA,
  ORGANISMO_DE_CONTROL,
  PRESTADORA_DE_SERVICIO
}
