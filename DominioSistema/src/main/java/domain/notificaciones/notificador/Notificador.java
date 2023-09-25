package domain.notificaciones.notificador;

import domain.ent_est_inc_serv_ubi.incidentes.Incidente;
import domain.notificaciones.ArmadorMensajes;
import domain.usuarios.Persona;

public abstract class Notificador {
  abstract protected void enviar(Persona persona, String mensaje);

  public void notificarIncidentes(Persona persona, Incidente... incidentes){
    String mensaje = new ArmadorMensajes().armarMensaje(incidentes);
    this.enviar(persona, mensaje);
  }

  public void notificarSugerencia(Persona persona, Incidente incidente){
    String mensaje = "Podría informar si el incidente del servicio " + incidente.getPrestacionDeServicio().getServicio() + " en " + incidente.getEstablecimiento() + "se encuentra resuelto? Gracias!";
    this.enviar(persona, mensaje);
  }

}
