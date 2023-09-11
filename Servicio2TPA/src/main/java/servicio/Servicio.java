package servicio;

import calculadores.ActualizadorGradoConfianza;
import calculadores.CalculadorPuntajes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import io.javalin.Javalin;
import io.javalin.openapi.BasicAuth;
import io.javalin.openapi.JsonSchemaLoader;
import io.javalin.openapi.JsonSchemaResource;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.SecurityConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;


import java.io.IOException;

public class Servicio {
    public static void main(String[] args) {

        //Javalin app = Javalin.create().start(7070);

        Javalin app = Javalin.create(config -> {
                    config.plugins.register(new OpenApiPlugin(
                                    new OpenApiPluginConfiguration()
                                            .withDocumentationPath("/openapi")
                                            .withDefinitionConfiguration((version, definition) -> definition
                                                    .withOpenApiInfo((openApiInfo) -> {
                                                        openApiInfo.setTitle("Awesome App");
                                                        openApiInfo.setVersion("1.0.0");
                                                    })
                                                    .withServer((openApiServer) -> {
                                                        openApiServer.setUrl(("http://localhost:{port}{basePath}/" + version + "/"));
                                                        openApiServer.setDescription("Server description goes here");
                                                        openApiServer.addVariable("port", "8080", new String[] { "7070", "8080" }, "Port of the server");
                                                        openApiServer.addVariable("basePath", "", new String[] { "", "v1" }, "Base path of the server");
                                                    })
                                                    .withDefinitionProcessor(content -> { // you can add whatever you want to this document using your favourite json api
                                                        content.set("test", new TextNode("Value"));
                                                        return content.toPrettyString();
                                                    }))
                            )
                    );

                    SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
                    // swaggerConfiguration.setDocumentationPath(deprecatedDocsPath);
                    config.plugins.register(new SwaggerPlugin(swaggerConfiguration));

                    ReDocConfiguration reDocConfiguration = new ReDocConfiguration();
                    // reDocConfiguration.setDocumentationPath(deprecatedDocsPath);
                    config.plugins.register(new ReDocPlugin(reDocConfiguration));

                    // Get schemes annotated with @JsonScheme annotation
                    for (JsonSchemaResource generatedJsonSchema : new JsonSchemaLoader().loadGeneratedSchemes()) {
                        System.out.println(generatedJsonSchema.getName());
                        System.out.println(generatedJsonSchema.getContentAsString());
                    }
                })
                .start(7070);


        app.post("/calcular_puntaje", ctx -> {
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
            actualizadorGradoConfianza.calcularGradoConfianzaUsuarios();

            calculadorPuntajes.calcularPuntajeComunidades();
            actualizadorGradoConfianza.calcularGradoConfianzaComunidades();

            String json_respuesta = objectMapper.writeValueAsString(Datos.getInstance());
            ctx.result(json_respuesta);
        });
    }
}
