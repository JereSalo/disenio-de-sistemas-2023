package persistence.repositories;

import persistence.DAO.Dao;
import persistence.DAO.DaoHibernate;

import java.util.HashMap;

public class FactoryRepositorios {
    
    private static HashMap<String, Repositorio> hashMapRepos;

    static {
        hashMapRepos = new HashMap<>();
    }

    public static <T> Repositorio<T> get(Class<T> tipo){
        
        Repositorio repo;
        
        if(hashMapRepos.containsKey(tipo.getName()))
            repo = hashMapRepos.get(tipo.getName());
        else{
                Dao<T> dao = new DaoHibernate<>(tipo);
                repo = new Repositorio<>(dao);
        } 
        hashMapRepos.put(tipo.toString(), repo);
        
        return repo;
    }
}
