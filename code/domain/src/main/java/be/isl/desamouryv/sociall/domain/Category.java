/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Virginie
 */
public enum Category {
    
    RECIPES(1L, "category.recipes"),
    PLACES(2L, "category.places"),
    PRODUCTS(3L, "category.products"),
    EVENTS(4L, "category.events");
    
    private Long id;
    private String bundleKey;

    private Category(Long id, String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public Long getId() {
        return id;
    }
    
    public String getBundleKey() {
        return bundleKey;
    }

    public Category findForId(Long id){
        for (Category category : Category.values()) {
            if(category.getId().equals(id))
                return category;
        }
        return null;
    }
    

    
}
