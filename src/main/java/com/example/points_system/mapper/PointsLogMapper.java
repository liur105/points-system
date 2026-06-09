package com.example.points_system.mapper;

import com.example.points_system.entity.PointsLog;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PointsLogMapper {
    
    @Insert("INSERT INTO points_log(user_id, points, reason) VALUES(#{userId}, #{points}, #{reason})")
    int insert(PointsLog pointsLog);
}