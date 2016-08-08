/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Bean;
import javax.persistence.EntityManager;

/**
 *
 * @author Virginie
 * @param <T>
 */
public abstract class AbstractFacade<T extends Bean> implements BeanFacade<T>{
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public T find(Long id){
        return getEntityManager().find(entityClass, id);
    }
    
    @Override
    public T find(T t){
        return getEntityManager().find(entityClass, t.getId());
    }
    
    @Override
    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public void refresh(T entity){
        getEntityManager().refresh(entity);
    }
    
}
