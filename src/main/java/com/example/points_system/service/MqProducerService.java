package com.example.points_system.service;

import com.example.points_system.dto.SignEventDTO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MqProducerService {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    public void sendSignEvent(Long userId, Integer points, String reason) {
        // 构建消息对象
        SignEventDTO event = new SignEventDTO();
        event.setUserId(userId);
        event.setPoints(points);
        event.setReason(reason);
        event.setSignTime(LocalDateTime.now());
        event.setMessage("用户 " + userId + " 签到成功，获得 " + points + " 积分");
        
        // 发送对象消息（自动转 JSON）
        Message<SignEventDTO> message = MessageBuilder.withPayload(event).build();
        rocketMQTemplate.send("sign-topic", message);
        
        System.out.println("【MQ】发送签到事件: userId=" + userId + ", points=" + points);
    }
}