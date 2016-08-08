/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui.converter;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Bean;
import be.isl.desamouryv.sociall.facade.ArtifactFacade;
import be.isl.desamouryv.sociall.facade.ArtifactFacadeImpl;
import be.isl.desamouryv.sociall.facade.BeanFacade;
import be.isl.desamouryv.sociall.ui.util.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Virginie
 */
@FacesConverter("artifactConverter")
public class ArtifactConverter extends BeanConverter{

    ArtifactFacade artifactFacade = EJB.lookup(ArtifactFacadeImpl.class);

    public ArtifactConverter() {
        super(Artifact.class);
    }
    
    @Override
    public BeanFacade<? extends Bean> getFacade() {
        return artifactFacade;
    }
    
}
