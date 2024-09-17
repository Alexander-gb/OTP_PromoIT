package com.example.otp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPCodeRequest {
    private String email;
    private String code;
}