package com.example.dbs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Test {

    @Id
    private Long id;
    private String name;


//    private LocalDateTime accountCreatedTime;
//    private LocalDateTime accountLastUpdatedTime;

//    @PrePersist
//    private void beforePersist() {
//
//        System.out.println("I\'m New");
//        LocalDateTime dateTime = LocalDateTime.now();
//        setAccountCreatedTime(dateTime);
//        setAccountLastUpdatedTime(dateTime);
//    }
}
