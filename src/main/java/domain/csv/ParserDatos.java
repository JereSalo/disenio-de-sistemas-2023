package domain.csv;

import java.util.List;
import java.util.stream.Collectors;

public class ParserDatos {
    // private TipoParser tipoParser;
    private LectorCSV lector;

    public Object parsearLinea(String[] linea) {
        String nombre = linea[0];
        String tipo = linea[1];
        // TODO
        // Agregar mas campos al CSV
        // Segun el tipo (if else) instanciar prestador u organismo
        // Hacer dos archivos .CSV??
        return null;
    }

    public List<Object> getDatos() {
        List<String[]> lineas = lector.leerArchivo();

        return lineas.stream().skip(1).map(this::parsearLinea).collect(Collectors.toList());
    }


}
