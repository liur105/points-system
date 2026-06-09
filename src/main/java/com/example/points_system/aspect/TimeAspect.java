package com.example.points_system.aspect;

import com.example.points_system.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class TimeAspect {

    // 定义切点：带有自定义注解的方法
    @Pointcut("@annotation(logAnnotation)")
    public void logPointcut(LogAnnotation logAnnotation) {
    }

    // 环绕通知
    @Around("logPointcut(logAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, LogAnnotation logAnnotation) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String module = logAnnotation.module();
        String value = logAnnotation.value();
        
        // 前置通知
        System.out.println("========== AOP 开始 ==========");
        System.out.println("【模块】" + module);
        System.out.println("【描述】" + value);
        System.out.println("【方法】" + methodName);
        System.out.println("【参数】" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("【时间】" + LocalDateTime.now());
        
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();
        
        long time = System.currentTimeMillis() - start;
        
        // 后置通知
        System.out.println("【耗时】" + time + "ms");
        System.out.println("【结果】" + result);
        System.out.println("========== AOP 结束 ==========");
        
        return result;
    }
}