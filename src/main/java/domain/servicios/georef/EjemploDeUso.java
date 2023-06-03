package domain.servicios.georef;

import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeMunicipios;
import domain.servicios.georef.entidades.ListadoDeProvincias;

import java.io.IOException;
import java.util.Scanner;

public class EjemploDeUso {
  public static void main(String[] args) throws IOException {

    mostrarProvincias();
    mostrarDepartamentos();
    mostrarMunicipios();

  }

  public static void mostrarProvincias() throws  IOException{
    Georef georefAPI = Georef.getInstancia();

    ListadoDeProvincias listadoDeProvincias = georefAPI.obtenerTodasLasProvincias();

    for (int i = 0; i < listadoDeProvincias.provincias.size(); i++) {
      System.out.println(listadoDeProvincias.provincias.get(i).nombre + ") " + listadoDeProvincias.provincias.get(i).id);
    }
  }

  public static void mostrarDepartamentos() throws IOException {
    Georef georefAPI = Georef.getInstancia();

    ListadoDeDepartamentos listadoDeDepartamentos = georefAPI.obtenerTodosLosDepartamentos();

    for (int i = 0; i < listadoDeDepartamentos.departamentos.size(); i++) {
      System.out.println(listadoDeDepartamentos.departamentos.get(i).nombre + ") " + listadoDeDepartamentos.departamentos.get(i).id + " Pertenece a Provincia con ID " + listadoDeDepartamentos.departamentos.get(i).idProvincia());
    }
  }

  public static void mostrarMunicipios() throws  IOException {
    Georef georefAPI = Georef.getInstancia();

    ListadoDeMunicipios listadoDeMunicipios = georefAPI.obtenerTodosLosMunicipios();

    for (int i = 0; i < listadoDeMunicipios.municipios.size(); i++) {
      System.out.println(listadoDeMunicipios.municipios.get(i).nombre + ") " + listadoDeMunicipios.municipios.get(i).id + " Pertenece a Provincia con ID " + listadoDeMunicipios.municipios.get(i).idProvincia());
    }
  }
}
