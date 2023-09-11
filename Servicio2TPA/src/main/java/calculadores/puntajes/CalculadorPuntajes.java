package calculadores.puntajes;

import calculadores.puntajes.comunidades.CalculadorPuntajeComunidad;
import calculadores.puntajes.usuarios.CalculadorAporteUsuarios;
import calculadores.puntajes.usuarios.CalculadorFraudeAperturaUsuarios;
import calculadores.puntajes.usuarios.CalculadorFraudeCierreUsuarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculadorPuntajes {
    private List<Calculador> calculadoresPuntajeUsuarios;
    private Calculador calculadorPuntajeComunidad;

    public void calcularPuntajesUsuarios(){
        calculadoresPuntajeUsuarios.forEach(Calculador::calcularPuntajes);
    }

    public void calcularPuntajeComunidades() {
        calculadorPuntajeComunidad.calcularPuntajes();
    }

    public CalculadorPuntajes() {
        this.calculadoresPuntajeUsuarios = new ArrayList<>();
        // Aca importa mucho el orden en el que están los calculadores en la lista
        Collections.addAll(this.calculadoresPuntajeUsuarios, new CalculadorFraudeAperturaUsuarios(), new CalculadorFraudeCierreUsuarios(), new CalculadorAporteUsuarios());
        this.calculadorPuntajeComunidad = new CalculadorPuntajeComunidad();
    }
}
