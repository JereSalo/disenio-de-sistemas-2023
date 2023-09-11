package calculadores.comunidades;

import calculadores.Calculador;
import servicio.entidades.Comunidad;
import servicio.Datos;
import servicio.entidades.Usuario;

import java.util.List;

public class CalculadorPuntajeComunidad implements Calculador {
    private double RESTA_POR_USUARIO_CON_RESERVAS = 0.2;

    public void calcularPuntajes() {
        List<Comunidad> comunidades = Datos.getInstance().getComunidades();
        comunidades.forEach(this::calcularPuntajeComunidad);
    }

    private void calcularPuntajeComunidad(Comunidad comunidad){
        List<Usuario> usuariosComunidad = comunidad.getUsuariosClase();

        double puntajeTotal = 0;
        int cantidadUsuarios = usuariosComunidad.size();
        int usuariosConReservas = 0;
        for (Usuario usuario : usuariosComunidad) {
            puntajeTotal += usuario.getPuntajeDeConfianza();
            if(usuario.getGradoDeConfianza().equals("Con reservas"))
                usuariosConReservas++;
        }
        double promedio = puntajeTotal / cantidadUsuarios;

        double puntajeDeConfianzaComunidad = promedio - (usuariosConReservas * RESTA_POR_USUARIO_CON_RESERVAS);

        comunidad.setPuntajeDeConfianza(puntajeDeConfianzaComunidad);
    }
}
