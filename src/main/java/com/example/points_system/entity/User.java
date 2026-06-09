package com.example.points_system.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private Integer totalPoints;
    private Date createTime;
}