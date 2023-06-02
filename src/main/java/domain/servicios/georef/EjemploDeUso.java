package domain.servicios.georef;

import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeMunicipios;
import domain.servicios.georef.entidades.ListadoDeProvincias;

import java.io.IOException;
import java.util.Scanner;

public class EjemploDeUso {
  public static void main(String[] args) throws IOException {
    Georef georef = Georef.getInstancia();
    ListadoDeProvincias listadoDeProvincias = georef.obtenerProvincias();

    listadoDeProvincias.provincias.sort((p1, p2) -> p1.id >= p2.id? 1 : -1);

    for (int i = 0; i < listadoDeProvincias.provincias.size(); i++) {
      System.out.println(listadoDeProvincias.provincias.get(i).nombre + ") " + listadoDeProvincias.provincias.get(i).id);
    }

    System.out.println("Seleccione una de las provincias ingresando su id");

    Scanner entradaScanner = new Scanner(System.in);
    int idProvinciaElegida = Integer.parseInt(entradaScanner.nextLine());

    ListadoDeMunicipios listadoDeMunicipios = georef.obtenerMunicipios(idProvinciaElegida);

    for (int i = 0; i < listadoDeMunicipios.municipios.size(); i++) {
      System.out.println(listadoDeMunicipios.municipios.get(i).nombre);
    }




  }
}
