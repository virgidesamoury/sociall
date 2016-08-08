package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.AgendaEvent;
import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Image;
import be.isl.desamouryv.sociall.facade.AgendaEventFacade;
import be.isl.desamouryv.sociall.facade.ArtifactFacade;
import be.isl.desamouryv.sociall.facade.ImageFacade;
import be.isl.desamouryv.sociall.facade.ReviewFacade;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@Named
@ViewScoped
public class AgendaController implements Serializable {

    private static final Logger logger = Logger.getLogger(AgendaController.class.getName());

    @Inject
    UserSession userSession;

    @EJB
    AgendaEventFacade agendaEventFacade;

    @EJB
    ArtifactFacade artifactFacade;

    @EJB
    ImageFacade imageFacade;

    @EJB
    ReviewFacade reviewFacade;

    private ScheduleModel eventModel;
    private List<AgendaEvent> agendaEvents;
    private List<Artifact> artifacts;
    private List<AgendaEvent> agendaEventsContaining;

    private AgendaEvent eventDetail;
    private Image artifactDetailPic;
    private Double artifactDetailAvgRating;

    private AgendaEvent newEvent;
    private Artifact newEventArtifact;

    private ScheduleEvent selectEvent;

    /**
     * Creates a new instance of AgendaController
     */
    public AgendaController() {
    }

    @PostConstruct
    public void init() {
        newEvent = new AgendaEvent();
        eventModel = new DefaultScheduleModel();

        agendaEvents = agendaEventFacade.findByUser(userSession.getUser());

        for (AgendaEvent agendaEvent : agendaEvents) {
            Artifact artifact = agendaEvent.getArtifact();
            eventModel.addEvent(new DefaultScheduleEvent(artifact.getTitle(),
                    agendaEvent.getStartDate(),
                    agendaEvent.getEndDate(), 
                    agendaEvent));
        }
    }

    public void findArtifacts(String query) {
        artifacts = artifactFacade.findWithString(query);
    }

    public void findInAgenda(String query) {
        logger.log(Level.INFO, "findInAgenda query: {0}", query);
        agendaEventsContaining = agendaEventFacade.findByUserAndTitle(userSession.getUser(), query);
        logger.log(Level.INFO, "result: : {0}", agendaEventsContaining);
    }

    public void showDetail(AgendaEvent ae) {
        this.eventDetail = ae;
        artifactDetailPic = imageFacade.findSingleImageByArtifact(ae.getArtifact());
        artifactDetailAvgRating = reviewFacade.getAverageRatingForArtifact(ae.getArtifact());
    }

    public List<Artifact> searchArtifacts(String query) {
        return artifactFacade.findWithString(query);
    }

    public void saveEvent() {
        try {
            agendaEventFacade.save(newEvent);
            eventModel.addEvent(new DefaultScheduleEvent(newEvent.getArtifact().getTitle(),
                    newEvent.getStartDate(),
                    newEvent.getEndDate(),
                    newEvent));
            newEvent = null;
            JsfUtils.info("agenda.event.save.success", null);
        } catch (Exception e) {
            JsfUtils.error("common.error", null);
        }
    }

    public void onEventSelect(SelectEvent ev) {
        System.out.println("onEventSelect");
        selectEvent = (ScheduleEvent) ev.getObject();
        System.out.println(selectEvent);
        System.out.println(selectEvent.getData());
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public AgendaEvent getEventDetail() {
        return eventDetail;
    }

    public Image getArtifactDetailPic() {
        return artifactDetailPic;
    }

    public Double getArtifactDetailAvgRating() {
        return artifactDetailAvgRating;
    }

    public List<AgendaEvent> getAgendaEventsContaining() {
        return agendaEventsContaining;
    }

    public AgendaEvent getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(AgendaEvent newEvent) {
        this.newEvent = newEvent;
    }

    public Artifact getNewEventArtifact() {
        return newEventArtifact;
    }

    public void setNewEventArtifact(Artifact newEventArtifact) {
        this.newEventArtifact = newEventArtifact;
    }

    public ScheduleEvent getSelectEvent() {
        return this.selectEvent;
    }

    public void setSelectEvent(ScheduleEvent selectEvent) {
        this.selectEvent = selectEvent;
    }
}
