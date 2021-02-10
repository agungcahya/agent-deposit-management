package com.spring.demo.agentdepositmanagement.module.balance.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BalanceRequest {
    private Integer id;
    private Integer agentId;
    private Long balance;
}
