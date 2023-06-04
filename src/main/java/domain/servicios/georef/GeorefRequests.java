package domain.servicios.georef;

import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeMunicipios;
import domain.servicios.georef.entidades.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefRequests {
  @GET("provincias")
  Call<ListadoDeProvincias> todasLasProvincias(@Query("campos") String campos, @Query("orden") String orden);

  @GET("departamentos")
  Call<ListadoDeDepartamentos> todosLosDepartamentos(@Query("max") int max, @Query("campos") String campos, @Query("orden") String orden, @Query("aplanar") Boolean aplanar);
  @GET("localidades")
  Call<ListadoDeMunicipios> todosLosMunicipios(@Query("max") int max, @Query("campos") String campos, @Query("orden") String orden, @Query("aplanar") Boolean aplanar);

}
