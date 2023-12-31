package com.example.demo.model;

import java.util.Date;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(columnDefinition="VARCHAR(128) default ''")
    @NotBlank
    private String title;

    @Column(columnDefinition="VARCHAR(1024) default ''")
    @URL
    private String url;

    @Column(columnDefinition="VARCHAR(128) default 'SYSTEM'")
    private String lastUpdateUser;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUpdateDate;
}
