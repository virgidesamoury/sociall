/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Virginie
 */
public class JsfUtils {

    private static final Logger logger = Logger.getLogger(JsfUtils.class.getName());

    public static String translate(String message) {
        if (message == null) {
            return null;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("be.isl.desamouryv.sociall.i18n.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        String translation = null;
        try {
            translation = bundle.getString(message);
        } catch (Exception e) {
            logger.log(Level.WARNING, "key not found: {0}", message);
        }
        return translation;
    }

    public static void info(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, translate(summary), translate(detail)));
    }

    public static void info(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, translate(summary), translate(detail)));
    }

    public static void warn(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, translate(summary), translate(detail)));
    }
    public static void warn(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, translate(summary), translate(detail)));
    }

    public static void error(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, translate(summary), translate(detail)));
    }

    public static void error(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, translate(summary), translate(detail)));
    }

    public static void fatal(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, translate(summary), translate(detail)));
    }
        
    public static void fatal(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_FATAL, translate(summary), translate(detail)));
    }

    public static void infoFlash(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        info(message, null);
    }

    public static void errorFlash(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        error(message, null);
    }

    public static void warnFlash(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        warn(message, null);
    }

    public static void fatalFlash(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        fatal(message, null);
    }

    public static Locale getCurrentLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public static void setValidationFailed() {
        FacesContext.getCurrentInstance().validationFailed();
    }

    public static void putInFlash(String key, Object object) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put(key, object);
    }

    public static Object getFromFlash(String key) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        return flash.get(key);
    }

}
