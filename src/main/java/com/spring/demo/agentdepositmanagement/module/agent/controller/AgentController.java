package com.spring.demo.agentdepositmanagement.module.agent.controller;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.agent.dto.AgentDto;
import com.spring.demo.agentdepositmanagement.module.agent.request.AgentRequest;
import com.spring.demo.agentdepositmanagement.module.agent.service.AgentService;
import com.spring.demo.agentdepositmanagement.request.LoginRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/agent-management")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping(value = "/agent/{agentId}")
    public ResponseEntity<ResponseDto> getAgentById(@PathVariable Integer agentId) throws NotFoundException {
        return ResponseEntity.ok(agentService.getAgentById(agentId));
    }

    @PostMapping(value = "/agent/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDto> agentLogin(@RequestBody LoginRequest loginRequest) throws NotFoundException {
        return ResponseEntity.ok(agentService.loginAgent(loginRequest));
    }

    @PostMapping(value = "/agent/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDto> registerAgent(@RequestBody AgentRequest agentRequest) {
        return ResponseEntity.ok(agentService.registerAgent(agentRequest));
    }
}
