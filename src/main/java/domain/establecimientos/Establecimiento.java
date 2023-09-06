package domain.establecimientos;

import java.util.ArrayList;
import java.util.List;
import domain.Persistente;
import domain.entidades.Entidad;
import domain.servicios.PrestacionServicio;

import javax.persistence.*;

@Entity
@Table(name = "establecimiento")
@Setter 
@Getter
public class Establecimiento extends Persistente{

    @Column(name = "nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoEstablecimiento tipoEstablecimiento;

    @ManyToOne
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    private Entidad entidad;

    @OneToMany(mappedBy = "establecimiento")
    private List<PrestacionServicio> prestaciones;

    public Establecimiento(){
        this.servicios = new ArrayList<>();
    }

}
