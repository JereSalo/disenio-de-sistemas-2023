package domain.notificaciones.notificador;

import lombok.Getter;

public class ConfiguracionCorreoElectronico {
  public static final String host = "smtp.gmail.com";

  public static final int puerto = 587;

  public static final String remitente = "usuario@gmail.com";

  public static final String password = "contrasenia";
}
