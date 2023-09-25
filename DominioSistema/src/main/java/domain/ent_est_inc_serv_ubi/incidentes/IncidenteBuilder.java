package domain.ent_est_inc_serv_ubi.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.ent_est_inc_serv_ubi.establecimientos.Establecimiento;
import domain.ent_est_inc_serv_ubi.servicios.PrestacionServicio;

public class IncidenteBuilder {
  private Incidente incidente;

  public IncidenteBuilder(){
    incidente = new Incidente();
  }

  public IncidenteBuilder withEntidad(Entidad entidad){
    incidente.setEntidad(entidad);
    return this;
  }

  public IncidenteBuilder withEstablecimiento(Establecimiento establecimiento){
    incidente.setEstablecimiento(establecimiento);
    return this;
  }

  public IncidenteBuilder withPrestacionDeServicio(PrestacionServicio prestacionDeServicio){
    incidente.setPrestacionDeServicio(prestacionDeServicio);
    return this;
  }

  public IncidenteBuilder withObservaciones(String observaciones){
    incidente.setObservaciones(observaciones);
    return this;
  }

  public IncidenteBuilder withComunidad(Comunidad comunidad){
    incidente.setComunidad(comunidad);
    return this;
  }

  public IncidenteBuilder withCreador(Miembro creador){
    incidente.setCreador(creador);
    return this;
  }

  public Incidente build(){
    return incidente;
  }
}