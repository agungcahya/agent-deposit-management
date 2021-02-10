package com.spring.demo.agentdepositmanagement.module.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "transaction")
@Entity
@Getter
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "type")
    private String type;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public void setTransactionDate(String transactionDate) {
        LocalDateTime date = LocalDateTime.parse(transactionDate);
        this.transactionDate = date;
    }
}
