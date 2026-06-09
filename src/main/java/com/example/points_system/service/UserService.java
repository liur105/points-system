package com.example.points_system.service;

import com.example.points_system.entity.User;
import com.example.points_system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public Long register(String name) {
        User user = new User();
        user.setName(name);
        userMapper.insert(user);
        return user.getId();
    }
    
    public User getUser(Long id) {
        return userMapper.selectById(id);
    }
}