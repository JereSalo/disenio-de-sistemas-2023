package domain.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SujetosRanking {
    @Setter
    @Getter
    private List<Entidad> entidades;
    
    @Setter @Getter
    private List<Incidente> incidentes;

    public SujetosRanking(List<Entidad> entidades, List<Incidente> incidentes){
        this.entidades = entidades;
        this.incidentes = incidentes;
    }
}
