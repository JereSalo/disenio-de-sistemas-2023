package domain.validadoresAuxiliares;

import domain.algoritmo.Configuracion;
import domain.algoritmo.EstrategiaValidacion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ValidadorFrecuencia implements EstrategiaValidacion {
    private String rutaContraseniasEspaniol;
    private String rutaPeoresContrasenias;

    private List<String> listaContraseniasEspaniol;
    private List<String> listaPeoresContrasenias;

    public ValidadorFrecuencia() {
        this.rutaPeoresContrasenias = Configuracion.getRutaPeoresContrasenias();
        this.rutaContraseniasEspaniol = Configuracion.getRutaContraseniasEspaniol();

        this.listaContraseniasEspaniol = new ArrayList<>();
        this.listaContraseniasEspaniol = this.obtenerLista(rutaContraseniasEspaniol);

        this.listaPeoresContrasenias = new ArrayList<>();
        this.listaPeoresContrasenias = this.obtenerLista(rutaPeoresContrasenias);
    }

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

    @Override
    public Boolean superaValidacion(String contrasenia) {
        // Supera la validacion si la contraseña no está incluida en ninguna de las 2 listas de contraseñas más usadas
        return !listaContraseniasEspaniol.contains(contrasenia) && !listaPeoresContrasenias.contains(contrasenia);
    }
}
