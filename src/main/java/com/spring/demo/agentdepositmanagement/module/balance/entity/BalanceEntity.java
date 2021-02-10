package com.spring.demo.agentdepositmanagement.module.balance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "balance")
@Entity
@Getter
@Setter
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "balance")
    private Long balance;

}
