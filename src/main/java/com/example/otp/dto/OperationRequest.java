package com.example.otp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationRequest {
    private String description;
    private String email;
}