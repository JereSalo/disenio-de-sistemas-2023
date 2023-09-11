package calculadores.puntajes.usuarios;

import calculadores.puntajes.Calculador;
import servicio.entidades.Datos;
import servicio.entidades.Incidente;
import servicio.entidades.Usuario;

import java.util.List;

public class CalculadorAporteUsuarios implements Calculador {
    private final double PUNTAJE_A_AUMENTAR = 0.5;

    public void calcularPuntajes() {
        Datos datos = Datos.getInstance();
        List<Incidente> incidentes = datos.getIncidentes();

        // Sumo a todos los que abrieron o cerraron un incidente 1 a la variable de AperturaCierre.
        for(Incidente incidente : incidentes){
            incidente.getCreador().sumarAperturaCierre();
            incidente.getCerrador().sumarAperturaCierre();
        }

        List<Usuario> usuarios = datos.getUsuarios();

        for (Usuario usuario : usuarios) {
            int aportesValiosos = usuario.getCantAperturasYCierres() - usuario.getCantFraudesAperturas() - usuario.getCantFraudesCierres();

            if(aportesValiosos > 0){
                usuario.aumentarPuntaje(PUNTAJE_A_AUMENTAR);
                // Imprimir que el usuario hizo un aporte
                System.out.println("El usuario " + usuario.getId() + " hizo " + aportesValiosos + " aportes valiosos");
            }
        }

    }
}
