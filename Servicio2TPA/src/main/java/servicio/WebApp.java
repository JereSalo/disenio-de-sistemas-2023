package servicio;

import com.fasterxml.jackson.databind.node.TextNode;
import io.javalin.Javalin;
import io.javalin.openapi.*;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import io.javalin.openapi.HttpMethod;


public class WebApp {
    public static void main(String[] args) {
        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));


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
                    swaggerConfiguration.setDocumentationPath("/swagger");
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
                .start(8080);


        app.get("/", ctx -> new PruebaController().handle(ctx));


        app.post("/calcular_puntaje", ctx -> {
            new CalcularPuntajeController().handle(ctx);
        });
    }




//    @OpenApi(
//            path = "/calcular_puntaje",
//            operationId = "calcularPuntaje",
//            methods = HttpMethod.POST,
//            summary = "Calcula Puntajes",
//            description = "Recibe JSON con datos de usuarios y comunidades, calcula puntajes y grado de confianza",
//            tags = {"Puntaje"},
//            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Datos.class)}),
//            headers = {
//                    //@OpenApiParam(name = "Authorization", description = "Alias and token provided as basic auth credentials", required = true, type = UUID.class),
//                    //@OpenApiParam(name = "Optional"),
//                    //@OpenApiParam(name = "X-Rick", example = "Rolled"),
//                    //@OpenApiParam(name = "X-SomeNumber", required = true, type = Integer.class, example = "500")
//            },
//            pathParams = {
//                    //@OpenApiParam(name = "name", description = "Name", required = true, type = UUID.class)
//            },
//            responses = {
////                    @OpenApiResponse(status = "200", description = "Status of the executed command", content = {
////                            @OpenApiContent(from = EntityDto[].class)
////                    }),
////                    @OpenApiResponse(
////                            status = "400",
////                            description = "Error message related to the invalid command format (0 < command length < " + 10 + ")",
////                            content = @OpenApiContent(from = EntityDto[].class)
////                    ),
////                    @OpenApiResponse(status = "401", description = "Error message related to the unauthorized access", content = {
////                            @OpenApiContent(from = EntityDto[].class)
////                    })
//            }
//
//    )

}

