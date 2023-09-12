package domain.servicios;

import lombok.Getter;
import lombok.Setter;
import domain.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "Servicio")
@Setter
@Getter
public class Servicio extends Persistente{

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    public Servicio(String descripcion){
        this.descripcion = descripcion;
    }

}
