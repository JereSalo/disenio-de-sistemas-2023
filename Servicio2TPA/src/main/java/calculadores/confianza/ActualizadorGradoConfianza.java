package calculadores.confianza;

import servicio.entidades.Comunidad;
import servicio.entidades.Datos;
import servicio.entidades.Usuario;

import java.util.List;

public class ActualizadorGradoConfianza {
    private int PUNTAJE_MAXIMO_NO_CONFIABLE = 2;
    private int PUNTAJE_MAXIMO_CON_RESERVAS = 3;
    private int PUNTAJE_MAXIMO_CONFIABLE_NIVEL_1 = 5;

    public void actualizarGradoConfianzaComunidades(){
        List<Comunidad> comunidades = Datos.getInstance().getComunidades();
        comunidades.forEach(this::actualizarGradoConfianzaComunidad);
    }

    private void actualizarGradoConfianzaComunidad(Comunidad comunidad){
        double puntaje = comunidad.getPuntajeDeConfianza();

        comunidad.establecerGradoDeConfianza(obtenerGradoConfianza(puntaje));
    }

    public void actualizarGradoConfianzaUsuarios(){
        List<Usuario> usuarios = Datos.getInstance().getUsuarios();

        usuarios.forEach(this::actualizarGradoConfianzaUsuario);
    }

    private void actualizarGradoConfianzaUsuario(Usuario usuario){
        double puntaje = usuario.getPuntajeDeConfianza();

        usuario.establecerGradoDeConfianza(obtenerGradoConfianza(puntaje));
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
