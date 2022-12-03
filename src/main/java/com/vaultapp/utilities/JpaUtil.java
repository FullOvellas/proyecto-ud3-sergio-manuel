package com.vaultapp.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton for EntityManagerFactory generation. Every instance of EntityManager
 * is provided for a single instance of EntityManagerFactory.
 *
 * @author Manuel Landín Gómez
 * @author Sergio Alonso Pazo
 */
public class JpaUtil {
    private static final EntityManagerFactory entityManagerrFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("manuel_home_unit");
    }

    /**
     * Generate an EntityManager connection instance.
     *
     * @return the EntityManager instance.
     */
    public static EntityManager getEntityManager() {
        return entityManagerrFactory.createEntityManager();
    }
}
