package domain.no_tengo_idea;

import java.util.ArrayList;
import java.util.List;

public class Estacion {
    private String nombre;
    private Ubicacion ubicacion;
    private List<PrestacionDeServicio> prestacionDeServicios;

    public Estacion(){
        this.prestacionDeServicios = new ArrayList<>();
    }

    private void crearServicioSiEsNecesario(Servicio servicio){
        if (!existeServicio(servicio)){
            this.prestacionDeServicios.add(new PrestacionDeServicio(servicio));
        }
    }
    
    private Boolean existeServicio(Servicio servicio){
        return this.prestacionDeServicios.stream().anyMatch(servicioActual -> servicioActual.nombreServicio() == servicio.nombre());
    }

    public void habilitarServicio(Servicio servicio){
        // TODO
        crearServicioSiEsNecesario(servicio);
        //this.prestacionDeServicios.stream().map(prestacion -> prestacion.servicio);
    }
    public void deshabilitarServicio(Servicio servicio){
        // TODO
        servicio.estado = NoDisponible;
    }
    public void eliminarServicio(Servicio servicio){
        // TODO
        prestacionDeServicios.remove(servicio);
    }

}
