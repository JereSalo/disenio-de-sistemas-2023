package web.controllers;

import domain.csv.LectorCSV;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;
import org.apache.commons.io.FileUtils;

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
        UploadedFile archivo = context.uploadedFile("archivo");

        String pathArchivo = "src/main/resources/domain/" + archivo.filename();

        FileUtil.streamToFile(archivo.content(), pathArchivo);

        LectorCSV lectorCSV = new LectorCSV(pathArchivo, ';');
        lectorCSV.leerArchivo();



        context.redirect("home");
    }
}
