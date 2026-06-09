package com.example.points_system.mapper;

import com.example.points_system.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    
    @Insert("INSERT INTO user(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
    
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param("id") Long id);
    
    @Update("UPDATE user SET total_points = total_points + #{points} WHERE id = #{id}")
    int addPoints(@Param("id") Long id, @Param("points") Integer points);
}