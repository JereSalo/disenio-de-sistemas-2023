package domain.servicios.georef;

import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeProvincias;
import domain.servicios.georef.entidades.ListadoDeMunicipios;

import java.io.IOException;

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
      System.out.println(listadoDeDepartamentos.departamentos.get(i).nombre + ") " + listadoDeDepartamentos.departamentos.get(i).id + " Pertenece a Provincia con ID " + listadoDeDepartamentos.departamentos.get(i).provincia_id);
    }
  }

  public static void mostrarMunicipios() throws  IOException {
    Georef georefAPI = Georef.getInstancia();
    ListadoDeMunicipios listadoDeMunicipios = georefAPI.obtenerTodosLosMunicipios();

    for (int i = 0; i < listadoDeMunicipios.localidades.size(); i++) {
      System.out.println(listadoDeMunicipios.localidades.get(i).municipio_nombre + ") " + listadoDeMunicipios.localidades.get(i).municipio_id + " Pertenece a Departamento con ID " + listadoDeMunicipios.localidades.get(i).departamento_id);
    }

  }
}
