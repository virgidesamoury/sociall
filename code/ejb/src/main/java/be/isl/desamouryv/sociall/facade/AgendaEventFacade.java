
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.AgendaEvent;
import be.isl.desamouryv.sociall.domain.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@Local
public interface AgendaEventFacade extends BeanFacade<AgendaEvent>{
    
    public List<AgendaEvent> findByUser(User user);
    
    public List<AgendaEvent> findByUserAndPeriod(User user, Date startDate, Date endDate);
    
    public List<AgendaEvent> findByUserAndTitle(User user, String query);
    
    
}
