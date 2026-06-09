package com.example.points_system.entity;

import lombok.Data;
import java.util.Date;

@Data
public class PointsLog {
    private Long id;
    private Long userId;
    private Integer points;
    private String reason;
    private Date createTime;
}