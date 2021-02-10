package com.spring.demo.agentdepositmanagement.module.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "admin")
@Entity
@Getter
@Setter
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}
