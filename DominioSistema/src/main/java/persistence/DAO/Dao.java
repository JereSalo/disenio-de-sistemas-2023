package persistence.DAO;

import java.util.List;

public interface Dao<T> {
    List<T> obtenerTodos();
    T buscarPorId(Long id);
    void agregar(Object unObjeto);
    void modificar(Object unObjeto);
    void eliminar(Object unObjeto);
}
