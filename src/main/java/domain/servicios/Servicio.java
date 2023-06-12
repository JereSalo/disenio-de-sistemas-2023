package domain.servicios;

import lombok.Getter;
import lombok.Setter;

public class Servicio {
    @Setter @Getter
    private String descripcion;

    public Servicio(String descripcion){
        this.descripcion = descripcion;
    }

}
