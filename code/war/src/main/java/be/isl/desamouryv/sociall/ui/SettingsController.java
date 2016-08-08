/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Setting;
import be.isl.desamouryv.sociall.settings.SettingsFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class SettingsController implements Serializable {

    @EJB
    SettingsFacade settingsFacade;
    
    List<Setting> settings;
    
    /**
     * Creates a new instance of SettingsController
     */
    public SettingsController() {
    }
    
    @PostConstruct
    public void init(){
        settings = settingsFacade.findAll();
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }
    
}
