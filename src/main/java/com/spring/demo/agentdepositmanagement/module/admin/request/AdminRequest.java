package com.spring.demo.agentdepositmanagement.module.admin.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminRequest {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
