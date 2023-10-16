package web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.comunidades.Comunidad;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController {

    Repositorio<Comunidad> repoComunidades;

    public ComunidadesController(Repositorio<Comunidad> repoComunidades){
        this.repoComunidades = repoComunidades;
    }
    
    public void obtenerComunidades(Context context) {
        String jsonResponse = this.armarJSONComunidades(repoComunidades.obtenerTodos());

        context.contentType("application/json");

        context.result(jsonResponse);
    }

    private String armarJSONComunidades(List<Comunidad> listaDeComunidades){
        List<Map<String, Object>> jsonComunidades = new ArrayList<>();

        listaDeComunidades.forEach(comunidad -> {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("id", comunidad.getId());
        jsonMap.put("nombre", comunidad.getNombre());

        jsonComunidades.add(jsonMap);
        });

        try {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(jsonComunidades);

        } catch (Exception e) {
        e.printStackTrace();
        return "[]";
        }
    }
}
