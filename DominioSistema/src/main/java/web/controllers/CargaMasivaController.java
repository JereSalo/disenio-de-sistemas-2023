package web.controllers;

import domain.csv.ParserDatos;
import domain.usuarios.OrganismoDeControl;
import domain.usuarios.PrestadoraDeServicio;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;
import persistence.repositories.Repositorio;
import web.controllers.base.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargaMasivaController extends Controller {

    private Repositorio<PrestadoraDeServicio> repoDePrestadorasDeServicio;
    private Repositorio<OrganismoDeControl> repoDeOrganismosDeControl;

    public CargaMasivaController(Repositorio<PrestadoraDeServicio> repoDePrestadoras, Repositorio<OrganismoDeControl> repoDeOrganismos) {
        this.repoDePrestadorasDeServicio = repoDePrestadoras;
        this.repoDeOrganismosDeControl = repoDeOrganismos;
    }

    public void mostrarFormCargaMasiva(Context context) {
        Map<String, Object> model = new HashMap<>();

        modificarModelLogueado(context, model);

        context.render("carga-masiva.hbs", model);
    }

    public void cargar(Context context) {
        // Agarramos archivo subido y lo guardamos en el directorio resources
        UploadedFile archivo = context.uploadedFile("archivo");
        String pathArchivo = "src/main/resources/domain/" + archivo.filename();
        FileUtil.streamToFile(archivo.content(), pathArchivo);

        ParserDatos parserDatos = new ParserDatos();

        String tipoEntidad = context.formParam("entidad");
        switch (tipoEntidad) {
            case "prestadoras":
                List<PrestadoraDeServicio> prestadoras = parserDatos.getDatosPrestadoras(pathArchivo, ';');
                this.repoDePrestadorasDeServicio.agregarTodos(prestadoras);
                break;
            case "organismos_de_control":
                List<OrganismoDeControl> organismos = parserDatos.getDatosOrganismos(pathArchivo, ';');
                this.repoDeOrganismosDeControl.agregarTodos(organismos);
                break;
        }

        context.redirect("home");
    }
}
