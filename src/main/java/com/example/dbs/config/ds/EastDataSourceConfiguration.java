package com.example.dbs.config.ds;

import com.example.dbs.model.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = Test.class,
        entityManagerFactoryRef = "entityManagerFactoryEast",
        transactionManagerRef = "eastTransactionManager"
 )
public class EastDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("east")
    public DataSourceProperties eastDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource eastDataSource() {
        return eastDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderEast() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean(name = "entityManagerFactoryEast")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryEast(@Qualifier("entityManagerFactoryBuilderEast") EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(eastDataSource())
                .packages(Test.class)
                .persistenceUnit("test")
                .build();
    }


    @Bean
    public PlatformTransactionManager eastTransactionManager(@Qualifier("entityManagerFactoryEast") LocalContainerEntityManagerFactoryBean entityManagerFactoryEast) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryEast.getObject()));
    }

}
