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

        LectorCSV lectorCSV = new LectorCSV(pathArchivo, ';');
        lectorCSV.leerArchivo();

        ParserDatos parserDatos = new ParserDatos();

        List<PrestadoraDeServicio> prestadoras = parserDatos.getDatosPrestadoras(pathArchivo, ';');

        List<OrganismoDeControl> organismos = parserDatos.getDatosOrganismos(pathArchivo, ';');

//        // Mostrar en pantalla todos los nombres de prestadoras
//
//        System.out.println("Prestadoras: ");
//        for (PrestadoraDeServicio prestadora : prestadoras) {
//            System.out.println(prestadora.getNombre());
//        }
//
//        // Mostrar en pantalla todos los nombres de organismos
//
//        System.out.println("Organismos: ");
//        for (OrganismoDeControl organismo : organismos) {
//            System.out.println(organismo.getNombre());
//        }

        // Supongamos que ya tenemos a todas las prestadoras y org de control bien cargados

//        this.repoDePrestadorasDeServicio.agregarTodos(prestadoras);

//        this.repoDeOrganismosDeControl.agregarTodos(organismos);

        context.redirect("home");
    }
}
