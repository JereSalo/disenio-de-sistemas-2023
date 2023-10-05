package web.controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;

import javax.naming.ldap.Control;
import java.util.HashMap;
import java.util.Map;

public class CargaMasivaController extends Controller {
    public void mostrarFormCargaMasiva(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelSiEstaLogueado(context, model);

        context.render("carga-masiva.hbs", model);
    }

    public void cargar(Context context) {
        // Obtener el archivo

        UploadedFile archivo = context.uploadedFile("archivo");

        // Esto esta en la documentacion de javalin, no se ni qué hace
        // FileUtil.streamToFile(archivo.content(), "upload/" + archivo.filename());

        // Ahora tendría que hallar la forma de interactuar con el Lector de CSV para que lea el archivo y cree los objetos


    }
}
