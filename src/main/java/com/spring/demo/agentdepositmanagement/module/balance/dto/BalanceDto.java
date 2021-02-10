package com.spring.demo.agentdepositmanagement.module.balance.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BalanceDto {

    private Integer id;
    private Integer agentId;
    private Long balance;
}
