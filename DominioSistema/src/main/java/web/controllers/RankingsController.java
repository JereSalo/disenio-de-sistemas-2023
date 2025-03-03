package web.controllers;

import domain.rankings.CalculadorRanking;
import domain.rankings.rankings.CantidadDeIncidentes;
import domain.rankings.rankings.GradoDeImpacto;
import domain.rankings.rankings.PromedioTiempoCierre;
import domain.rankings.rankings.Ranking;
import domain.rankings.valorRanking.ValorRanking;
import persistence.repositories.Repositorio;

import io.javalin.http.Context;
import web.controllers.base.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingsController extends Controller {

    // No me acuerdo de que tendria que ser el repo (valorRanking / sujetoRanking / etc)
    private Repositorio<Ranking> repoRankings;

    public RankingsController(Repositorio<Ranking> repo){
        this.repoRankings = repo;
    }

    public void mostrarRankings(Context context){
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        Ranking promedioTiempoCierre = new PromedioTiempoCierre();
        Ranking cantidadDeIncidentes = new CantidadDeIncidentes();

        List<Ranking> listaCriterios = new ArrayList<>();

        listaCriterios.add(promedioTiempoCierre);
        listaCriterios.add(cantidadDeIncidentes);

        List<List<ValorRanking>> listaRankings = CalculadorRanking.getInstance(listaCriterios).generarTodosLosRankings();

        List<ValorRanking> rankingPromedioTiempoCierre = listaRankings.get(0);
        List<ValorRanking> rankingMayorCantIncidentes = listaRankings.get(1);

        model.put("rankingMayorCantIncidentes", rankingMayorCantIncidentes);
        model.put("rankingPromedioTiempoCierre", rankingPromedioTiempoCierre);

        context.render("rankings.hbs", model);
    }
}
