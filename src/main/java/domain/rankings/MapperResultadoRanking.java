package domain.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import domain.rankings.valorRanking.ValorRanking;
import domain.rankings.valorRanking.ValorRankingEntidad;
import domain.rankings.valorRanking.ValorRankingIncidente;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

// ResultadoRanking esta en la base de datos
// ValorRanking se usa a nivel codigo
public class MapperResultadoRanking {

    public static ValorRanking convertirAValorRanking(ResultadoRanking resultadoRanking){
        ValorRanking valorRanking = MapperResultadoRanking.valorRankingFactory(resultadoRanking);
        return valorRanking;
    }

    private static ValorRanking valorRankingFactory(ResultadoRanking resultadoRanking){
        ValorRanking valor;

        if(resultadoRanking.getEntidad() == null)
            valor = new ValorRankingIncidente(resultadoRanking.getIncidente(), resultadoRanking.getValorPosicion());
        else
            valor = new ValorRankingEntidad(resultadoRanking.getEntidad(), resultadoRanking.getValorPosicion());
        
        return valor;
    }

    // Este metodo es llamado por GeneradorDeInformes
    public static List<List<ValorRanking>> convertirAListValorRanking(List<ResultadoRanking> resultadosRanking){
        List<List<ValorRanking>> valoresRanking = new ArrayList<>();

        // Para saber cuantas listas hay q instanciar
        OptionalLong cantRank = resultadosRanking.stream().mapToLong(r -> r.getIdRanking()).max();
        int cantRankings = Math.toIntExact(cantRank.getAsLong());

        for(int i = 0; i < cantRankings; i++){
            List<ValorRanking> ranking = new ArrayList<>();
            valoresRanking.add(ranking);
        }

        resultadosRanking.forEach(resultado -> {
            List<ValorRanking> rankingObtenido = valoresRanking.get(Math.toIntExact(resultado.getIdRanking()));
            ValorRanking valorRanking = MapperResultadoRanking.convertirAValorRanking(resultado);

            rankingObtenido.add(Math.toIntExact(resultado.getPosicion()), valorRanking);
        });

        return valoresRanking;
    }

    // Este metodo es llamado por CalculadorRanking para UN SOLO ranking
    public static List<ResultadoRanking> convertirAResultadoRanking(List<ValorRanking> valoresRanking, Long idRanking){
        
        List<ResultadoRanking> listaDeResultadosRankings = new ArrayList<>();

        valoresRanking.forEach(valorRanking -> {
            if (valorRanking.getNombreSujeto() == "incidente"){
                ResultadoRanking resultado =  new ResultadoRanking();
                resultado.setIdRanking(idRanking);
                resultado.setPosicion((long) valoresRanking.indexOf(valorRanking));
                resultado.setEntidad(null);
                resultado.setIncidente((Incidente) valorRanking.getSujeto());
                resultado.setValorPosicion(valorRanking.getValor());
                
                listaDeResultadosRankings.add(resultado);
            }
            else{
                ResultadoRanking resultado =  new ResultadoRanking();
                resultado.setIdRanking(idRanking);
                resultado.setPosicion((long) valoresRanking.indexOf(valorRanking));
                resultado.setEntidad((Entidad) valorRanking.getSujeto());
                resultado.setIncidente(null);
                resultado.setValorPosicion(valorRanking.getValor());

                listaDeResultadosRankings.add(resultado);
            };
    });
        return listaDeResultadosRankings;
    }
}
