package domain.algoritmo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BibliotecaAuxiliar {
  public List<String> obtenerLista(String archivo) {
    // Usamos Files.readAllLines para obtener la lista.
    // Bloque Try Catch por si no encuentra el archivo
    List<String> lista = null;
    try {
      lista = Files.readAllLines(Paths.get(archivo));
    } catch (IOException e) {
      System.out.println(e + " - No se pudo encontrar el archivo especificado");
      throw new RuntimeException(e);
    }

    return lista;
  }
}
