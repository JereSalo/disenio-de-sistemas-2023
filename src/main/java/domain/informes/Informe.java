package domain.informes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Informe {

    @Setter @Getter
    private List<Parrafo> parrafos;

    public Informe(){

    }
    public void agregarParrafo(Parrafo parrafo){
        this.parrafos.add(parrafo);
    }
}
