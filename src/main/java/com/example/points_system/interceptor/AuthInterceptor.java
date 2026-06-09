package com.example.points_system.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        
        System.out.println("【AuthInterceptor】请求路径: " + path);
        
        // 放行注册接口
        if ("/user/register".equals(path)) {
            System.out.println("【AuthInterceptor】放行注册接口");
            return true;
        }
        
        // 放行查询用户接口（用于登录验证）- 使用 startsWith 更简单
        if (path.startsWith("/user/") && !path.equals("/user/sign")) {
            // 检查是否是数字ID（/user/5 这种格式）
            String suffix = path.substring("/user/".length());
            if (suffix.matches("\\d+")) {
                System.out.println("【AuthInterceptor】放行查询用户接口: " + path);
                return true;
            }
        }
        
        // 放行静态资源和首页
        if (path.equals("/") || path.equals("/index.html") || path.startsWith("/static/")) {
            System.out.println("【AuthInterceptor】放行静态资源");
            return true;
        }
        
        // 其他 /user/** 接口需要登录
        String userId = request.getHeader("X-UserId");
        
        if (userId == null || userId.isEmpty()) {
            System.out.println("【AuthInterceptor】未登录，拒绝访问: " + path);
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"请先登录\"}");
            return false;
        }
        
        request.setAttribute("userId", userId);
        System.out.println("【AuthInterceptor】登录校验通过，userId=" + userId);
        return true;
    }
}