/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.converter;

import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.ui.util.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Virginie
 */
@FacesConverter(forClass = User.class)
public class UserConverter extends BeanConverter {

    UserFacade UserFacade = EJB.lookup(UserFacade.class);

    /**
     * Creates a new instance of UserConverter
     */
    public UserConverter() {
        super(User.class);
    }

    @Override
    public BeanFacade<User> getFacade() {
        return UserFacade;
    }
}
