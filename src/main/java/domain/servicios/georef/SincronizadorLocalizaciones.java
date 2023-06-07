package domain.servicios.georef;

import domain.localizacion.Departamento;
import domain.localizacion.Municipio;
import domain.localizacion.Provincia;
import domain.servicios.georef.entidades.*;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SincronizadorLocalizaciones {
  private static SincronizadorLocalizaciones instancia = null;

  @Getter
  final private static List<Provincia> provincias = new ArrayList<>();
  @Getter
  final private static List<Departamento> departamentos = new ArrayList<>();
  @Getter
  final private static List<Municipio> municipios = new ArrayList<>();

  final private static Georef georefAPI = Georef.getInstancia();
  public static SincronizadorLocalizaciones getInstancia(){
    if (instancia == null){
      instancia = new SincronizadorLocalizaciones();
    }

    return instancia;
  }

  public void sincronizar() throws IOException {

    sincronizarProvincias();
    sincronizarDepartamentos();
    sincronizarMunicipios();
  }

  private void sincronizarProvincias() throws IOException{
    ListadoDeProvincias listadoDeProvinciasAPI = georefAPI.obtenerTodasLasProvincias();

    for (int i = 0; i < listadoDeProvinciasAPI.provincias.size(); i++){
      provincias.add(new Provincia(listadoDeProvinciasAPI.provincias.get(i).id, listadoDeProvinciasAPI.provincias.get(i).nombre));
    }
  }

  private void sincronizarDepartamentos() throws IOException {
    ListadoDeDepartamentos listadoDeDepartamentosAPI = georefAPI.obtenerTodosLosDepartamentos();

    for (int i = 0; i < listadoDeDepartamentosAPI.departamentos.size(); i++){
      departamentos.add(new Departamento(listadoDeDepartamentosAPI.departamentos.get(i).id, listadoDeDepartamentosAPI.departamentos.get(i).nombre, buscarProvinciaPorID(provincias, listadoDeDepartamentosAPI.departamentos.get(i).provincia_id)));
    }
  }
  private void sincronizarMunicipios() throws IOException{
    ListadoDeMunicipios listadoDeMunicipiosAPI = georefAPI.obtenerTodosLosMunicipios();

    for (int i = 0; i < listadoDeMunicipiosAPI.localidades.size(); i++){
      municipios.add(new Municipio(listadoDeMunicipiosAPI.localidades.get(i).municipio_id, listadoDeMunicipiosAPI.localidades.get(i).municipio_nombre, buscarDepartamentoPorID(departamentos, listadoDeMunicipiosAPI.localidades.get(i).departamento_id)));
    }
  }

  private Provincia buscarProvinciaPorID(List<Provincia> listadoDeProvincias, int id){
    return listadoDeProvincias.stream().filter(p -> p.getId() == id).findFirst().get();
  }

  private Departamento buscarDepartamentoPorID(List<Departamento> listadoDeDepartamentos, int id){
    return listadoDeDepartamentos.stream().filter(d -> d.getId() == id).findFirst().get();
  }
}
