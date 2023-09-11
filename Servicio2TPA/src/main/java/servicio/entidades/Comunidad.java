package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Comunidad {
    @JsonProperty("id")
    private int id;

    private List<Integer> usuariosQueLaComponen;

    @JsonIgnore
    private List<Usuario> usuariosClase = new ArrayList<>();

    @JsonProperty("Puntaje de Confianza")
    private double puntajeDeConfianza;

    // Getters y setters
    @JsonProperty("Grado de Confianza")
    private String gradoDeConfianza;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Usuario> getUsuariosClase() {
        return usuariosClase;
    }

    public void setUsuariosClase(List<Usuario> usuariosClase) {
        this.usuariosClase = usuariosClase;
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

    @JsonProperty("Inactivado")
    private Boolean inactivado = false;


    @JsonProperty("Usuarios que la componen")
    public void setUsuariosQueLaComponen(List<Integer> usuariosQueLaComponen) {
        this.usuariosQueLaComponen = usuariosQueLaComponen;
    }

    @JsonIgnore
    public List<Integer> getUsuariosQueLaComponen() {
        return usuariosQueLaComponen;
    }

    public void agregarUsuario(Usuario usuario) {
        usuariosClase.add(usuario);
    }

    public void establecerGradoDeConfianza(String gradoDeConfianza){
        this.gradoDeConfianza = gradoDeConfianza;
        if(gradoDeConfianza.equals("No confiable"))
            inactivado = true;
    }
}
