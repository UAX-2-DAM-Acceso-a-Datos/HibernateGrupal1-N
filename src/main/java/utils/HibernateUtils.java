package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

	
	private static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			
			// Cargamos la configuración de hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
			
		}catch(Throwable e){
			System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
		}
		return sessionFactory;
	}
	
	
	public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
 
	/**
	 * Close the connection to the DB
	 */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
