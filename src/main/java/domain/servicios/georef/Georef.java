package domain.servicios.georef;
import domain.servicios.georef.entidades.Departamento;
import domain.servicios.georef.entidades.ListadoDeDepartamentos;
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

    public ListadoDeDepartamentos getDepartamento(int id) throws IOException {
      GeorefRequests georefRequests = this.retrofit.create(GeorefRequests.class);

      Call<ListadoDeDepartamentos> request = georefRequests.departamentos(id);
      Response<ListadoDeDepartamentos> response = request.execute();

      return response.body();
    }
}
