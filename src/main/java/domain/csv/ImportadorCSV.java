package domain.csv;

import domain.Persona;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImportadorCSV {

    public static void main(String[] args) throws IOException {
        Path rutaArchivo = Path.of("src","main","java","domain","nuevo","prueba.csv");

        Files.lines(rutaArchivo)
                .skip(1)
                .map(linea -> {
                    String[] campos = linea.split(";");
                    return new Persona(campos[0],Integer.parseInt(campos[1]),campos[2]);
                }).forEach(System.out::println);

    }

    public void importarArchivo() throws IOException {
        String rutaArchivo = "./prueba.csv";
        BufferedReader lector = null;
        String linea = "";

        try{
            lector = new BufferedReader(new FileReader(rutaArchivo));
            while((linea = lector.readLine()) != null){

                String[] fila = linea.split(";");

                for(String index:fila){
                    System.out.printf("%-10s",index);
                }
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                lector.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
