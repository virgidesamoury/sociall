/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Image;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface ImageFacade extends BeanFacade<Image>{
    
    public Image findSingleImageByArtifact(Artifact artifact);
    
}
