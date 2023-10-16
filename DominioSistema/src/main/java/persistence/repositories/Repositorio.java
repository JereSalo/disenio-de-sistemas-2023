package persistence.repositories;

import lombok.Getter;
import lombok.Setter;
import persistence.DAO.Dao;

import java.util.List;

public class Repositorio<T> {
    @Getter @Setter
    private Dao<T> dao;

    public Repositorio(Dao<T> dao) {
        this.dao = dao;
    }

    public void modificar(Object unObjeto){
        this.dao.modificar(unObjeto);
    }

    public void eliminar(Object unObjeto){
        this.dao.eliminar(unObjeto);
    }

    public void agregar(Object unObjeto){
        this.dao.agregar(unObjeto);
    }

    public void agregarTodos(List<T> objetos){
        for (T objeto : objetos) {
            this.dao.agregar(objeto);
        }
    }

    public List<T> obtenerTodos(){
        return this.dao.obtenerTodos();
    }

    public T buscarPorId(Long id){
        return this.dao.buscarPorId(id);
    }

}
