/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.settings;

import be.isl.desamouryv.sociall.Constants;
import be.isl.desamouryv.sociall.domain.Setting;
import be.isl.desamouryv.sociall.facade.AbstractFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class SettingsFacadeImpl extends AbstractFacade<Setting> implements SettingsFacade {

    private static final Logger logger = Logger.getLogger(SettingsFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public SettingsFacadeImpl() {
        super(Setting.class);
    }

    @Override
    public String getUploadPath() {
        logger.log(Level.INFO, "Loading setting with key {0}...", Constants.UPLOAD_PATH_KEY);
        Setting setting = (Setting) em.createQuery("SELECT s FROM Setting s WHERE s.key like " 
                + Constants.UPLOAD_PATH_KEY)
                .getSingleResult();
        logger.log(Level.INFO, "... setting found: {0}", setting.getTextValue());
        return setting.getTextValue();
    }
    
    @Override
    public Float getMaxRating() {
        logger.log(Level.INFO, "Loading setting with key {0}...", Constants.MAX_RATING_KEY);
        Setting setting = (Setting) em.createQuery("SELECT s FROM Setting s WHERE s.key like " 
                + Constants.MAX_RATING_KEY)
                .getSingleResult();
        logger.log(Level.INFO, "... setting found: {0}", setting.getTextValue());
        return setting.getNumericValue().floatValue();
    }

    @Override
    public List<Setting> findAll() {
        return em.createNamedQuery("Setting.findAll", Setting.class).getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
