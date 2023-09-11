package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Incidente {
    @JsonProperty("id")
    private int id;

    @JsonProperty("Creador (usuario_id)")
    private int creadorUsuarioId;

    @JsonIgnore
    private Usuario creador;

    @JsonProperty("Datetime Creacion")
    private String datetimeCreacion;

    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @JsonProperty("Cerrador (usuario_id)")
    private int cerradorUsuarioId;

    @JsonIgnore
    private Usuario cerrador;

    @JsonProperty("Datetime Cierre")
    private String datetimeCierre;

    @JsonIgnore
    private LocalDateTime fechaCierre;

    @JsonProperty("id prestacion de servicio")
    private int idPrestacionServicio;

    @JsonProperty("id comunidad")
    private int idComunidad;
}
