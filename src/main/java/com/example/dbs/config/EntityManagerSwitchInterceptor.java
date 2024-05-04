package com.example.dbs.config;

import jakarta.persistence.EntityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class EntityManagerSwitchInterceptor extends OncePerRequestFilter {

    private final DynamicEntityManagerProvider entityManagerProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String prodHeader = request.getHeader("prod");
        boolean isProd = Boolean.parseBoolean(prodHeader);

        EntityManager entityManager = entityManagerProvider.getEntityManager(isProd);
        PlatformTransactionManager transactionManager = entityManagerProvider.getPlatformTransactionManager(isProd);

        EntityManagerHolder.setCurrentEntityManager(entityManager);
        EntityManagerHolder.setPlatformTransactionManager(transactionManager);

        filterChain.doFilter(request, response);
    }
}