package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.mail.MailSender;
import be.isl.desamouryv.sociall.facade.UserFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class MainController implements Serializable {

    private static final Logger logger = Logger.getLogger(MainController.class.getName());
    
    @EJB
    UserFacade userFacade;

    @EJB
    MailSender mailSender;

    List<CategoryUI> categories;
    List<Artifact> artifacts;

    /**
     * Creates a new instance of MainController
     */
    public MainController() {
        categories = new ArrayList<>();
        String[] names = {"Recettes", "Lieux", "Produits", "Evénements"};
        String[] urls = {"pages/recipe/list", "pages/place/list", "pages/product/list", "pages/event/list"};
        String[] css = {"recipes", "places", "products", "events"};
        for (int i = 0; i < names.length; i++) {
            CategoryUI category = new CategoryUI();
            category.setName(names[i]);
            category.setUrl(urls[i]);
            category.setCssClass(css[i]);
            categories.add(category);
        }
    }

    @PostConstruct
    public void init() {

    }

    public String doSignUp() {
        logger.log(Level.INFO, "doSignUp");
        return "/pages/signin/signup";
    }

    public List<CategoryUI> getCategories() {
        return categories;
    }

    public class CategoryUI {

        String name;
        String url;
        String cssClass;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCssClass() {
            return cssClass;
        }

        public void setCssClass(String cssClass) {
            this.cssClass = cssClass;
        }

    }

}
