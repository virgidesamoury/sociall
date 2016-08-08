
package be.isl.desamouryv.sociall.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "AgendaEvent.findByUser", query = "SELECT a FROM AgendaEvent a WHERE a.user = :user"),
    @NamedQuery(name = "AgendaEvent.findByUserAndPeriod", query = "SELECT a FROM AgendaEvent a WHERE a.user = :user AND a.startDate BETWEEN :start AND :end"),
    @NamedQuery(name = "AgendaEvent.findByUserAndTitle", query = "SELECT a FROM AgendaEvent a WHERE a.user = :user AND LOWER(a.artifact.title) LIKE LOWER(:query)")
})
public class AgendaEvent extends Bean{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGE_SEQ")
    @SequenceGenerator(name = "AGE_SEQ",
            sequenceName = "agenda_event_id_seq",
            initialValue = 100)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    private String note;
    
    @ManyToOne
    private Artifact artifact;
    
    @XmlTransient
    @OneToOne
    private User user;
    
    @Override
    public Long getId() {
        return this.id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
