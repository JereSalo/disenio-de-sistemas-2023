package domain.contrasenias.validadoresAuxiliares;

import domain.contrasenias.Configuracion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ValidadorFrecuencia implements EstrategiaValidacion {
    private List<String> listaContraseniasEspaniol;
    private List<String> listaPeoresContrasenias;

    public ValidadorFrecuencia() {
        this.listaContraseniasEspaniol = this.obtenerLista(Configuracion.getRutaContraseniasEspaniol());
        this.listaPeoresContrasenias = this.obtenerLista(Configuracion.getRutaPeoresContrasenias());
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
