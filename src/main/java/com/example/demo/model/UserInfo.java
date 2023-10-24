package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private Integer age;
}
