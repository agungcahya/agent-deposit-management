package com.spring.demo.agentdepositmanagement.module.balance.controller;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.balance.request.BalanceRequest;
import com.spring.demo.agentdepositmanagement.module.balance.service.BalanceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/balance-management")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping(value = "/balance/{balanceId}")
    public ResponseEntity<ResponseDto> getBalanceById(@PathVariable Integer balanceId) throws NotFoundException {
        return ResponseEntity.ok(balanceService.getBalanceById(balanceId));
    }

    @GetMapping(value = "/balance/agent/{agentId}")
    public ResponseEntity<ResponseDto> getBalanceByAgentId(@PathVariable Integer agentId) throws NotFoundException {
        return ResponseEntity.ok(balanceService.getBalanceByAgentId(agentId));
    }

    @PostMapping(value = "/balance/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDto> registerBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(balanceService.registerBalance(balanceRequest));
    }
}
