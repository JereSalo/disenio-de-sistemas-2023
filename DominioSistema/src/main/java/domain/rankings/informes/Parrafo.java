package domain.rankings.informes;

import domain.rankings.valorRanking.ValorRanking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Parrafo {

    @Getter @Setter
    private String titulo;
    @Getter @Setter
    private List<ValorRanking> cuerpo;

    public Parrafo(String titulo, List<ValorRanking> ranking){
        this.titulo = titulo;
        this.cuerpo = ranking;
    }
}
