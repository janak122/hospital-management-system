
package org.raghuvir.hms.applisteners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.raghuvir.hms.daos.*;
import org.raghuvir.hms.daos.HibernateTemplet.SearchingCode;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.raghuvir.hms.utils.NewHibernateUtil;

public class ContextListenerImpl implements ServletContextListener {

    
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        context.setAttribute("EntitiesConstants", new EntitiesConstants());
        HibernateTemplet.executeSearchTemplate(NewHibernateUtil.getSessionFactory(), (FullTextSession ftxtsession)->{
        	try {
				ftxtsession.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	return null;
        });
    }

    
    public void contextDestroyed(ServletContextEvent sce) {
        NewHibernateUtil.closeSessionFactory();
    }
}
