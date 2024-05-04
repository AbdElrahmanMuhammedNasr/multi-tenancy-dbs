package com.example.dbs.config;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class DynamicEntityManagerProvider {

    private final EntityManager entityManagerFactoryEast;
    private final EntityManager entityManagerFactoryWest;

    private final   PlatformTransactionManager westTransactionManager;
    private final   PlatformTransactionManager eastTransactionManager;

    public DynamicEntityManagerProvider(@Qualifier("entityManagerFactoryEast") EntityManager entityManagerFactoryEast,
                                        @Qualifier("entityManagerFactoryWest") EntityManager entityManagerFactoryWest,
                                        @Qualifier("westTransactionManager") PlatformTransactionManager westTransactionManager,
                                        @Qualifier("eastTransactionManager")  PlatformTransactionManager eastTransactionManager) {
        this.entityManagerFactoryEast = entityManagerFactoryEast;
        this.entityManagerFactoryWest = entityManagerFactoryWest;
        this.westTransactionManager = westTransactionManager;
        this.eastTransactionManager = eastTransactionManager;
    }

    public EntityManager getEntityManager(boolean isProd) {
        return isProd ? entityManagerFactoryEast : entityManagerFactoryWest;
    }
    public PlatformTransactionManager getPlatformTransactionManager(boolean isProd) {
        return isProd ? eastTransactionManager : westTransactionManager;
    }
}
