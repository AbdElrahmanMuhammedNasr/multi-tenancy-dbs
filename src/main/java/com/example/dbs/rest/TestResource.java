package com.example.dbs.rest;

import com.example.dbs.config.EntityManagerHolder;
import com.example.dbs.model.Test;
import jakarta.persistence.EntityManager;
 import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
 import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class TestResource {


    @GetMapping("/save")
    public void test() {
        Test build = Test.builder().id((long) new Random().nextInt()).name("tamer " + new Random().nextInt()).build();
        PlatformTransactionManager transactionManager = EntityManagerHolder.getPlatformTransactionManager();
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        EntityManager currentEntityManager = EntityManagerHolder.getCurrentEntityManager();
         currentEntityManager.persist(build);
        transactionManager.commit(status);

    }


}
