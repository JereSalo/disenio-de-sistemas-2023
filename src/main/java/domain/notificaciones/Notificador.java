package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.usuarios.Persona;

public abstract class Notificador {
  abstract protected void notificar(Persona persona, String mensaje);
  abstract public void notificarIncidentes(Persona persona, Incidente... incidentes);

}
