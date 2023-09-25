package servicio;

import io.javalin.Javalin;



public class Servicio {
    public static void main(String[] args) {
        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));

        Javalin app = Javalin.create().start(port);

        app.post("/calcular_puntaje", ctx -> {
            new CalcularPuntajeController().handle(ctx);
        });
    }

}

