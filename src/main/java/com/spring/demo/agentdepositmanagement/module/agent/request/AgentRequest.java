package com.spring.demo.agentdepositmanagement.module.agent.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgentRequest {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
}
