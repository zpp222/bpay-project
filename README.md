# bpay-project

>## spring boot/cloud
### 1.dubbo for service and console.
### 2.eureka for console and client with eureka.
### 3.Hystrix for client

>## db
### 1.mysql relation db for service.
### 2.mongodb document store for store.
### 3.redis\hezalcast key/value for service.

>## Messaging
### 1. kafka for store with service.

>## Cloud Routing
### 1.Spring Cloud Gateway(NIO)
### 2.zuul

>## OAuth2
### 1.OAuth2 for sso-provider 
### 2.client for sso-client
### 2.zuul for sso-resource

>## batch+quartz
### 1. bpay-batch for batch job.

## 架构图
```mermaid
sequenceDiagram
client/user ->> gateway/zuul: 接口请求
gateway/zuul -->> oauth2: 是否有效令牌?
oauth2 -->> gateway/zuul: 否
oauth2 -->> client/user: 登陆页面
client/user ->> oauth2: 登陆请求
oauth2 ->> console: 接口请求
console ->> service: 远程服务请求
service -->> client/user: 响应结果
Note right of oauth2: sso 单点登陆
```
```
```
```mermaid
sequenceDiagram
service ->> store: 异步消息
store -->> batch: 数据加工
```