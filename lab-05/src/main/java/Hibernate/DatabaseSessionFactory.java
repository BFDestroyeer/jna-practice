package Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import AlarmClock.SimpleAlarmClock;
import AlarmClock.AdvancedAlarmClock;

public class DatabaseSessionFactory {
    private static SessionFactory sessionFactory;

    private DatabaseSessionFactory() {

    }

    public static SessionFactory get() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
                configuration.addAnnotatedClass(SimpleAlarmClock.class);
                configuration.addAnnotatedClass(AdvancedAlarmClock.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            };
        }
        return sessionFactory;
    }
}
