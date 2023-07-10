package domain.csv;

import domain.Gestores.TipoGestor;
import domain.usuarios.OrganismoDeControl;
import domain.usuarios.PrestadoraDeServicio;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ParserDatos {


    private OrganismoDeControl parsearLineaArchivoOrg(String[] linea) {
        String nombre = linea[0];

        //System.out.println("Nombre: " + nombre + ", tipo: " + tipo);

        return new OrganismoDeControl(nombre);
    }

    private List<OrganismoDeControl> parsearArchivoOrganismoDeControl(List<String[]> lineas){
        return lineas.stream().skip(1).map(this::parsearLineaArchivoOrg).collect(Collectors.toList());
    }

    public List<OrganismoDeControl> getDatosOrganismos(String path, Character separator) {
        LectorCSV lector = new LectorCSV(path, separator);
        List<String[]> lineas = lector.leerArchivo();

        return parsearArchivoOrganismoDeControl(lineas);
    }

    private PrestadoraDeServicio parsearLineaArchivoPrest(String[] linea) {
        String nombre = linea[0];

        //System.out.println("Nombre: " + nombre + ", tipo: " + tipo);

        return new PrestadoraDeServicio(nombre);
    }

    private List<PrestadoraDeServicio> parsearArchivoPrestadoras(List<String[]> lineas){
        return lineas.stream().skip(1).map(this::parsearLineaArchivoPrest).collect(Collectors.toList());
    }

    public List<PrestadoraDeServicio> getDatosPrestadoras(String path, Character separator) {
        LectorCSV lector = new LectorCSV(path, separator);
        List<String[]> lineas = lector.leerArchivo();

        return parsearArchivoPrestadoras(lineas);
    }


}