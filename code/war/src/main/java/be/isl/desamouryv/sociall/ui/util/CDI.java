/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.util;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * <a href="dominikdorn.com/2010/04/cdi-weld-manual-bean-lookup"/>
 * @author GLX8000
 */
public class CDI {

    public static <T> T getBean(Class<T> clazz) {
        BeanManager bm = getBeanManager();
        Bean<T> bean = (Bean<T>) bm.getBeans(clazz).iterator().next();
        CreationalContext<T> ctx = bm.createCreationalContext(bean);
        T obj = (T) bm.getReference(bean, clazz, ctx); // this could be inlined, but intentionally left this way
        return obj;
    }

    private static BeanManager getBeanManager() {
        try {
            InitialContext initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            System.out.println(e);
            return null;
        }
    }
}
