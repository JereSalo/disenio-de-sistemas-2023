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

    @JsonProperty("creador_id")
    private int creador_id;

    @JsonIgnore
    private Usuario creador;

    @JsonProperty("fecha_creacion")
    private String fecha_creacion;

    @JsonIgnore
    private LocalDateTime dateTimeCreacion;

    @JsonProperty("cerrador_id")
    private int cerrador_id;

    @JsonIgnore
    private Usuario cerrador;

    @JsonProperty("fecha_cierre")
    private String fecha_cierre;

    @JsonIgnore
    private LocalDateTime dateTimeCierre;

    @JsonProperty("prestacion_servicio_id")
    private int prestacion_servicio_id;

    @JsonProperty("comunidad_id")
    private int comunidad_id;
}
