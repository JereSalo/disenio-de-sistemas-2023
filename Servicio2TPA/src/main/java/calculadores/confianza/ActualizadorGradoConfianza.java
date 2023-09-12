package calculadores.confianza;

import servicio.entidades.Comunidad;
import servicio.entidades.Confiable;
import servicio.entidades.Datos;
import servicio.entidades.Usuario;

import java.util.List;

public class ActualizadorGradoConfianza {
    private int PUNTAJE_MAXIMO_NO_CONFIABLE = 2;
    private int PUNTAJE_MAXIMO_CON_RESERVAS = 3;
    private int PUNTAJE_MAXIMO_CONFIABLE_NIVEL_1 = 5;

    public void actualizarGradosDeConfianzaUsuarios(Datos datos){
        List<Usuario> usuarios = datos.getUsuarios();
        usuarios.forEach(this::actualizarGradoConfianza);
    }

    public void actualizarGradosDeConfianzaComunidades(Datos datos){
        List<Comunidad> comunidades = datos.getComunidades();
        comunidades.forEach(this::actualizarGradoConfianza);
    }

    private void actualizarGradoConfianza(Confiable confiable){
        double puntaje = confiable.getPuntajeDeConfianza();

        confiable.establecerGradoDeConfianza(obtenerGradoConfianza(puntaje));
    }

    private String obtenerGradoConfianza(double puntaje){
        if (puntaje < PUNTAJE_MAXIMO_NO_CONFIABLE) {
            return "No Confiable";
        } else if (puntaje <= PUNTAJE_MAXIMO_CON_RESERVAS) {
            return "Con Reservas";
        } else if (puntaje <= PUNTAJE_MAXIMO_CONFIABLE_NIVEL_1) {
            return "Confiable Nivel 1";
        } else {
            return "Confiable Nivel 2";
        }
    }
}
