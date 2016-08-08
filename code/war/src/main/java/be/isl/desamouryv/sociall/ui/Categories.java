/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Category;
import java.util.Arrays;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Virginie
 */
@Named(value = "categories")
@ApplicationScoped
public class Categories {

    private final List<Category> categories;
    
    /**
     * Creates a new instance of Categories
     */
    public Categories() {
        categories = Arrays.asList(Category.values());
    }
    
    public List<Category> getCategories(){
        return categories;
    }
    
}
