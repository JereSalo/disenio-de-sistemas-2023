package domain.comunidades;

import domain.Persistente;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table (name = "RolMiembroServicio")
@Setter
@Getter
public class RolMiembroServicio extends Persistente {

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    @Column( name = "afectado")
    private Boolean afectado;
}
