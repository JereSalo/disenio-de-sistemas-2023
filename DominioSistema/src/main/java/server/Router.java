package server;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
  public static void init() {
    Server.app().get("/", ctx -> {
      ctx.result("Hola masters");
    });

    Server.app().routes(() -> {

      });
  };
}
