package persistence.repositories;

import domain.rankings.SujetosRanking;
import domain.rankings.rankings.Ranking;
import domain.rankings.valorRanking.ValorRanking;
import persistence.DAO.Dao;

import java.util.ArrayList;
import java.util.List;

// Este va a tener una lista de rankings, pero le voy a dar metodos para filtrar por ranking
public class RepositorioRanking extends Repositorio<Ranking> {

    private List<ValorRanking> rankings = new ArrayList<>();
    public RepositorioRanking(Dao<Ranking> dao) {
        super(dao);

    }
}
