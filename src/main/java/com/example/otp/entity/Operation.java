package com.example.otp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(unique = true)
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String otpCode;
    private boolean isVerified;
}