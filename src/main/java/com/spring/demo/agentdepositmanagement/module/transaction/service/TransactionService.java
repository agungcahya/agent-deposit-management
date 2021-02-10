package com.spring.demo.agentdepositmanagement.module.transaction.service;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.balance.entity.BalanceEntity;
import com.spring.demo.agentdepositmanagement.module.balance.repository.BalanceRepository;
import com.spring.demo.agentdepositmanagement.module.transaction.dto.TransactionDto;
import com.spring.demo.agentdepositmanagement.module.transaction.entity.TransactionEntity;
import com.spring.demo.agentdepositmanagement.module.transaction.repository.TransactionRepository;
import com.spring.demo.agentdepositmanagement.module.transaction.request.TransactionRequest;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    public ResponseDto getTransactionById(Integer balanceId) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        Optional<TransactionEntity> transactionEntity = transactionRepository.findById(balanceId);
        if (transactionEntity.isPresent()) {
            transactionDtoList.add(dozerBeanMapper.map(transactionEntity.get(), TransactionDto.class));
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(transactionDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto getTransactionByAgentId(Integer agentId, String type) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<TransactionDto> transactionDtoList;
        List<TransactionEntity> transactionEntityList;
        if (type == null) {
            transactionEntityList = transactionRepository.findByAgentIdOrderByTransactionDateDesc(agentId);
        } else {
            transactionEntityList = transactionRepository.findByAgentIdAndTypeOrderByTransactionDateDesc(agentId, type);
        }
        if (!transactionEntityList.isEmpty()) {
            transactionDtoList = transactionEntityList.stream().map(transactionEntity -> dozerBeanMapper.map(transactionEntity, TransactionDto.class)).collect(Collectors.toList());
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(transactionDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto registerTransaction(TransactionRequest transactionRequest) {
        ResponseDto responseDto = new ResponseDto();
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        TransactionEntity transactionEntity = dozerBeanMapper.map(transactionRequest, TransactionEntity.class);
        transactionEntity.setTransactionDate(LocalDateTime.now().toString());
        transactionDtoList.add(dozerBeanMapper.map(transactionRepository.save(transactionEntity), TransactionDto.class));

        Optional<BalanceEntity> balanceEntity = balanceRepository.findByAgentId(transactionEntity.getAgentId());
        BalanceEntity balance = new BalanceEntity();
        if (balanceEntity.isPresent()) {
            balance = balanceEntity.get();
            switch (transactionEntity.getType()) {
                case "topup":
                    balance.setBalance(balance.getBalance() + transactionEntity.getAmount());
                    break;
                case "purchase":
                    balance.setBalance(balance.getBalance() - transactionEntity.getAmount());
                    break;
            }
        }
        balanceRepository.save(balance);
        responseDto.setCode("000");
        responseDto.setMessage("success");
        responseDto.setData(transactionDtoList);
        return responseDto;
    }
}
