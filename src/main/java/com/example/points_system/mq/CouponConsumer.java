package com.example.points_system.mq;

import com.example.points_system.dto.SignEventDTO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
    topic = "sign-topic",
    consumerGroup = "coupon-group"
)
public class CouponConsumer implements RocketMQListener<SignEventDTO> {
    
    @Override
    public void onMessage(SignEventDTO event) {
        System.out.println("========== 收到签到事件 ==========");
        System.out.println("用户ID: " + event.getUserId());
        System.out.println("获得积分: " + event.getPoints());
        System.out.println("签到时间: " + event.getSignTime());
        System.out.println("消息内容: " + event.getMessage());
        System.out.println("========== 发放优惠券给用户 ==========");
        // 实际业务：调用优惠券系统
    }
}