/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.facade.ArtifactFacade;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Virginie
 */
@Named(value = "searchController")
@ViewScoped
public class SearchController implements Serializable {

    private static final String SEARCH_CRITERIA_KEY = "searchCriteria";
    private static final String SEARCH_RESULT_KEY = "searchResult";

    private static final Logger logger = Logger.getLogger(SearchController.class.getName());

    @EJB
    ArtifactFacade artifactFacade;

    private List<Artifact> artifacts;
    private String criteria;

    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
    }
    
    @PostConstruct
    public void init(){
        artifacts = artifactFacade.findLatest(10);
    }

    public void doSearch() {
        logger.log(Level.INFO, "doSearch {0}", criteria);
        artifacts = searchArtifacts(criteria);
        logger.log(Level.INFO, "artifacts: {0}", artifacts);
    }

    private List<Artifact> searchArtifacts(String string) {
        return artifactFacade.findWithString(string);
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

}
