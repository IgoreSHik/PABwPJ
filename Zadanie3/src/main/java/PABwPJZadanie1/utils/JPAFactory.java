package PABwPJZadanie1.utils;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JPAFactory {

    private EntityManagerFactory EMF;
    private static JPAFactory instance;

    private JPAFactory() {
        EMF = Persistence.createEntityManagerFactory("PU");
    }

    public static JPAFactory getInstanance() {
        if (instance == null) {
            instance = new JPAFactory();
        }
        return instance;
    }

    public static EntityManager getEntityManager() {
        return getInstanance().EMF.createEntityManager();
    }

}