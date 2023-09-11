package persistence.repositories;

import lombok.Getter;
import lombok.Setter;
import persistence.DAO.Dao;

import java.util.List;

public class Repositorio<T> {
    @Getter @Setter
    protected Dao<T> dao;

    public Repositorio(Dao<T> dao) {
        this.dao = dao;
    }

    public void modificar(Object unObjeto){
        this.dao.modificar(unObjeto);
    }

    public void eliminar(Object unObjeto){
        this.dao.eliminar(unObjeto);
    }

    public void eliminarTodo(){
        this.dao.eliminarTodo();
    }

    public void agregar(Object unObjeto){
        this.dao.agregar(unObjeto);
    }

    public List<T> ejecutarQuery(String query){
        return this.dao.ejecutarQuery(query);
    }

    public List<T> obtenerTodos(){
        return this.dao.obtenerTodos();
    }

    public T buscarPorId(int id){
        return this.dao.buscarPorId(id);
    }

}
