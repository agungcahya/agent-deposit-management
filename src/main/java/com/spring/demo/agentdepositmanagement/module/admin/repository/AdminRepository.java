package com.spring.demo.agentdepositmanagement.module.admin.repository;

import com.spring.demo.agentdepositmanagement.module.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
    Optional<AdminEntity> findById(Integer id);
}
