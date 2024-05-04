package com.example.dbs.config.ds;

import com.example.dbs.model.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = Test.class,
        entityManagerFactoryRef = "entityManagerFactoryWest",
        transactionManagerRef = "westTransactionManager"
)
public class WestDataSourceConfiguration {



    @Bean
    @ConfigurationProperties("west")
    public DataSourceProperties westDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource westDataSource() {
        return westDataSourceProperties().initializeDataSourceBuilder().build();
    }



    @Bean(name = "entityManagerFactoryWest")
    public LocalContainerEntityManagerFactoryBean westEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(westDataSource())
                . packages(Test.class)
                 .build();
    }

    @Bean
    public PlatformTransactionManager westTransactionManager(@Qualifier("entityManagerFactoryWest") LocalContainerEntityManagerFactoryBean westEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(westEntityManagerFactory.getObject()));
    }

}
