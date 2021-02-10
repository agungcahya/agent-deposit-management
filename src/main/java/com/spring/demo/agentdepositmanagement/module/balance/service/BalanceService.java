package com.spring.demo.agentdepositmanagement.module.balance.service;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.balance.dto.BalanceDto;
import com.spring.demo.agentdepositmanagement.module.balance.entity.BalanceEntity;
import com.spring.demo.agentdepositmanagement.module.balance.repository.BalanceRepository;
import com.spring.demo.agentdepositmanagement.module.balance.request.BalanceRequest;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    public ResponseDto getBalanceById(Integer balanceId) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<BalanceDto> balanceDtoList = new ArrayList<>();
        Optional<BalanceEntity> balanceEntity = balanceRepository.findById(balanceId);
        if (balanceEntity.isPresent()) {
            balanceDtoList.add(dozerBeanMapper.map(balanceEntity.get(), BalanceDto.class));
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(balanceDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto getBalanceByAgentId(Integer agentId) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<BalanceDto> balanceDtoList = new ArrayList<>();
        Optional<BalanceEntity> balanceEntity = balanceRepository.findByAgentId(agentId);
        if (balanceEntity.isPresent()) {
            balanceDtoList.add(dozerBeanMapper.map(balanceEntity.get(), BalanceDto.class));
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(balanceDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto registerBalance(BalanceRequest balanceRequest) {
        ResponseDto responseDto = new ResponseDto();
        List<BalanceDto> balanceDtoList = new ArrayList<>();
        BalanceEntity balanceEntity = dozerBeanMapper.map(balanceRequest, BalanceEntity.class);
        balanceDtoList.add(dozerBeanMapper.map(balanceRepository.save(balanceEntity), BalanceDto.class));
        responseDto.setCode("000");
        responseDto.setMessage("success");
        responseDto.setData(balanceDtoList);
        return responseDto;
    }
}
