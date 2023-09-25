package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Confiable {
    @JsonProperty("grado_confianza")
    protected String gradoDeConfianza;

    @JsonProperty("inactivado")
    protected Boolean inactivado = false;

    @JsonProperty("puntaje_confianza")
    protected double puntajeDeConfianza;
    public void establecerGradoDeConfianza(String gradoDeConfianza){
        this.gradoDeConfianza = gradoDeConfianza;
        if(gradoDeConfianza.equals("No Confiable"))
            inactivado = true;
    }
}
