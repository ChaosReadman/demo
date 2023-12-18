package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(columnDefinition="VARCHAR(128) default ''")
    private String userName;

    @Column(columnDefinition="VARCHAR(512) default ''")
    private String password;

    @Column(columnDefinition="VARCHAR(128) default ''")
    private String nickName;

    @Column(columnDefinition="INT default 0")
    private int age;

    @Column(columnDefinition = "VARCHAR(8) default '00000001'")
    private String privileges;

    @Column(columnDefinition="VARCHAR(128) default 'SYSTEM'")
    private String lastUpdateUser;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUpdateDate;
}
