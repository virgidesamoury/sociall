/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.converter;

import be.isl.desamouryv.sociall.domain.Bean;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Virginie
 */
public abstract class BeanConverter implements Converter {

    private Class clazz;

    /**
     * Creates a new instance of BookConverter
     */
    public BeanConverter() {
    }

    public BeanConverter(Class clazz) {
        this.clazz = clazz;
    }

    public abstract BeanFacade<? extends Bean> getFacade();

    @Override
    public Bean getAsObject(FacesContext context, UIComponent component, String value) {
         if (value == null || value.isEmpty()) {
            return null;
        }
         
        if (!value.matches("\\d+")) {
            throw new ConverterException(new FacesMessage("Wrong entity id format: " + value));
        }
        
        Bean entity = getFacade().find(Long.valueOf(value));
        
        return entity;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
//        if (value.getClass() != clazz) {
//            throw new ConverterException(new FacesMessage("Wrong value type: " + value.getClass()));
//        }
        Long id = ((Bean) value).getId();
        
        return (id != null) ? String.valueOf(id) : null;
    }

}
