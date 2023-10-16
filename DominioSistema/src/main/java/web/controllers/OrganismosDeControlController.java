package web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.usuarios.OrganismoDeControl;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import persistence.repositories.Repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganismosDeControlController {

    Repositorio<OrganismoDeControl> repoOrganismosDeControl;

    public OrganismosDeControlController(Repositorio<OrganismoDeControl> repoOrganismosDeControl){
        this.repoOrganismosDeControl = repoOrganismosDeControl;
    }

    public void obtenerOrganismos(Context context) {

        String jsonResponse = this.armarJSONOrganismos(repoOrganismosDeControl.obtenerTodos());

        context.contentType("application/json");

        context.result(jsonResponse);
    }

    private String armarJSONOrganismos(List<OrganismoDeControl> listaDeOrganismos){
        List<Map<String, Object>> jsonOrganismos = new ArrayList<>();

        listaDeOrganismos.forEach(organismo -> {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("id", organismo.getId());
        jsonMap.put("nombre", organismo.getNombre());

        jsonOrganismos.add(jsonMap);
        });

        try {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(jsonOrganismos);

        } catch (Exception e) {
        e.printStackTrace();
        return "[]";
        }
  }
}
