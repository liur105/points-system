package com.example.points_system.controller;

import com.example.points_system.annotation.LogAnnotation;
import com.example.points_system.entity.User;
import com.example.points_system.service.SignService;
import com.example.points_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SignService signService;
    
    @PostMapping("/register")
    @LogAnnotation(module = "用户模块", value = "用户注册")
    public String register(@RequestParam String name) {
        Long userId = userService.register(name);
        return "注册成功，用户ID: " + userId;
    }
    
    @GetMapping("/{id}")
    @LogAnnotation(module = "用户模块", value = "查询用户信息")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
    
    @PostMapping("/sign")
    @LogAnnotation(module = "签到模块", value = "每日签到")
    public String sign(@RequestParam Long userId) {
        return signService.dailySign(userId);
    }
}