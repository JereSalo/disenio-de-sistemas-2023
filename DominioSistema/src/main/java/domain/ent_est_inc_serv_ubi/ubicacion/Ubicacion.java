package domain.ent_est_inc_serv_ubi.ubicacion;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ubicacion")
@Setter
@Getter
public class Ubicacion extends Persistente {
    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;
}
