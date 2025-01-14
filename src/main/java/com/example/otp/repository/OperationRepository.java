package com.example.otp.repository;

import com.example.otp.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findByEmail(String email);
}