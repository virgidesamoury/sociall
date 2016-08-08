/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

/**
 *
 * @author Virginie
 * @param <T>
 */
public interface BeanFacade<T> {
    
    public T find(T t);
    public T find(Long id);
    public void save(T t);
    public T update(T t);
    public void delete(T t);
    public void refresh(T t);
}
