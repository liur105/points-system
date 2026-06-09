package com.example.points_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;

@Service
public class SignService {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private PointsService pointsService;
    
    @Autowired
    private MqProducerService mqProducerService;
    
    public String dailySign(Long userId) {
        String lockKey = "sign:lock:" + userId + ":" + LocalDate.now();
        
        Boolean locked = redisTemplate.opsForValue()
            .setIfAbsent(lockKey, "1", Duration.ofDays(1));
        
        if (Boolean.FALSE.equals(locked)) {
            return "今天已经签到过了，明天再来吧";
        }
        
        try {
            pointsService.addPoints(userId, 10, "每日签到");
            mqProducerService.sendSignEvent(userId, 10, "每日签到");
            return "签到成功！获得10积分";
        } finally {
            redisTemplate.delete(lockKey);
        }
    }
}