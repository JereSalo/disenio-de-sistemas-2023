package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Usuario {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPuntajeDeConfianza() {
        return puntajeDeConfianza;
    }

    public void setPuntajeDeConfianza(double puntajeDeConfianza) {
        this.puntajeDeConfianza = puntajeDeConfianza;
    }

    public String getGradoDeConfianza() {
        return gradoDeConfianza;
    }

    public void setGradoDeConfianza(String gradoDeConfianza) {
        this.gradoDeConfianza = gradoDeConfianza;
    }

    public Boolean getInactivado() {
        return inactivado;
    }

    public void setInactivado(Boolean inactivado) {
        this.inactivado = inactivado;
    }

    public Integer getCantAperturasYCierres() {
        return cantAperturasYCierres;
    }

    public void setCantAperturasYCierres(Integer cantAperturasYCierres) {
        this.cantAperturasYCierres = cantAperturasYCierres;
    }

    public Integer getCantFraudesAperturas() {
        return cantFraudesAperturas;
    }

    public void setCantFraudesAperturas(Integer cantFraudesAperturas) {
        this.cantFraudesAperturas = cantFraudesAperturas;
    }

    public Integer getCantFraudesCierres() {
        return cantFraudesCierres;
    }

    public void setCantFraudesCierres(Integer cantFraudesCierres) {
        this.cantFraudesCierres = cantFraudesCierres;
    }

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
