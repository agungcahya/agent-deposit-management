package com.spring.demo.agentdepositmanagement.module.transaction.controller;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.transaction.request.TransactionRequest;
import com.spring.demo.agentdepositmanagement.module.transaction.service.TransactionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/transaction-management")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transaction/{transactionId}")
    public ResponseEntity<ResponseDto> getTransactionById(@PathVariable Integer transactionId) throws NotFoundException {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }

    @GetMapping(value = "/transaction/agent/{agentId}")
    public ResponseEntity<ResponseDto> getTransactionByAgentId(@RequestParam Integer agentId, @RequestParam @Nullable String type) throws NotFoundException {
        return ResponseEntity.ok(transactionService.getTransactionByAgentId(agentId, type));
    }

//    @GetMapping(value = "/transaction/topup/agent/{agentId}")
//    public ResponseEntity<ResponseDto> getTopupTransactionByAgentId(@PathVariable Integer agentId) throws NotFoundException {
//        return ResponseEntity.ok(transactionService.getTransactionByAgentId(agentId));
//    }
//
//    @GetMapping(value = "/transaction/purchase/{agentId}")
//    public ResponseEntity<ResponseDto> getPurchaseTransactionByAgentId(@PathVariable Integer agentId) throws NotFoundException {
//        return ResponseEntity.ok(transactionService.getTransactionByAgentId(agentId));
//    }

    @PostMapping(value = "/transaction/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDto> registerTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.registerTransaction(transactionRequest));
    }
}
