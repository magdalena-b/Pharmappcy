package wt.muppety.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionService {

    private static final SessionFactory sessionFactory = new Configuration().configure()
            .buildSessionFactory();

    private static Session session;

    public static void openSession() {
        session = sessionFactory.openSession();
    }

    public static Session getSession() {
        if (SessionService.session == null) {
            SessionService.openSession();
        }
        return session;
    }

    public static void closeSession() {
        if (session != null)
            session.close();
    }
}