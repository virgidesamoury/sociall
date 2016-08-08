/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Artifact;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface ArtifactFacade extends BeanFacade<Artifact>{

    public List<Artifact> findWithString(String string);

    public Artifact findArtifact(Long id);
    
    public List<Artifact> findLatest(int max);
}
