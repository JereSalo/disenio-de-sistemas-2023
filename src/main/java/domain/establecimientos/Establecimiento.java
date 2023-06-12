package domain.establecimientos;

import domain.servicios.Servicio;

import java.util.ArrayList;
import java.util.List;

public class Establecimiento {
    private String nombre;
    private TipoEstablecimiento tipoEstablecimiento;
    private List<Servicio> servicios;

    public Establecimiento(){
        this.servicios = new ArrayList<>();
    }



}
