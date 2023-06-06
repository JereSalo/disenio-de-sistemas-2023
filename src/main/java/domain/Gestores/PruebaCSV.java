package domain.Gestores;

import domain.csv.Configuracion;
import domain.csv.ParserDatos;

import java.io.IOException;

public class PruebaCSV {
    public static void main(String[] args) throws IOException {

        ParserDatos parserDatos = new ParserDatos();
        parserDatos.getDatos(Configuracion.getRutaArchivoCSV(),';');

    }
}
