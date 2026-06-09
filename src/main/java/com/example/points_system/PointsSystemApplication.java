package com.example.points_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.points_system.mapper")
public class PointsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointsSystemApplication.class, args);
    }

}