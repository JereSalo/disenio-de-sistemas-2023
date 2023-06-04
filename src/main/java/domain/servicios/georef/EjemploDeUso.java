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
      // Borrar municipios con id 0 de la lista.
      if(listadoDeMunicipios.localidades.get(i).municipio_id == 0 || listadoDeMunicipios.localidades.get(i).departamento_id == 0){
        listadoDeMunicipios.localidades.remove(i);
      }
      // Borrar municipios con id repetido de la lista. Estoy recorriendo un montón de veces la lista, pero me parece la forma más sencilla por ahora.
      for (int j = 0; j < listadoDeMunicipios.localidades.size(); j++) {
        if(listadoDeMunicipios.localidades.get(i).municipio_id == listadoDeMunicipios.localidades.get(j).municipio_id && i != j){
          listadoDeMunicipios.localidades.remove(j);
        }
      }
      System.out.println(listadoDeMunicipios.localidades.get(i).municipio_nombre + ") " + listadoDeMunicipios.localidades.get(i).municipio_id + " Pertenece a Departamento con ID " + listadoDeMunicipios.localidades.get(i).departamento_id);
    }
    // Despues habría que separar esto en diferentes métodos.


  }
}
