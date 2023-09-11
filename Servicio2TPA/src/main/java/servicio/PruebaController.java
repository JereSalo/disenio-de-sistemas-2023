package servicio;

import io.javalin.http.Handler;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;

public class PruebaController implements Handler {
    public PruebaController() {
        super();
    }

    @OpenApi(
            summary = "Prueba",
            operationId = "prueba",
            tags = {"Prueba"},
            path = "/",
            responses = {
                    @OpenApiResponse(status = "200", description = "Status of the executed command", content = {
                            @OpenApiContent(from = String.class)
                    })
            }
    )
    @Override
    public void handle(io.javalin.http.Context ctx) throws Exception {
        ctx.result("Este es prueba controller");
    }
}
