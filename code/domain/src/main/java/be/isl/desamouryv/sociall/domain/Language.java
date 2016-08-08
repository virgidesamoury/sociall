/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.domain;

/**
 *
 * @author Virginie
 */
public enum Language {
    FR("fr"), NL("nl"), EN("en");
     
    private String code;

    private Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Language findByCode(String code){
        Language[] values = Language.values();
        for (Language language : values) {
            if(language.getCode().equals(code)){
                return language;
            }
        }
        return null;
    }
    
}
