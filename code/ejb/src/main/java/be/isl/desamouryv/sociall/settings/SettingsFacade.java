/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.settings;

import be.isl.desamouryv.sociall.domain.Setting;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface SettingsFacade extends BeanFacade<Setting>{

    public String getUploadPath();

    public Float getMaxRating();
    
    public List<Setting> findAll();
}
