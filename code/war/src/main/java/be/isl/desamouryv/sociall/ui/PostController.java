/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Category;
import be.isl.desamouryv.sociall.domain.Event;
import be.isl.desamouryv.sociall.domain.Ingredient;
import be.isl.desamouryv.sociall.domain.Place;
import be.isl.desamouryv.sociall.domain.Product;
import be.isl.desamouryv.sociall.domain.Recipe;
import be.isl.desamouryv.sociall.domain.Tag;
import be.isl.desamouryv.sociall.facade.ArtifactFacade;
import be.isl.desamouryv.sociall.facade.TagFacade;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Virginie
 */
@Named(value = "postController")
@ViewScoped
public class PostController implements Serializable {

    private static final Logger logger = Logger.getLogger(PostController.class.getName());

    @EJB
    ArtifactFacade artifactFacade;

    @EJB
    TagFacade tagFacade;

    private Artifact artifact;

    private Category category;

    private int step;
    private String title;
    private String description;

    private Recipe recipe;
    private Event event;
    private List<Artifact> artifacts;

    private Tag selectedTag;
    List<Tag> selectedTags;

    List<Ingredient> selectedIngredients;

    /**
     * Creates a new instance of PostController
     */
    public PostController() {
        artifact = new Recipe();
        selectedTags = new ArrayList<>();
        selectedIngredients = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            selectedIngredients.add(new Ingredient());
        }
    }

    public void extractFromFlash() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            artifact = (Artifact) JsfUtils.getFromFlash("artifact");
        }
    }

    public void categoryValueChange() {
        switch (category) {
            case RECIPES:
                artifact = new Recipe();
                break;
            case EVENTS:
                artifact = new Event();
                break;
            case PLACES:
                artifact = new Place();
                break;
            case PRODUCTS:
                artifact = new Product();
                break;
        }
        artifact.setTitle(title);
    }

    public void submit() {
        logger.log(Level.INFO, "Text: {0}", description);
    }

    public void searchArtifacts() {
        logger.log(Level.INFO, "searchArtifacts");
        artifacts = artifactFacade.findWithString(title);
    }

    public String checkForDuplicates() {
        searchArtifacts();
        if (artifacts.isEmpty()) {
            artifact.setTitle(title);
            return NavigationController.create(artifact);
        }
        return null;
    }

    public List<Tag> completeTag(String query) {
        return tagFacade.findContaining(query);
    }

    public void handleSelect() {
        if (selectedTags != null) {
            selectedTags.add(selectedTag);
            selectedTag = null;
        }
    }

    public void removeIngredient(Ingredient i) {
        for (Iterator<Ingredient> it = selectedIngredients.iterator(); it.hasNext();) {
            Ingredient ingredient = it.next();
            if (ingredient.equals(i)) {
                it.remove();
                break;
            }
        }
    }

    public void addIngredient() {
        selectedIngredients.add(new Ingredient());
    }

    public void removeTag(Tag t) {
        for (Iterator<Tag> it = selectedTags.iterator(); it.hasNext();) {
            Tag tag = it.next();
            if (tag.equals(t)) {
                it.remove();
                break;
            }
        }
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public void setSelectedTags(List<Tag> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public List<Tag> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedIngredients(List<Ingredient> selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }

    public List<Ingredient> getSelectedIngredients() {
        return selectedIngredients;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

}
