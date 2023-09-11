package domain.rankings;

import domain.rankings.rankings.Ranking;
import domain.rankings.valorRanking.ValorRanking;
import domain.repositorios.RepoDeResultadosRankings;
import domain.repositorios.RepoDeSujetosRanking;

import java.util.ArrayList;
import java.util.List;

public class CalculadorRanking {
  private List<Ranking> rankings;
  
  private Repositorio<ResultadoRanking> repoDeResultadosRankings;
  private Repositorio<Entidad> repoDeEntidades;
  private Repositorio<Incidente> repoDeIncidentes;

  private static CalculadorRanking instancia = null;

  public static CalculadorRanking getInstance(List<Ranking> rankings){
    if (instancia == null){
      instancia = new CalculadorRanking(rankings);
    }
    return instancia;
  }

  private CalculadorRanking (List<Ranking> rankings){
    this.rankings = rankings;
    this.repoDeResultadosRankings = FactoryRepositorios.get(ResultadoRanking.class);
    this.repoDeEntidades = FactoryRepositorios.get(Entidad.class);
    this.repoDeIncidentes = FactoryRepositorios.get(Incidentes.class);
  }
  
  public void generarTodosLosRankings(){
  
    this.repoDeResultadosRankings.eliminarTodo();

    rankings.forEach(ranking -> {repoDeResultadosRankings.resultadosRankings.add(calcularRanking(ranking));});

    rankings.forEach(ranking -> {
      List<ResultadoRanking> resultadosRanking = MapperResultadoRanking.convertirAResultadoRanking(this.calcularRanking(ranking));
      
      resultadosRanking.forEach(resultado -> {
        this.repoDeResultadosRankings.agregar(resultado);
      });

    });
  }

  private List<ValorRanking> calcularRanking(Ranking ranking){
    ArrayList<ValorRanking> valorRankingResultante = new ArrayList<ValorRanking>();

    ranking.calcularValoresAsociados(valorRankingResultante, repoDeSujetosRanking);

    ordenarLista(valorRankingResultante);

    return valorRankingResultante;
  }
  private void ordenarLista(List<ValorRanking> listaValorRanking){
    listaValorRanking.sort((v1, v2) -> {return comparadorValorRanking(v1, v2);});
  }

  private int comparadorValorRanking( ValorRanking v1, ValorRanking v2){
    if(v1.getValor() == v2.getValor())
      return 0;
    else if(v1.getValor() > v2.getValor())
      return 1;
    else
      return -1;
  }


}
