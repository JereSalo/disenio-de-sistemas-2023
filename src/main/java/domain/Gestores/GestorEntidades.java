package domain.Gestores;

import domain.Designado;
import domain.Entidad;
import domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class GestorEntidades {

    @Getter @Setter
    protected String nombre;

    @Getter @Setter
    protected Usuario usuario;

    @Getter @Setter
    protected Designado designado;

    @Getter @Setter
    protected List<Entidad> entidades;
}
