package org.raghuvir.hms.utils;

import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.Stoppable;

public class NewHibernateUtil {

	private static final SessionFactory sessionFactory;
	private static StandardServiceRegistry serviceRegistry;
	static {
		try {
			Configuration cf = new Configuration().configure("org/raghuvir/hms/utils/hibernate.cfg.xml");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cf.getProperties()).build();
			sessionFactory = cf.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.print("Initial SessionFactory creation failed ====> ");
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (serviceRegistry != null) {
			sessionFactory.close();
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
		}
        ConnectionProvider connectionProvider = ((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
        if(Stoppable.class.isInstance(connectionProvider))
        {((Stoppable)connectionProvider).stop();}
	}

}
