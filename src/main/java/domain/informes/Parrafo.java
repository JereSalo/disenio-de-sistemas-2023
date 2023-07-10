package domain.informes;

import domain.rankings.valorRanking.ValorRanking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Parrafo {

    @Getter @Setter
    private String titulo;
    @Getter @Setter
    private List<String> cuerpo;
    @Getter @Setter
    private int puesto;

    public Parrafo(String titulo){
        this.titulo = titulo;
        this.puesto = 0;
    }

    public void agregarAParrafo(ValorRanking valor){
        String linea = Integer.toString(puesto) + " - " + valor.getNombreSujeto()
                + " - "  + Float.toString(valor.getValor());
        puesto++;

        cuerpo.add(linea);
    }
}
