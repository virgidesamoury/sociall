/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.AgendaEvent;
import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Contact;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.AgendaEventFacade;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.service.ContactService;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class ProfileController implements Serializable {

    @EJB
    AgendaEventFacade agendaEventFacade;

    @EJB
    UserFacade userFacade;

    @Inject
    UserSession userSession;

    @EJB
    ContactService contactService;

    private Long userId;

    private User user;

    private Contact contact;
    
    private ScheduleModel eventModel;
    private List<AgendaEvent> agendaEvents;

    /**
     * Creates a new instance of ProfileController
     */
    public ProfileController() {
    }


    public void findProfileValues() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            System.out.println("findProfileValue");
            searchUser();
            fillAgenda();
            findContact();
        }
    }
    
    
    public void searchUser() {
        System.out.println("searchuser " + userId);
        user = userFacade.find(userId);
    }

    private void fillAgenda() {
        System.out.println("fillAgenda");
        eventModel = new DefaultScheduleModel();

        agendaEvents = agendaEventFacade.findByUser(user);

        for (AgendaEvent agendaEvent : agendaEvents) {
            Artifact artifact = agendaEvent.getArtifact();
            eventModel.addEvent(new DefaultScheduleEvent(artifact.getTitle(),
                    agendaEvent.getStartDate(),
                    agendaEvent.getEndDate(),
                    agendaEvent));
        }
    }

    private void findContact() {
        System.out.println("findContact");
        contact = contactService.findContactByUsers(userSession.getUser(), user);
    }
    

    public void addContact() {
        System.out.println("addContact");
        try {
            contact = contactService.addContact(userSession.getUser(), user);
            JsfUtils.info("contact.add.success", null);
        } catch (Exception e) {
            JsfUtils.error("common.error", null);
        }
    }
    
    public boolean showInviteButton(){
        boolean result = contact == null;
        System.out.println("showInviteButton: " + result);
        return result;
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public Contact getContact() {
        return contact;
    }
    

}
