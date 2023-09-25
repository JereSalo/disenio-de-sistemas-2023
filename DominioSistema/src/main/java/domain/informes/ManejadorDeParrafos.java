package domain.informes;

import domain.entidades.Entidad;

import java.util.List;

public class ManejadorDeParrafos {
    public Parrafo filtrarParrafo(Parrafo parrafo, List<Entidad> entidades){
        List<String> nombresEntidades = entidades.stream().map(entidad -> entidad.getNombre()).toList();
        parrafo.getCuerpo().stream().
                filter(valor -> nombresEntidades.contains(valor));
        return parrafo;
    }
}
