package domain.Gestores;

import domain.csv.ParserDatos;

import java.io.IOException;

public class PruebaCSV {
    public static void main(String[] args) throws IOException {

        ParserDatos parserDatos = new ParserDatos();
        parserDatos.getDatos("./src/main/resources/prueba.csv",';');

    }
}
