package br.com.customerAccount.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author miguel
 * <p>
 * Classe utilizada para instanciar singleton de acesso ao BD
 */
public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer");

    /**
     * @return Instancia unica de entity manager
     */
    public EntityManager getEntityManager() {
        try {

            return entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
