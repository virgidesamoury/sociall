/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Role;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface RoleFacade extends BeanFacade<Role>{

    public Role findByName(String name);
    
}
