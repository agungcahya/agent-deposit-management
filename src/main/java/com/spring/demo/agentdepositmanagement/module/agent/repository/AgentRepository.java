package com.spring.demo.agentdepositmanagement.module.agent.repository;

import com.spring.demo.agentdepositmanagement.module.agent.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<AgentEntity,Integer> {
    Optional<AgentEntity> findById(Integer id);

    Optional<AgentEntity> findByEmailAndPassword(String email, String password);
}
