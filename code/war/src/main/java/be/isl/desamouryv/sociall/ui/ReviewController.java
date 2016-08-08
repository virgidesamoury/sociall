/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Review;
import be.isl.desamouryv.sociall.facade.ReviewFacade;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class ReviewController implements Serializable {

    @Inject
    UserSession userSession;

    @EJB
    ReviewFacade reviewFacade;

    private Review review;

    private Integer rating;

    private boolean saved;

    /**
     * Creates a new instance of ReviewController
     */
    public ReviewController() {
        saved = false;
        review = new Review();
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void createReview(Artifact artifact) {
        try {
            review.setUser(userSession.getUser());
            review.setArtifact(artifact);
            doSetRating(review, rating);
            reviewFacade.save(review);
            userSession.addReview(review);
            addReview(artifact);
            saved = true;
            JsfUtils.info("review.save.success", null);
        } catch (Exception e) {
            JsfUtils.error("common.error", null);
        }
    }

    public void addReview(Artifact artifact) {
        if(artifact.getReviews() == null){
            artifact.setReviews(new ArrayList<Review>());
        }
        artifact.getReviews().add(review);
    }

    private void doSetRating(Review review, Integer rating) {
        if (rating == null) {
            review.setRating(0);
        } else {
            review.setRating(rating * 20);
        }
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public boolean isSaved() {
        return saved;
    }

}
