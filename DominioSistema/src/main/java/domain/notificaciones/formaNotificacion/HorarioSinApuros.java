package domain.notificaciones.formaNotificacion;

import domain.Persistente;
import javax.persistence.*;

import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;
import persistence.converters.ConverterHorarios;

import java.time.LocalTime;

@Entity
@Table (name = "HorarioSinApuros")
@Setter
@Getter
public class HorarioSinApuros extends Persistente {

  @ManyToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona persona;

  @Convert(converter = ConverterHorarios.class)
  @Column(name = "horario")
  private LocalTime horario;
}
