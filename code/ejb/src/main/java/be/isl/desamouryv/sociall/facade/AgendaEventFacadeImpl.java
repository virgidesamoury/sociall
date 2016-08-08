package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.AgendaEvent;
import be.isl.desamouryv.sociall.domain.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@Stateless
public class AgendaEventFacadeImpl extends AbstractFacade<AgendaEvent> implements AgendaEventFacade {

    private static final Logger logger = Logger.getLogger(AgendaEventFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public AgendaEventFacadeImpl() {
        super(AgendaEvent.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public List<AgendaEvent> findByUser(User user) {
        return em.createNamedQuery("AgendaEvent.findByUser", AgendaEvent.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<AgendaEvent> findByUserAndPeriod(User user, Date startDate, Date endDate) {
        return em.createNamedQuery("AgendaEvent.findByUserAndPeriod", AgendaEvent.class)
                .setParameter("user", user)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .getResultList();
    }

    @Override
    public List<AgendaEvent> findByUserAndTitle(User user, String query) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<AgendaEvent> criteriaQuery = criteriaBuilder.createQuery(AgendaEvent.class);
//        Root<AgendaEvent> artifactRoot = criteriaQuery.from(AgendaEvent.class);
////        artifactRoot.join(AgendaEvent_.)
        return em.createNamedQuery("AgendaEvent.findByUserAndTitle", AgendaEvent.class)
                .setParameter("user", user)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

}
