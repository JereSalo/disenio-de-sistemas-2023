package domain.servicios.georef;

import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeMunicipios;
import domain.servicios.georef.entidades.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefRequests {
  @GET("provincias.json")
  Call<ListadoDeProvincias> todasLasProvincias();

  @GET("departamentos.json")
  Call<ListadoDeDepartamentos> todosLosDepartamentos();
  @GET("municipios.json")
  Call<ListadoDeMunicipios> todosLosMunicipios();

}
