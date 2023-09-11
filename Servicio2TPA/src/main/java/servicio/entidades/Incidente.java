package servicio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Incidente {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreadorUsuarioId() {
        return creadorUsuarioId;
    }

    public void setCreadorUsuarioId(int creadorUsuarioId) {
        this.creadorUsuarioId = creadorUsuarioId;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public String getDatetimeCreacion() {
        return datetimeCreacion;
    }

    public void setDatetimeCreacion(String datetimeCreacion) {
        this.datetimeCreacion = datetimeCreacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCerradorUsuarioId() {
        return cerradorUsuarioId;
    }

    public void setCerradorUsuarioId(int cerradorUsuarioId) {
        this.cerradorUsuarioId = cerradorUsuarioId;
    }

    public Usuario getCerrador() {
        return cerrador;
    }

    public void setCerrador(Usuario cerrador) {
        this.cerrador = cerrador;
    }

    public String getDatetimeCierre() {
        return datetimeCierre;
    }

    public void setDatetimeCierre(String datetimeCierre) {
        this.datetimeCierre = datetimeCierre;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getIdPrestacionServicio() {
        return idPrestacionServicio;
    }

    public void setIdPrestacionServicio(int idPrestacionServicio) {
        this.idPrestacionServicio = idPrestacionServicio;
    }

    public int getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        this.idComunidad = idComunidad;
    }

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
