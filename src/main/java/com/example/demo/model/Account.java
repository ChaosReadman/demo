package com.example.demo.model;

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
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;
}
