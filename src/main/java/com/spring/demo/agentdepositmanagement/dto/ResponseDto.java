package com.spring.demo.agentdepositmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
public class ResponseDto<T> {
    private String code;
    private String message;

    @Nullable
    private List<T> data;
}
