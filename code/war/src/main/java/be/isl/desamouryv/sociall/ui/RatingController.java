/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.facade.ReviewFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Virginie
 */
@Named(value = "ratingController")
@RequestScoped
public class RatingController {

    @EJB
    ReviewFacade reviewFacade;

    /**
     * Creates a new instance of RatingController
     */
    public RatingController() {
    }

    public Integer calculateAverageRating(Artifact artifact) {
        Double avg = reviewFacade.getAverageRatingForArtifact(artifact);
        if (avg == null) {
            return 0;
        }
        int round = roundRating(avg);
        return round;
    }

    public int roundRating(Double rating) {
        if (rating == null) {
            return 0;
        }
        Double d = rating / 20;
        int round = (int) Math.round(d);
        return round;
    }

}
