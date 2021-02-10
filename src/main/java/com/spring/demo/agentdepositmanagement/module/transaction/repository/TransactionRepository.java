package com.spring.demo.agentdepositmanagement.module.transaction.repository;

import com.spring.demo.agentdepositmanagement.module.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer> {
    Optional<TransactionEntity> findById(Integer id);

    List<TransactionEntity> findByAgentIdOrderByTransactionDateDesc(Integer id);

    List<TransactionEntity> findByAgentIdAndTypeOrderByTransactionDateDesc(Integer id, String type);

}
