package com.spring.demo.agentdepositmanagement.module.transaction.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequest {
    private Integer id;
    private Integer agentId;
    private Long amount;
    private String type;
    private String transactionDate;
}
