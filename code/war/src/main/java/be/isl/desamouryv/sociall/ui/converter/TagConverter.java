/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.converter;

import be.isl.desamouryv.sociall.domain.Tag;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import be.isl.desamouryv.sociall.facade.TagFacade;
import be.isl.desamouryv.sociall.ui.util.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Virginie
 */
@FacesConverter(forClass = Tag.class)
public class TagConverter extends BeanConverter {

    TagFacade TagFacade = EJB.lookup(TagFacade.class);

    /**
     * Creates a new instance of TagConverter
     */
    public TagConverter() {
        super(Tag.class);
    }

    @Override
    public BeanFacade<Tag> getFacade() {
        return TagFacade;
    }
}
