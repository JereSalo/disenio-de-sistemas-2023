package domain.ent_est_inc_serv_ubi.establecimientos;

import java.util.ArrayList;
import java.util.List;
import domain.Persistente;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;
import lombok.Getter;
import lombok.Setter;

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
        this.prestaciones = new ArrayList<>();
    }

    public Establecimiento(String nombre, TipoEstablecimiento tipoEstablecimiento, Entidad entidad) {
        this.nombre = nombre;
        this.tipoEstablecimiento = tipoEstablecimiento;
        this.entidad = entidad;
        this.prestaciones = new ArrayList<>();
    }

    public void addPrestacionServicio(PrestacionServicio prestacionServicio){
        this.prestaciones.add(prestacionServicio);
    }
}
