package domain.algoritmo;

import lombok.Getter;

public class Configuracion {
  @Getter
  private static String rutaPeoresContrasenias = "./src/main/resources/10k-worst-passwords.txt";

  @Getter
  private static String rutaContraseniasEspaniol = "./src/main/resources/palabrasEspaniol.txt";
}