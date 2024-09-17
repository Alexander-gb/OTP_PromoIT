package com.example.otp.controller;

import com.example.otp.dto.OTPCodeRequest;
import com.example.otp.dto.OperationRequest;
import com.example.otp.entity.Operation;
import com.example.otp.service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody OperationRequest request) {
        Operation operation = operationService.createOperation(request.getDescription(), request.getEmail());
        return ResponseEntity.ok(operation);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyOTPCode(@RequestBody OTPCodeRequest request) {
        boolean isValid = operationService.verifyOTPCode(request.getEmail(), request.getCode());
        return ResponseEntity.ok(isValid);
    }
}