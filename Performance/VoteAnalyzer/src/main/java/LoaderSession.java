import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class LoaderSession {

    public static Session startSession(Session session) {
        if(session != null) {
            return session;
        }
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
        session = sessionFactory.openSession();
        return session;
    }

    public static void closeLoaderSession(Session session) {
        if(session != null) {
            session.close();
            session.getSessionFactory().close();
        }
    }
}
