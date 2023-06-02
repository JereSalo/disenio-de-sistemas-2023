package domain.servicios.georef;

import domain.servicios.georef.entidades.ListadoDeDepartamentos;
import domain.servicios.georef.entidades.ListadoDeMunicipios;
import domain.servicios.georef.entidades.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefRequests {
  @GET("provincias")
  Call<ListadoDeProvincias> provincias();

  @GET("provincias")
  Call<ListadoDeProvincias> provincias(@Query("id") int id);

  @GET("departamentos")
  Call<ListadoDeDepartamentos> departamentos();
  @GET("departamentos")
  Call<ListadoDeDepartamentos> departamentos(@Query("id") int id);

  @GET("municipios")
  Call<ListadoDeMunicipios> municipios();

  // Creo que no debería llamarse municipios la call si vas a ponerle el id del municipio.
  // Yo pondría en vez de id de municipio el id de la provincia en este GET.
  @GET("municipios")
  Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia);

}
