package servicio;

import calculadores.confianza.ActualizadorGradoConfianza;
import calculadores.puntajes.CalculadorPuntajes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import servicio.entidades.Confiable;
import servicio.entidades.Datos;

import java.io.IOException;
import java.util.List;

public class CalcularPuntajeController implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        String json = ctx.body();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Datos.initializeFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Datos datos = Datos.getInstance();

        new Sincronizador().sincronizar();

        CalculadorPuntajes calculadorPuntajes = new CalculadorPuntajes();
        ActualizadorGradoConfianza actualizadorGradoConfianza = new ActualizadorGradoConfianza();

        calculadorPuntajes.calcularPuntajesUsuarios();
        actualizadorGradoConfianza.actualizarGradosDeConfianzaUsuarios();

        calculadorPuntajes.calcularPuntajeComunidades();
        actualizadorGradoConfianza.actualizarGradosDeConfianzaComunidades();


        String json_respuesta = objectMapper.writeValueAsString(datos);
        ctx.result(json_respuesta);
    }
}
