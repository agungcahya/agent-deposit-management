package com.spring.demo.agentdepositmanagement.module.agent.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgentDto {

    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
}
