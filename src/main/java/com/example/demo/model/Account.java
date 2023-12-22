package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(columnDefinition="VARCHAR(128) default ''")
    @NotBlank
    private String userName;

    @Column(columnDefinition="VARCHAR(512) default ''")
    private String password;

    @Column(columnDefinition="VARCHAR(16) default ''")
    @Pattern(regexp="^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$")
    private String orgPassword;

    @Column(columnDefinition="VARCHAR(128) default ''")
    @NotBlank
    private String nickName;

    @Column(columnDefinition="INT default 0")
    @NotNull
    private Integer age;

    @Column(columnDefinition = "VARCHAR(8) default '00000001'")
    @NotBlank
    private String privileges;

    @Column(columnDefinition="VARCHAR(128) default 'SYSTEM'")
    private String lastUpdateUser;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUpdateDate;
}
