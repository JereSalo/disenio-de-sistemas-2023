package domain.usuarios;

import domain.entidades.Entidad;
import domain.informes.Informe;
import domain.informes.ManejadorDeParrafos;
import domain.informes.Parrafo;
import domain.informes.RepoInformes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PrestadoraDeServicio {
  @Setter @Getter
  private String nombre;
  @Setter @Getter
  private Usuario usuario;
  @Setter @Getter
  private Designado designado;
  @Setter @Getter
  private List<Entidad> entidades;

  public PrestadoraDeServicio(String nombre) {
      this.nombre = nombre;
  }

  private Informe ConsultarInforme(){
    Informe informe = RepoInformes.getInforme();
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
