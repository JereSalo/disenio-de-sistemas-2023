package domain.csv;

import domain.Gestores.GestorEntidades;
import domain.Gestores.TipoGestor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ParserDatos {


    private GestorEntidades parsearLinea(String[] linea) {
        TipoGestor tipo = TipoGestor.valueOf(linea[0]);
        String nombre = linea[1];

        //System.out.println("Nombre: " + nombre + ", tipo: " + tipo);

        return new GestorEntidades(nombre,tipo);
    }

    private List<GestorEntidades> parsearArchivo(List<String[]> lineas){
        return lineas.stream().skip(1).map(this::parsearLinea).collect(Collectors.toList());
    }

    public List<GestorEntidades> getDatos(String path, Character separator) {
        LectorCSV lector = new LectorCSV(path, separator);
        List<String[]> lineas = lector.leerArchivo();

        return parsearArchivo(lineas);
    }


}