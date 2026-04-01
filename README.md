# nova-lite-coffee-shop

这是一个适合学习 `JDK 21 + Spring Boot 3.2+` 的轻量“模块化单体”示例，业务主题是咖啡在线点单。

## 设计目标

- 保留企业项目常见的四层结构：`interfaces / application / domain / infrastructure`
- 用一个完整小业务展示多个模块之间如何协作，而不是只看单个 `order`
- 展示统一返回、统一异常、JWT 无状态认证、多租户上下文、异步线程池、MQ 抽象
- 先用内存仓储跑通主链路，避免一开始被数据库和中间件配置分散注意力
- 代码注释以中文为主，便于从 `JDK 8` 过渡到新版本开发方式

## 当前模块

- `system`：认证与令牌发放
- `member`：会员档案、积分、等级的入口模块
- `store`：门店信息与营业信息
- `product`：咖啡商品目录
- `cart`：购物车聚合与结算前上下文
- `order`：下单主链路
- `payment`：支付记录与渠道适配入口
- `promotion`：优惠券、活动、营销能力入口
- `common`：统一返回与异常体系
- `infrastructure`：安全、租户、MQ、线程池等基础能力

## 目录结构

```text
src/main/java/com/nova/coffee
├── CoffeeShopApplication.java
├── common
│   ├── enums
│   ├── exception
│   ├── result
│   └── thread
├── infrastructure
│   ├── mq
│   ├── security
│   └── tenant
└── modules
    ├── system
    │   ├── application
    │   └── interfaces
    ├── member
    │   ├── application
    │   ├── domain
    │   ├── infrastructure
    │   └── interfaces
    ├── store
    │   ├── application
    │   ├── domain
    │   ├── infrastructure
    │   └── interfaces
    ├── product
    │   ├── application
    │   ├── domain
    │   ├── infrastructure
    │   └── interfaces
    ├── cart
    │   ├── application
    │   ├── domain
    │   ├── infrastructure
    │   └── interfaces
    ├── order
        ├── application
        ├── domain
        ├── infrastructure
        └── interfaces
    ├── payment
    │   ├── application
    │   ├── domain
    │   ├── infrastructure
    │   └── interfaces
    └── promotion
        ├── application
        ├── domain
        ├── infrastructure
        └── interfaces
```

这个结构的重点不是“拆很多包”，而是让你能看清楚：

- `common` 放真正通用的能力
- `infrastructure` 放安全、租户、MQ 这类技术实现
- `modules/*` 才是业务模块
- 每个业务模块内部继续保持 `application / domain / infrastructure / interfaces` 分层

## 当前已包含能力

- 获取测试 Token
- 查询当前会员信息
- 查询门店列表
- 查询咖啡商品列表
- 查询购物车内容
- 创建订单与查询订单
- 查询订单对应的支付记录
- 查询可用优惠券
- 验证 MySQL、Redis、Redisson 基础连通性
- `Result<T>` 统一响应体
- 业务异常与全局异常处理
- 基于请求头的租户上下文提取
- 基于 JWT 的简单认证过滤器
- 自定义线程池 + `@Async` 异步通知示例
- MQ 统一接口抽象
- Prometheus 指标暴露
- Docker Compose 中间件样例

## 当前刻意简化

- 目前只有 `product` 模块先接入了 MyBatis-Plus，其他模块仍保留内存仓储，便于逐步学习
- MQ 当前默认打印日志模拟投递
- 多租户先演示“共享库共享表 + tenantId”的上下文设计思路
- 认证先使用固定演示用户，便于快速体验完整链路

## 推荐学习顺序

1. 先看启动类与目录结构，理解模块化单体怎么组织
2. 再看 `system / member / store / product / cart / order / payment / promotion` 这些模块分别管什么
3. 再看 `SecurityConfig`、`JwtAuthenticationFilter` 理解新版 Spring Security 写法
4. 再看 `TenantHeaderInterceptor` 和 `TenantContext` 理解租户上下文透传
5. 最后看 `AsyncTaskConfig`、`OrderAsyncNotifier` 与 MQ 抽象，理解并发与异步扩展点

## 启动前提

- 需要本机安装 `JDK 21`
- 需要本机安装 `Maven 3.9+`

## 启动命令

```bash
mvn spring-boot:run
```

## 本地中间件

先启动 MySQL 和 Redis：

```bash
docker compose -f docker/docker-compose.yml up -d mysql redis
```

再执行初始化 SQL：

```bash
mysql -uroot -proot nova_lite < doc/sql/init.sql
```

## 推荐体验顺序

1. 调用 `POST /api/system/auth/token` 获取测试令牌
2. 带上 `Authorization: Bearer <token>` 调用 `GET /api/products`
3. 调用 `GET /api/members/me`、`GET /api/stores`、`GET /api/carts/current` 观察基础模块
4. 带上 `X-Tenant-Id: demo-tenant` 和 token 调用 `POST /api/orders`
5. 使用返回的 `orderNo` 调用 `GET /api/orders/{orderNo}` 和 `GET /api/payments/orders/{orderNo}`
6. 调用 `GET /api/promotions/coupons` 查看营销模块入口
7. 调用 `GET /api/system/infra/status` 验证 MySQL、Redis、Redisson 是否已经连通

## 默认请求头

- `X-Tenant-Id`: 租户编号，示例值 `demo-tenant`
- `Authorization`: `Bearer <jwt>`

后续如果你认可这套风格，我可以继续帮你把它升级到：

- 购物车、订单、支付表的 MyBatis-Plus 落库
- Redis 缓存与购物车缓存模型
- 购物车真实增删改
- 支付下单与回调链路
- 用户、门店、优惠券的数据库持久化
- RabbitMQ / RocketMQ 适配
- 多数据源
- 真正可运行的多租户 SQL 拦截
