package domain.Organizaciones;

import lombok.Getter;
import lombok.Setter;

public class Persona {

    @Setter @Getter
    private int edad;

    @Setter @Getter
    private String nombre;

    @Setter @Getter
    private String sexo;

    public Persona(String nombre, int edad, String sexo) {
        this.edad = edad;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "Nombre: " + nombre +
                "Sexo:" + sexo +
                "Edad: " + edad +
                "}";
    }
}
