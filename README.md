# 用户积分系统

一个基于 Spring Boot 的积分签到系统，用于学习 Spring Boot 相关技术。

## 技术栈

- Spring Boot 3.5.14
- MyBatis
- MySQL
- Redis
- RocketMQ
- Maven
- JDK 17

## 功能

| 功能 | 说明 |
|------|------|
| 用户注册 | 输入用户名，返回用户ID |
| 用户查询 | 根据用户ID查询用户信息和积分 |
| 每日签到 | 用户签到获得10积分，同一天不可重复签到 |

## 项目中使用到的技术点

| 技术点 | 代码位置 | 说明 |
|--------|----------|------|
| MVC | controller / service / mapper | 三层架构 |
| 依赖注入 | @Autowired | 注入 service 和 mapper |
| 事务 | PointsService.addPoints() | @Transactional 保证积分记录和用户积分同步更新 |
| AOP | TimeAspect | @Around 拦截 service 方法，打印执行耗时 |
| 拦截器 | LogInterceptor / AuthInterceptor | 日志拦截器和登录校验拦截器 |
| SQL/索引 | schema.sql | points_log 表的 idx_user_id 索引 |
| Redis 分布式锁 | SignService.dailySign() | setIfAbsent 实现签到防重复 |
| RocketMQ 生产者 | MqProducerService | 签到成功后发送消息 |
| RocketMQ 消费者 | CouponConsumer | 接收消息，模拟发放优惠券 |

## 项目结构
points-system/
├── src/main/java/com/example/points_system/
│ ├── annotation/
│ │ └── LogAnnotation.java # 自定义日志注解
│ ├── aspect/
│ │ └── TimeAspect.java # AOP 切面
│ ├── controller/
│ │ └── UserController.java # 控制器
│ ├── dto/
│ │ └── SignEventDTO.java # MQ 消息 DTO
│ ├── entity/
│ │ ├── User.java
│ │ └── PointsLog.java
│ ├── interceptor/
│ │ ├── LogInterceptor.java # 日志拦截器
│ │ ├── AuthInterceptor.java # 登录拦截器
│ │ └── WebConfig.java # 拦截器配置
│ ├── mapper/
│ │ ├── UserMapper.java
│ │ └── PointsLogMapper.java
│ ├── mq/
│ │ └── CouponConsumer.java # MQ 消费者
│ ├── service/
│ │ ├── UserService.java
│ │ ├── PointsService.java
│ │ ├── SignService.java
│ │ └── MqProducerService.java # MQ 生产者
│ └── PointsSystemApplication.java
├── src/main/resources/
│ ├── application.yml
│ ├── db/schema.sql # 建表 SQL
│ └── static/index.html # 前端页面
└── pom.xml
