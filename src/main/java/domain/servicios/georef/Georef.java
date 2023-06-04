package domain.servicios.georef;
import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeMunicipios;
import domain.servicios.georef.entidades.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Georef {

    private static Georef instancia = null;
    private static final String urlAPI = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private Georef(){
      this.retrofit = new Retrofit.Builder().baseUrl(urlAPI).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Georef getInstancia() {
      if (instancia == null) {
        instancia = new Georef();
      }

      return instancia;
    }

    public ListadoDeProvincias obtenerTodasLasProvincias() throws IOException {
      GeorefRequests georefRequests = this.retrofit.create(GeorefRequests.class);

      Call<ListadoDeProvincias> request = georefRequests.todasLasProvincias("id,nombre", "id");
      Response<ListadoDeProvincias> response = request.execute();

      return response.body();
    }

    public ListadoDeDepartamentos obtenerTodosLosDepartamentos() throws IOException {
    GeorefRequests georefRequests = this.retrofit.create(GeorefRequests.class);


    Call<ListadoDeDepartamentos> request = georefRequests.todosLosDepartamentos(1000,"id,nombre,provincia.id", "id", true);
    Response<ListadoDeDepartamentos> response = request.execute();

    return response.body();
  }

  private ListadoDeMunicipios filtrarMunicipios(ListadoDeMunicipios listado){

    // Deberia eliminar los que tienen id = 0, ya sea id de departamento o de municipio.
    for (int i = 0; i < listado.localidades.size(); i++) {
      if (listado.localidades.get(i).municipio_id == 0 || listado.localidades.get(i).departamento_id == 0) {
        listado.localidades.remove(i);
      }
      // Borrar municipios con id repetido de la lista. Estoy recorriendo un montón de veces la lista, pero me parece la forma más sencilla por ahora.
      for (int j = 0; j < listado.localidades.size(); j++) {
        if (listado.localidades.get(i).municipio_id == listado.localidades.get(j).municipio_id && i != j) {
          listado.localidades.remove(j);
        }
      }
    }

    return listado;
  }

  public ListadoDeMunicipios obtenerTodosLosMunicipios() throws IOException {
      GeorefRequests georefRequests = this.retrofit.create(GeorefRequests.class);

      Call<ListadoDeMunicipios> request = georefRequests.todosLosMunicipios(5000, "municipio,departamento.id", "id" ,true);
      Response<ListadoDeMunicipios> response = request.execute();

      return filtrarMunicipios(response.body());
    }

}
