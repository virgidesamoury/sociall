/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui.util;

import be.isl.desamouryv.sociall.domain.Language;

/**
 *
 * @author Virginie
 */
public class Utils {
        
    public static Language getCurrentLanguage() {
        Language language = Language.findByCode(JsfUtils.getCurrentLocale().getLanguage());
        return language == null ? Language.EN : language;
    }
    
}
