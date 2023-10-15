package web.controllers;

import domain.csv.LectorCSV;
import domain.csv.ParserDatos;
import domain.usuarios.OrganismoDeControl;
import domain.usuarios.PrestadoraDeServicio;
import domain.usuarios.Usuario;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;
import org.apache.commons.io.FileUtils;
import persistence.repositories.Repositorio;

import javax.naming.ldap.Control;
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

        modificarModelSiEstaLogueado(context, model);

        context.render("carga-masiva.hbs", model);
    }

    public void cargar(Context context) {
        UploadedFile archivo = context.uploadedFile("archivo");

        String pathArchivo = "src/main/resources/domain/" + archivo.filename();

        FileUtil.streamToFile(archivo.content(), pathArchivo);

        String tipoEntidad = context.formParam("entidad");
        ParserDatos parserDatos = new ParserDatos();

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
