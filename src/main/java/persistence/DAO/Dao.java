package persistence.DAO;

import java.util.List;

public interface Dao<T> {
    List<T> obtenerTodos();
    T buscarPorId(int id);
    void agregar(Object unObjeto);
    void modificar(Object unObjeto);
    void eliminar(Object unObjeto);
    List<T> ejecutarQuery(String query);
}
