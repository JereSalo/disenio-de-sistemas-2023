package domain.rankings;

// ResultadoRanking esta en la base de datos
// ValorRanking se usa a nivel codigo
public class MapperResultadoRanking {

    public static ValorRanking convertirAValorRanking(ResultadoRanking resultadoRanking){
        ValorRanking valorRanking = this.instanciarValorRanking(resultadoRanking);                

        return valorRanking;
    }

    private static ValorRanking instanciarValorRanking(ResultadoRanking resultadoRanking){
        ValorRanking valor;

        if(resultadoRanking.entidad == null)
            valor = new ValorRanking(resultadoRanking.incidente, )

        return valor;
    }

    // Este metodo es llamado por GeneradorDeInformes
    public static List<List<ValorRanking>> convertirAListValorRanking(List<ResultadoRanking> resultadosRanking){
        List<List<ValorRanking>> valoresRanking = new ArrayList<>();

        // Para saber cuantas listas hay q instanciar
        Collection.max(resultadoRanking.map(r -> r.getIdRanking()));
        
        resultadosRanking.forEach({
            new ArrayList<>();
            
        })
    }

    // Este metodo es llamado por CalculadorRanking para UN SOLO ranking
    public static List<ResultadoRanking> convertirAResultadoRanking(List<ValorRanking> valoresRanking, Long idRanking){
        List<ResultadoRanking> listaDeResultadosRankings = new ArrayList<>();

        valoresRanking.forEach(valorRanking -> {
            if valorRanking.getNombreSujeto() == "incidente"{
                listaDeResultadosRankings.add( new ResultadoRanking(idRanking, valoresRanking.getIndex(valorRanking), null,
                valorRanking.getIncidente, valorRanking.getValor()));
            }
            else{
                listaDeResultadosRankings.add( new ResultadoRanking(idRanking, valoresRanking.getIndex(valorRanking), ));
            }
            
            });
    }
}
