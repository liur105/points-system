package com.example.points_system.service;

import com.example.points_system.entity.PointsLog;
import com.example.points_system.mapper.PointsLogMapper;
import com.example.points_system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointsService {
    
    @Autowired
    private PointsLogMapper pointsLogMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Transactional(rollbackFor = Exception.class)
    public void addPoints(Long userId, Integer points, String reason) {
        PointsLog log = new PointsLog();
        log.setUserId(userId);
        log.setPoints(points);
        log.setReason(reason);
        pointsLogMapper.insert(log);
        
        userMapper.addPoints(userId, points);
    }
}