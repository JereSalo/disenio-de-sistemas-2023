package calculadores.puntajes;

import calculadores.puntajes.comunidades.CalculadorPuntajeComunidad;
import calculadores.puntajes.usuarios.CalculadorAporteUsuarios;
import calculadores.puntajes.usuarios.CalculadorFraudeAperturaUsuarios;
import calculadores.puntajes.usuarios.CalculadorFraudeCierreUsuarios;
import servicio.entidades.Datos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculadorPuntajes {
    private List<Calculador> calculadoresPuntajeUsuarios;
    private Calculador calculadorPuntajeComunidad;

    public void calcularPuntajesUsuarios(Datos datos){
        calculadoresPuntajeUsuarios.forEach(calculador -> calculador.calcularPuntajes(datos));
    }

    public void calcularPuntajeComunidades(Datos datos) {
        calculadorPuntajeComunidad.calcularPuntajes(datos);
    }

    public CalculadorPuntajes() {
        this.calculadoresPuntajeUsuarios = new ArrayList<>();
        // Aca importa mucho el orden en el que están los calculadores en la lista
        Collections.addAll(this.calculadoresPuntajeUsuarios, new CalculadorFraudeAperturaUsuarios(), new CalculadorFraudeCierreUsuarios(), new CalculadorAporteUsuarios());
        this.calculadorPuntajeComunidad = new CalculadorPuntajeComunidad();
    }
}
