package com.example.points_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignEventDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long userId;
    private Integer points;
    private String reason;
    private LocalDateTime signTime;
    private String message;
}