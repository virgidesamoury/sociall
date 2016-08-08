/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui.detail;

import be.isl.desamouryv.sociall.domain.Event;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class EventDetailController extends DetailController<Event> {

    private static final Logger logger = Logger.getLogger(EventDetailController.class.getName());

    public EventDetailController() {
    }
    
}
