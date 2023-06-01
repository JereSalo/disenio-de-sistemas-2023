package domain;

import domain.servicios.georef.Georef;
import domain.servicios.georef.entidades.ListadoDeDepartamentos;

import java.io.IOException;

public class Main {

    public static void main(String[] argc) throws IOException {

        Georef georefAPI = Georef.getInstancia();

        ListadoDeDepartamentos listadoDedepartamentos = georefAPI.getDepartamento(22112);

        System.out.println("Nombre: " + listadoDedepartamentos.departamentos.get(0).nombre);
    }
}
