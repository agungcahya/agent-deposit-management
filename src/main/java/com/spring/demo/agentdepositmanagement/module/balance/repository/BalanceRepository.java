package com.spring.demo.agentdepositmanagement.module.balance.repository;

import com.spring.demo.agentdepositmanagement.module.balance.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<BalanceEntity,Integer> {
    Optional<BalanceEntity> findById(Integer id);

    Optional<BalanceEntity> findByAgentId(Integer id);
}
