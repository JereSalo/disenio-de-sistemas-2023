package domain.servicios.georef;

import domain.servicios.georef.entidades.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface GeorefRequests {
  @GET("provincias")
  Call<ListadoDeProvincias> todasLasProvincias(@Query("campos") String campos, @Query("orden") String orden);

  @GET("departamentos")
  Call<ListadoDeDepartamentos> todosLosDepartamentos(@Query("max") int max, @Query("campos") String campos, @Query("orden") String orden, @Query("aplanar") Boolean aplanar);
  @GET("localidades")
  Call<ListadoDeMunicipios> todosLosMunicipios(@Query("max") int max, @Query("campos") String campos, @Query("orden") String orden, @Query("aplanar") Boolean aplanar);

}
