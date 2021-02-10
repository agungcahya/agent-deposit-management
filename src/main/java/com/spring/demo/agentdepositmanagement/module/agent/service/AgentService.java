package com.spring.demo.agentdepositmanagement.module.agent.service;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.agent.dto.AgentDto;
import com.spring.demo.agentdepositmanagement.module.agent.entity.AgentEntity;
import com.spring.demo.agentdepositmanagement.module.agent.repository.AgentRepository;
import com.spring.demo.agentdepositmanagement.module.agent.request.AgentRequest;
import com.spring.demo.agentdepositmanagement.module.balance.entity.BalanceEntity;
import com.spring.demo.agentdepositmanagement.module.balance.repository.BalanceRepository;
import com.spring.demo.agentdepositmanagement.module.balance.service.BalanceService;
import com.spring.demo.agentdepositmanagement.request.LoginRequest;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    public ResponseDto getAgentById(Integer agentId) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<AgentDto> agentDtoList = new ArrayList<>();
        Optional<AgentEntity> agentEntity = agentRepository.findById(agentId);
        if (agentEntity.isPresent()) {
            agentDtoList.add(dozerBeanMapper.map(agentEntity.get(), AgentDto.class));
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(agentDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto loginAgent(LoginRequest loginRequest) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<AgentDto> agentDtoList = new ArrayList<>();
        Optional<AgentEntity> agentEntity = agentRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (agentEntity.isPresent()) {
            agentDtoList.add(dozerBeanMapper.map(agentEntity.get(), AgentDto.class));
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(agentDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto registerAgent(AgentRequest agentRequest) {
        ResponseDto responseDto = new ResponseDto();
        List<AgentDto> agentDtoList = new ArrayList<>();
        AgentEntity agentEntity = dozerBeanMapper.map(agentRequest, AgentEntity.class);
        agentDtoList.add(dozerBeanMapper.map(agentRepository.save(agentEntity), AgentDto.class));
        if (agentRequest.getId() == null) {
            BalanceEntity balanceEntity = new BalanceEntity();
            balanceEntity.setAgentId(agentEntity.getId());
            balanceEntity.setBalance(0L);
            balanceRepository.save(balanceEntity);
        }
        responseDto.setCode("000");
        responseDto.setMessage("success");
        responseDto.setData(agentDtoList);
        return responseDto;
    }
}
