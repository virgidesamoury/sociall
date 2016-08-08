/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.validator;

import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Permet de vérifier que deux chaînes de caractères entrées dans deux inputs
 * sont bien égales (voir clientdetail.xhtml). Le validator doit être attaché au
 * premier input, le second doit avoir binding="#{repeatPassword}".
 *
 * @author Virginie
 */
@FacesValidator
public class NewPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String password = (String) value;

        UIInput repeatComponent = (UIInput) component.getAttributes().get("repeatPassword");
        String repeatPassword = (String) repeatComponent.getSubmittedValue();

        if (password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty()) {
            return;
        }

        if (!password.equals(repeatPassword)) {
            repeatComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.translate("validator.password"), ""));
        }
    }

}
