package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.params.RecepcionIncidenteParams;
import domain.usuarios.Persona;

public final class CuandoSuceden implements FormaNotificacion{
  private static final CuandoSuceden instancia = new CuandoSuceden();

  private CuandoSuceden(){}

  public static CuandoSuceden obtenerInstancia(){
    return instancia;
  }

  public void recibirIncidente(RecepcionIncidenteParams params) {
    Incidente incidente = params.getIncidente();
    Persona persona = params.getPersona();

    persona.notificarIncidente(incidente);
  }
}
