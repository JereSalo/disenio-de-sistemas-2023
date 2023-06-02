package domain;

public class Servicio {
    private String descripcion;
    public EstadoServicio estado;
    public Servicio(String descripcion, EstadoServicio estado){
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
