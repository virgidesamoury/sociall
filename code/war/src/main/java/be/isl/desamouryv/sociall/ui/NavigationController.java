/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Event;
import be.isl.desamouryv.sociall.domain.Place;
import be.isl.desamouryv.sociall.domain.Product;
import be.isl.desamouryv.sociall.domain.Recipe;
import be.isl.desamouryv.sociall.ui.socialauth.Authentication;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Virginie
 */
@Named
@RequestScoped
public class NavigationController {

    public enum ArtifactPage {

        EVENT(1L, Event.class, "/pages/detail/event.xhtml?id=", "/pages/member/createEvent.xhtml"),
        RECIPE(2L, Recipe.class, "/pages/detail/recipe.xhtml?id=", "/pages/member/createRecipe.xhtml"),
        PLACE(3L, Place.class, "/pages/detail/place.xhtml?id=", "/pages/member/createPlace.xhtml"),
        PRODUCT(4L, Product.class, "/pages/detail/product.xhtml?id=", "/pages/member/createProduct.xhtml");

        Long id;
        Class clazz;
        String detailPage;
        String createPage;

        private ArtifactPage(Long id, Class clazz, String detailPage, String createPage) {
            this.id = id;
            this.clazz = clazz;
            this.detailPage = detailPage;
            this.createPage = createPage;
        }

        public static ArtifactPage findByClass(Class clazz) {
            for (ArtifactPage a : ArtifactPage.values()) {
                if (a.clazz == clazz) {
                    return a;
                }
            }
            return null;
        }

    }

    private static final Logger logger = Logger.getLogger(NavigationController.class.getName());

    /**
     * Creates a new instance of NavigationController
     */
    public NavigationController() {
    }

    public static String create(Artifact artifact) {
        System.out.println("***************** create");
        JsfUtils.putInFlash("artifact", artifact);
        return ArtifactPage.findByClass(artifact.getClass()).createPage;
    }

    public static String detail(Artifact artifact) {
        logger.log(Level.INFO, "NavigationController.detail: {0}", artifact);
        logger.log(Level.INFO, "NavigationController.detail class: {0}", artifact.getClass());
        JsfUtils.putInFlash("artifact", artifact);
        return ArtifactPage.findByClass(artifact.getClass()).detailPage + artifact.getId() + "&faces-redirect=true";
    }

    public static String list(String string) {
        logger.log(Level.INFO, "NavigationController.list: {0}", string);
        return String.format("/pages/searchresults.xhtml?criteria=%s&faces-redirect=true", string);
    }

    public static String authentication(Authentication method) {
        String outcome = null;
        switch (method) {
            case LOGIN:
                outcome = Authentication.LOGIN.getPage();
                break;
            case SIGNIN:
                JsfUtils.info("signup.fillprofile", null);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                outcome = Authentication.SIGNIN.getPage() + "&faces-redirect=true";
                break;
        }
        return outcome;
    }

}
