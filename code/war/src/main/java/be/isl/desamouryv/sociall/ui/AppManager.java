/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.app.MenuItem;
import be.isl.desamouryv.sociall.facade.MenuItemFacade;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Virginie
 */
@Named(value = "appManager")
@ApplicationScoped
//@Eager //http://ovaraksin.blogspot.be/2013/02/eager-cdi-beans.html
public class AppManager {

    private static final Logger logger = Logger.getLogger(AppManager.class.getName());

//      javax.ejb.EJBException: Attempt to invoke when container is in Initializing    
//    SettingsFacade settingsFacade = EJB.lookup(SettingsFacadeImpl.class);
//    IngredientFacade ingredientFacade = EJB.lookup(IngredientFacadeImpl.class);
    @EJB
    MenuItemFacade menuItemFacade;

    private LinkedList<MenuItem> menuItems;

    /**
     * Creates a new instance of AppManager
     */
    public AppManager() {
    }

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "AppManager.init");
        menuItems = new LinkedList<>(menuItemFacade.findAll());
        logger.log(Level.INFO, "MenuItems: {0}", menuItems);
    }

    public LinkedList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(LinkedList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Integer getMenuIndex(String title) {
        System.out.println("*******************************************");
        if (menuItems != null) {
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getTitle().equals(title)) {
                    return menuItem.getIndex();
                }
            }
        }
        return -1;
    }
    
    public int getTest(){
        return 1;
    }

}
