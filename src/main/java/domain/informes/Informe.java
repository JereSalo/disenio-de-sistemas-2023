package domain.informes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Informe {
    @Setter @Getter
    private Date fechaCreacion;

    @Setter @Getter
    private List<Parrafo> parrafos;

    public void agregarParrafo(Parrafo parrafo){
        this.parrafos.add(parrafo);
    }
}
