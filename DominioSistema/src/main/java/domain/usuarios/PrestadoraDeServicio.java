package domain.usuarios;

import domain.ent_est_inc_serv_ubi.entidades.Entidad;
import domain.rankings.informes.Informe;
import domain.rankings.informes.ManejadorDeParrafos;
import domain.rankings.informes.Parrafo;
import domain.rankings.informes.RepoInformes;
import domain.Persistente;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.persistence.*;

@Entity
@Table (name = "PrestadoraDeServicio")
@Setter@Getter
public class PrestadoraDeServicio extends Persistente{

  @Column(name = "nombre")
  private String nombre;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "designado_id", referencedColumnName = "id")
  private Designado designado;

  @OneToMany(mappedBy = "prestadoraDeServicio", fetch = FetchType.EAGER)
  private List<Entidad> entidades;

  public PrestadoraDeServicio(String nombre) {
      this.nombre = nombre;
  }

  public PrestadoraDeServicio() {

  }

  private Informe ConsultarInforme(){
    Informe informe = RepoInformes.getInforme(); // Esta prohibida esta linea
    List<Parrafo> parrafos = informe.getParrafos();

    for(int i=0; i< parrafos.size(); i++){
      ManejadorDeParrafos manejaParrafos = new ManejadorDeParrafos();
      Parrafo parrafoFiltrado = manejaParrafos.filtrarParrafo(parrafos.get(i), this.entidades);
      parrafos.set(i,parrafoFiltrado);
    }

    Informe informeFiltrado = new Informe();
    informeFiltrado.setParrafos(parrafos);

    return informeFiltrado;
  }

}
