package com.example.dbs.config;


import jakarta.persistence.EntityManager;
import org.springframework.transaction.PlatformTransactionManager;

public class EntityManagerHolder {

    private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<>();
    private static final ThreadLocal<PlatformTransactionManager> currentPlatformTransactionManager = new ThreadLocal<>();

    public static EntityManager getCurrentEntityManager() {
        return currentEntityManager.get();
    }

    public static void setCurrentEntityManager(EntityManager entityManager) {
        currentEntityManager.set(entityManager);
    }

    public static PlatformTransactionManager getPlatformTransactionManager() {
        return currentPlatformTransactionManager.get();
    }

    public static void setPlatformTransactionManager(PlatformTransactionManager PlatformTransactionManager) {
        currentPlatformTransactionManager.set(PlatformTransactionManager);
    }



}
