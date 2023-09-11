package servicio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import servicio.entidades.Comunidad;
import servicio.entidades.Incidente;
import servicio.entidades.Usuario;

import java.io.IOException;
import java.util.List;


public class Datos {
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Comunidad> getComunidades() {
        return comunidades;
    }

    public void setComunidades(List<Comunidad> comunidades) {
        this.comunidades = comunidades;
    }

    // ATRIBUTOS
    @JsonProperty("usuarios")
    private List<Usuario> usuarios;

    private List<Incidente> incidentes;

    @JsonProperty("comunidades")
    private List<Comunidad> comunidades;


    // SINGLETON
    private static Datos instance;

    public static Datos getInstance() {
        if (instance == null) {
            instance = new Datos();
        }
        return instance;
    }


    // METODOS
    @JsonIgnore
    public List<Incidente> getIncidentes(){
        return incidentes;
    }

    @JsonSetter("incidentes")
    public void setIncidentes(List<Incidente> incidentes){
        this.incidentes = incidentes;
    }

    public static void initializeFromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        instance = objectMapper.readValue(json, Datos.class);
    }

    private Datos() {
    }

    public Usuario obtenerUsuarioPorID(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
}
