package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.usuarios.Persona;

public abstract class Notificador {
  abstract protected void notificar(Persona persona, String mensaje);

  public void notificarIncidentes(Persona persona, Incidente... incidentes){
    String mensaje = this.armarMensaje(incidentes);
    this.notificar(persona, mensaje);
  }

  private String armarMensaje(Incidente... incidentes) {
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
