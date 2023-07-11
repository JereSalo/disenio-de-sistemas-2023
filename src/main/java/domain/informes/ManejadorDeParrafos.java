package domain.informes;

public class ManejadorDeParrafos {
    public Parrafo filtrarParrafo(Parrafo parrafo, String nombreEntidad){
        parrafo.getCuerpo().stream().filter(linea ->
            nombreEntidad.equals(linea.getNombreSujeto()));

        return parrafo;
    }
}
