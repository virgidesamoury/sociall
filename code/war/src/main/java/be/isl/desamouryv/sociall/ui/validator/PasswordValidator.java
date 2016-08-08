/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.validator;

import be.isl.desamouryv.sociall.ui.UserSession;
import be.isl.desamouryv.sociall.ui.util.CDI;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Permet de vérifier si une chaîne passée en input est bien égale au mot de
 * passe de userSession.customer.
 *
 * @author Virginie
 */
@FacesValidator
public class PasswordValidator implements Validator {

    UserSession userSession = CDI.getBean(UserSession.class);
//    UserSession userSession = EJB.lookup(UserSession.class );

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String inputPwd = (String) value;

        if (inputPwd == null || inputPwd.isEmpty() || userSession.getUser()== null) {
            return;
        }

        if (!inputPwd.equals(userSession.getUser().getPassword())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.translate("validator.password"), ""));
        }
    }

}
