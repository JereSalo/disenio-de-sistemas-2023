package domain.notificaciones.notificador;

import domain.incidentes.Incidente;
import domain.usuarios.Persona;

public abstract class Notificador {
  abstract protected void enviar(Persona persona, String mensaje);

  public void notificarIncidentes(Persona persona, Incidente... incidentes){
    String mensaje = this.armarMensaje(incidentes);
    this.enviar(persona, mensaje);
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

  public void notificarSugerencia(Persona persona, Incidente incidente){
    String mensaje = "Podría informar si el incidente del servicio " + incidente.getPrestacionDeServicio().getServicio() + " en " + incidente.getEstablecimiento() + "se encuentra resuelto? Gracias!";
    this.enviar(persona, mensaje);
  }

}
