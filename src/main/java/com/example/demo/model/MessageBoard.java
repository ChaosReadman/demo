package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class messageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(columnDefinition="VARCHAR(128) default ''")
    private String userName;

    @Column(columnDefinition="VARCHAR(512) default ''")
    private String message;

    @Column(columnDefinition="VARCHAR(128) default 'SYSTEM'")
    private String lastUpdateUser;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUpdateDate;
}
