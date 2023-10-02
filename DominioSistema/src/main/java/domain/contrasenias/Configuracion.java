package domain.contrasenias;

import lombok.Getter;

public class Configuracion {
  @Getter
  private static String rutaPeoresContrasenias = "./src/main/resources/domain/10k-worst-passwords.txt";

  @Getter
  private static String rutaContraseniasEspaniol = "./src/main/resources/domain/palabrasEspaniol.txt";
}