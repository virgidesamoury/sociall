/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.converter;

import be.isl.desamouryv.sociall.domain.Recipe;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import be.isl.desamouryv.sociall.facade.RecipeFacade;
import be.isl.desamouryv.sociall.facade.RecipeFacadeImpl;
import be.isl.desamouryv.sociall.ui.util.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Virginie
 */
@FacesConverter(forClass = Recipe.class)
public class RecipeConverter extends BeanConverter {

    RecipeFacade recipeFacade = EJB.lookup(RecipeFacadeImpl.class);

    /**
     * Creates a new instance of UserConverter
     */
    public RecipeConverter() {
        super(Recipe.class);
    }

    @Override
    public BeanFacade<Recipe> getFacade() {
        return recipeFacade;
    }
}
