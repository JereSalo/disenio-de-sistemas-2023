package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Usuario {


    @JsonProperty("id")
    private int id;

    @JsonProperty("Puntaje de confianza")
    private double puntajeDeConfianza;

    @JsonProperty("Grado de confianza")
    private String gradoDeConfianza;

    @JsonProperty("Inactivado")
    private Boolean inactivado = false;

    @JsonIgnore
    private Integer cantAperturasYCierres = 0;

    @JsonIgnore
    private Integer cantFraudesAperturas = 0;

    @JsonIgnore
    private Integer cantFraudesCierres = 0;

    public void sumarAperturaCierre() {
        cantAperturasYCierres++;
    }

    public void sumarFraudeApertura() {
        cantFraudesAperturas++;
    }

    public void sumarFraudeCierre() {
        cantFraudesCierres++;
    }

    public void aumentarPuntaje(double puntaje) {
        puntajeDeConfianza += puntaje;
    }

    public void restarPuntaje(double puntaje) {
        puntajeDeConfianza -= puntaje;
    }

    public void establecerGradoDeConfianza(String gradoDeConfianza){
        this.gradoDeConfianza = gradoDeConfianza;
        if(gradoDeConfianza.equals("No confiable"))
            inactivado = true;
    }
}
