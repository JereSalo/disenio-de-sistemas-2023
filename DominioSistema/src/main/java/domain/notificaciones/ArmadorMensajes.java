package domain.notificaciones;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;

public class ArmadorMensajes {
    public String armarMensaje(Incidente... incidentes) {
        String mensaje = "";
        for (Incidente incidente : incidentes) {
            if(incidente.getFechaDeCierre() == null){
                mensaje += incidente.getPrestacionDeServicio().getServicio().getDescripcion() + " fuera de funcionamiento en " + incidente.getEstablecimiento() + " de " + incidente.getEntidad() + "\n";
            }
            else{
                mensaje += incidente.getPrestacionDeServicio().getServicio().getDescripcion() + " regresa al funcionamiento habitual en " + incidente.getEstablecimiento() + " de " + incidente.getEntidad() + "\n";
            }
        }
        return mensaje;
    }
}
