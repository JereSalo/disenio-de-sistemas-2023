package domain.notificaciones;

import lombok.Getter;

public class ConfiguracionCorreoElectronico {
  @Getter
  private static String host = "smtp.gmail.com";

  @Getter
  private static Integer puerto = 587;

  @Getter
  private static String remitente = "usuario@gmail.com";

  @Getter
  private static String password = "contrasenia";
}
