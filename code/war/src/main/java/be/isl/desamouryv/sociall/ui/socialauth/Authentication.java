/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui.socialauth;

/**
 *
 * @author Virginie
 */
public enum Authentication {
    LOGIN("/"),
    SIGNIN("/pages/member/profile");
    
    private final String page;

    private Authentication(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
