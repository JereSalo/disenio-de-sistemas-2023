package domain.Gestores;

import domain.usuarios.Designado;
import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GestorEntidades {

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private Designado designado;

    @Getter @Setter
    private List<Entidad> entidades;

    @Getter @Setter
    private TipoGestor tipo;

    public GestorEntidades(String nombre, TipoGestor tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
}