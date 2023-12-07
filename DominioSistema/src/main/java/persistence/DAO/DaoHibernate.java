package persistence.DAO;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DaoHibernate<T> implements WithSimplePersistenceUnit, Dao<T>{
    private Class<T> type;

    public DaoHibernate(Class<T> type){
        this.type = type;
    }

    @Override
    public List<T> obtenerTodos() {

        CriteriaBuilder builder = entityManager().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(this.type);

        criteria.from(type);

        List<T> entities = entityManager().createQuery(criteria).getResultList();

        entities.forEach(ent -> entityManager().refresh(ent));

        return entities;
    }

    @Override
    public T buscarPorId(Long id) {

        T t =  this.entityManager().find(type, id);

        if (t != null) entityManager().refresh(t);

        return t;
    }

    @Override
    public void agregar(Object unObjeto) {

        entityManager().getTransaction().begin();

        entityManager().persist(unObjeto);

        entityManager().getTransaction().commit();

    }

    @Override
    public void modificar(Object unObjeto) {


        entityManager().getTransaction().begin();

        entityManager().merge(unObjeto);

        entityManager().getTransaction().commit();
    }

    @Override
    public void eliminar(Object unObjeto) {

        
        entityManager().getTransaction().begin();

        Object reattached = entityManager().merge(unObjeto);

        entityManager().remove(reattached);

        entityManager().getTransaction().commit();

    }

}