package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Comunidad extends Confiable{
    @JsonProperty("id")
    private int id;

    private List<Integer> usuariosQueLaComponen;

    @JsonIgnore
    private List<Usuario> usuariosClase = new ArrayList<>();

    // Metodos

    @JsonProperty("usuarios_id")
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
}

