package domain.algoritmo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class BibliotecaAuxiliar {
  public List<String> obtenerLista(String archivo){
    InputStream archivoComoStream = this.getClass().getClassLoader().getResourceAsStream(archivo);
    var lista = new BufferedReader(new InputStreamReader(archivoComoStream)).lines().toList();
    return lista;
  }
}
