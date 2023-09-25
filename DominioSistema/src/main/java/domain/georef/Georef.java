package domain.georef;
import domain.georef.entidades.ListadoDeDepartamentos;
import domain.georef.entidades.ListadoDeMunicipios;
import domain.georef.entidades.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Georef {

    private static Georef instancia = null;
    private static final String urlAPI = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;
    private int intentosMaximos = 3;

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
      Response<ListadoDeProvincias> response = ejecutarRequest(request);

      return response.body();
    }

    public ListadoDeDepartamentos obtenerTodosLosDepartamentos() throws IOException {
      GeorefRequests georefRequests = this.retrofit.create(GeorefRequests.class);

      Call<ListadoDeDepartamentos> request = georefRequests.todosLosDepartamentos(1000,"id,nombre,provincia.id", "id", true);
      Response<ListadoDeDepartamentos> response = ejecutarRequest(request);

      return response.body();
    }



    public ListadoDeMunicipios obtenerTodosLosMunicipios() throws IOException {
      GeorefRequests georefRequests = this.retrofit.create(GeorefRequests.class);

      Call<ListadoDeMunicipios> request = georefRequests.todosLosMunicipios(5000, "municipio,departamento.id", "id" ,true);
      Response<ListadoDeMunicipios> response = ejecutarRequest(request);

      return filtrarMunicipios(response.body());
    }



    private Response ejecutarRequest(Call request) throws IOException {
      for(int i = 0; i < intentosMaximos; i++){
        Response response = request.clone().execute();
        if(response.isSuccessful()){
          return response;
        }
        System.out.println("Intento " + i + " fallido.");
      }


      throw new IOException("No se pudo obtener la respuesta deseada después de " + intentosMaximos + " intentos.");
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
}
