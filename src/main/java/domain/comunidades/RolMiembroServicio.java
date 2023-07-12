package domain.comunidades;

import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

public class RolMiembroServicio {

    @Getter @Setter
    private Servicio servicio;

    @Getter @Setter
    private Boolean afectado;
}
