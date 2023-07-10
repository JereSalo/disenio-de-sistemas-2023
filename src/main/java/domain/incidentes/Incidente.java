package domain.incidentes;

import domain.establecimientos.Establecimiento;
import domain.servicios.PrestacionServicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

public class Incidente {
    @Getter @Setter
    private PrestacionServicio prestacion;

    @Getter @Setter
    private Establecimiento establecimiento;

    @Getter @Setter
    private Persona creador;

    @Getter @Setter
    private Date fechaApertura;

    @Getter @Setter
    private Date fechaCierre;

    @Getter @Setter
    private String observaciones;

    public Incidente(PrestacionServicio prestacion, Establecimiento establecimiento, Persona creador){
        this.prestacion = prestacion;
        this.establecimiento = establecimiento;
        this.creador = creador;
        this.fechaCierre = null;
    }


}
