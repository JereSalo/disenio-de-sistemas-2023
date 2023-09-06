package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.informes.*;
import domain.localizacion.Localizacion;
import domain.Persistente;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "entidad")
@Setter
@Getter
public class Entidad extends Persistente{

  @Column(name = "nombre")
  private String nombre;

  @OneToMany(mappedBy = "entidad")
  private List<Establecimiento> establecimientos;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoEntidad tipoEntidad;
  
  private 

  private
  
  private Localizacion localizacion;

}
