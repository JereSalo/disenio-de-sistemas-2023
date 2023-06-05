package domain.csv;

import domain.Gestores.GestorEntidades;
import domain.Gestores.TipoGestor;
import domain.OrganismoDeControl;
import domain.PrestadoraDeServicio;

import java.util.List;
import java.util.stream.Collectors;

public class ParserDatos {
    private LectorCSV lector;

    public GestorEntidades parsearLinea(String[] linea) {
        TipoGestor tipo = TipoGestor.valueOf(linea[0]);
        String nombre = linea[1];

        switch (tipo){
            case Organismo:
                return new OrganismoDeControl(nombre);
            case Prestadora:
                return new PrestadoraDeServicio(nombre);
        }
        return null;
    }

    public List<GestorEntidades> getDatos() {
        List<String[]> lineas = lector.leerArchivo();

        return lineas.stream().skip(1).map(this::parsearLinea).collect(Collectors.toList());
    }


}
