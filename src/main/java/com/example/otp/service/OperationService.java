package com.example.otp.service;


import com.example.otp.entity.Operation;
import com.example.otp.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final EmailService emailService;

    public OperationService(OperationRepository operationRepository, EmailService emailService) {
        this.operationRepository = operationRepository;
        this.emailService = emailService;
    }


    @Value("${otp.code.lifetime.minutes}")
    private int codeLifetimeMinutes;

    @Value("${otp.code.length}")
    private int codeLength;

    public Operation createOperation(String description, String email) {
        Operation operation = new Operation();
        operation.setDescription(description);
        operation.setEmail(email);
        operation.setCreatedAt(LocalDateTime.now());
        operation.setExpiresAt(LocalDateTime.now().plusMinutes(codeLifetimeMinutes));
        operation.setOtpCode(generateRandomCode(codeLength));
        operation.setVerified(false);

        operationRepository.save(operation);

        // Отправка кода по email
        emailService.sendOTPCode(email, operation.getOtpCode());

        return operation;
    }

    public Optional<Operation> getOperationByEmail(String email) {
        return operationRepository.findByEmail(email);
    }

    public boolean verifyOTPCode(String email, String code) {
        Optional<Operation> operationOptional = operationRepository.findByEmail(email);
        if (operationOptional.isPresent()) {
            Operation operation = operationOptional.get();
            if (operation.getOtpCode().equals(code) && operation.getExpiresAt().isAfter(LocalDateTime.now())) {
                operation.setVerified(true);
                operationRepository.save(operation);
                return true;
            }
        }
        return false;
    }

    private String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
