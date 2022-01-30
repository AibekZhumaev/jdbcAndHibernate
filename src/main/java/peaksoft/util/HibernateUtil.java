package peaksoft.util;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import peaksoft.model.User;

import java.util.Properties;

/**
 * @author Zha_Aibek@mail.com
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                //settings
                Properties settings = new Properties();
                settings.load(ClassLoader.getSystemClassLoader().
                        getResourceAsStream("hibernate.properties"));

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return sessionFactory;
    }
    public static void shutDown() {
        getSessionFactory().close();
    }
}
