package web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.usuarios.PrestadoraDeServicio;
import io.javalin.http.Context;
import persistence.repositories.Repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrestadorasDeServiciosController {

    Repositorio<PrestadoraDeServicio> repoPrestadorasDeServicios;

    public PrestadorasDeServiciosController(Repositorio<PrestadoraDeServicio> repoPrestadorasDeServicios){
        this.repoPrestadorasDeServicios = repoPrestadorasDeServicios;
    }

    public void obtenerPrestadoras(Context context) {
        String jsonResponse = this.armarJSONPrestadoras(repoPrestadorasDeServicios.obtenerTodos());

        context.contentType("application/json");

        context.result(jsonResponse);
    }

    private String armarJSONPrestadoras(List<PrestadoraDeServicio> listaDePrestadoras){
        List<Map<String, Object>> jsonPrestadoras = new ArrayList<>();

        listaDePrestadoras.forEach(prestadora -> {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("id", prestadora.getId());
        jsonMap.put("nombre", prestadora.getNombre());

        jsonPrestadoras.add(jsonMap);
        });

        try {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(jsonPrestadoras);

        } catch (Exception e) {
        e.printStackTrace();
        return "[]";
        }
    }
}
