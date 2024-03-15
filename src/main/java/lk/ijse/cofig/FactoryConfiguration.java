package lk.ijse.cofig;


import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

    private FactoryConfiguration() {

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.loadProperties("hibernate.properties");
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Borrowing.class)
                .addAnnotatedClass(BorrowingDetail.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(User.class);
        Metadata build = metadataSources.getMetadataBuilder().build();

        sessionFactory = build.getSessionFactoryBuilder().build();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
