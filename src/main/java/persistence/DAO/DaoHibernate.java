package persistence.DAO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DaoHibernate<T> implements Dao<T> {
    private Class<T> type;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public DaoHibernate(Class<T> type){
        this.type = type;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    }

    private void instanciarEntityManager(){
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @Override
    public List<T> obtenerTodos() {

        instanciarEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(this.type);

        criteria.from(type);

        List<T> entities = this.entityManager.createQuery(criteria).getResultList();

        this.entityManager.close();

        return entities;
    }

    @Override
    public T buscarPorId(int id) {

        instanciarEntityManager();

        T t =  this.entityManager.find(type, id);

        this.entityManager.close();

        return t;
    }

    @Override
    public void agregar(Object unObjeto) {
        
        instanciarEntityManager();

        this.entityManager.getTransaction().begin();

        this.entityManager.persist(unObjeto);

        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void modificar(Object unObjeto) {

        instanciarEntityManager();

        this.entityManager.getTransaction().begin();

        this.entityManager.merge(unObjeto);

        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    @Override
    public void eliminar(Object unObjeto) {

        instanciarEntityManager();
        
        this.entityManager.getTransaction().begin();

        Object reattached = this.entityManager.merge(unObjeto);

        this.entityManager.remove(reattached);

        this.entityManager.getTransaction().commit();

        this.entityManager.close();
    }

    public void eliminarTodo(){
        //TODO
        instanciarEntityManager();
    }
}