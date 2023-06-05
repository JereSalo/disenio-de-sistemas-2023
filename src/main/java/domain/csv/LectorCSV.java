package domain.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LectorCSV {
    private String path;
    private Character separator;

    public LectorCSV(String path, Character separator) {
        this.path = path;
        this.separator = separator;
    }

    public List<String[]> leerArchivo() {
        FileReader fileReader = abrirArchivo();

        CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();

        try {
            return csvReader.readAll();
        } catch (IOException | CsvException e) {
            throw new IllegalArgumentException("No se pudo leer el archivo");
        }
    }

    public FileReader abrirArchivo() {
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No se pudo abrir el archivo");
        }
    }
}
