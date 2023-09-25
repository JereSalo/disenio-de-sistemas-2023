package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;


@Getter
@Setter
public class Datos {
    // ATRIBUTOS
    @JsonProperty("usuarios")
    private List<Usuario> usuarios;

    private List<Incidente> incidentes;

    @JsonProperty("comunidades")
    private List<Comunidad> comunidades;


    // METODOS
    @JsonIgnore
    public List<Incidente> getIncidentes(){
        return incidentes;
    }

    @JsonSetter("incidentes")
    public void setIncidentes(List<Incidente> incidentes){
        this.incidentes = incidentes;
    }

    public void initializeFromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(json, Datos.class);
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
