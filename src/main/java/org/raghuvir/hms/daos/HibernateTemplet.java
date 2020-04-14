package org.raghuvir.hms.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

public class HibernateTemplet {

	public interface SearchingCode {
		Object execute(FullTextSession ftxtsession);
	}

	public interface ABlockOfCode {
        Object execute(Session session);
    }
	
	public static synchronized Object executeTemplate(SessionFactory factory, ABlockOfCode code) {
		Session session;
		session = factory.openSession();
		Transaction tx = null;
		Object returnvalue = null;
		try {
			tx = session.beginTransaction();

			returnvalue = code.execute(session);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
			session.close();
		}
		return returnvalue;
	}

	public static synchronized Object executeSearchTemplate(SessionFactory factory, SearchingCode code) {
		Session session;
		session = factory.openSession();
		Transaction tx = null;
		Object returnvalue = null;

		try {
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			tx = fullTextSession.beginTransaction();
			
			returnvalue = code.execute(fullTextSession);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
			session.close();
		}
		return returnvalue;
	}
}
