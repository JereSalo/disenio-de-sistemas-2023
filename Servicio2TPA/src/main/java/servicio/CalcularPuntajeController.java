package servicio;

import calculadores.confianza.ActualizadorGradoConfianza;
import calculadores.puntajes.CalculadorPuntajes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import servicio.entidades.Datos;

import java.io.IOException;

public class CalcularPuntajeController implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        String json = ctx.body();

        ObjectMapper objectMapper = new ObjectMapper();

        // Deserializar el JSON en un objeto Data
        try {
            Datos.initializeFromJson(json);
        } catch (IOException e) {
            // Manejar la excepción si ocurre un error al leer el JSON
            e.printStackTrace();
        }

        new Sincronizador().sincronizar();

        CalculadorPuntajes calculadorPuntajes = new CalculadorPuntajes();
        ActualizadorGradoConfianza actualizadorGradoConfianza = new ActualizadorGradoConfianza();

        calculadorPuntajes.calcularPuntajesUsuarios();
        actualizadorGradoConfianza.actualizarGradoConfianzaUsuarios();

        calculadorPuntajes.calcularPuntajeComunidades();
        actualizadorGradoConfianza.actualizarGradoConfianzaComunidades();

        Datos datos = Datos.getInstance();
        String json_respuesta = objectMapper.writeValueAsString(datos);
        ctx.result(json_respuesta);
    }
}
