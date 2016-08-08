/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.converter;

import be.isl.desamouryv.sociall.domain.Ingredient;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import be.isl.desamouryv.sociall.facade.IngredientFacade;
import be.isl.desamouryv.sociall.facade.IngredientFacadeImpl;
import be.isl.desamouryv.sociall.ui.util.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Virginie
 */
@FacesConverter(forClass = Ingredient.class)
//@FacesConverter("ingredientConverter")
public class IngredientConverter extends BeanConverter {

    IngredientFacade ingredientFacade = EJB.lookup(IngredientFacadeImpl.class);

    /**
     * Creates a new instance of UserConverter
     */
    public IngredientConverter() {
        super(Ingredient.class);
    }

    @Override
    public BeanFacade<Ingredient> getFacade() {
        return ingredientFacade;
    }
}
