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
public enum SocialProvider {

    FACEBOOK("facebook"),
    GOOGLE("google"),
    GOOGLE_PLUS("googleplus"),
    TWITTER("twitter");

    String name;

    private SocialProvider(String name) {
        this.name = name;
    }

    public static SocialProvider findByName(String name) {
        for (SocialProvider provider : SocialProvider.values()) {
            if (provider.name.equals(name)) {
                return provider;
            }
        }
        return null;
    }
}
