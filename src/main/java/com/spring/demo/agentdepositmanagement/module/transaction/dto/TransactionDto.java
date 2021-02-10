package com.spring.demo.agentdepositmanagement.module.transaction.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class TransactionDto {

    private Integer id;
    private Integer agentId;
    private Long amount;
    private String type;
    private String transactionDate;
}
