package domain.no_tengo_idea;

public class PrestacionDeServicio {
    private Estacion estacion;
    private Servicio servicio;
    private EstadoServicio estado;

    public PrestacionDeServicio(Servicio servicio){
        this.servicio = servicio;
    }

    public String nombreServicio(Servicio servicio){
        return servicio.nombre();
    }
}
