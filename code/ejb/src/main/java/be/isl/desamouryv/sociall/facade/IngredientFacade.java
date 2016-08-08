/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Ingredient;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface IngredientFacade extends BeanFacade<Ingredient>{

    public List<Ingredient> findAll();

    public List<Ingredient> findContaining(String string);
    
}
