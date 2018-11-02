# bpay-project

>## spring boot/cloud
#### 1.dubbo for service and console.
#### 2.eureka for console and client with eureka.
#### 3.Hystrix for client

>## db
#### 1.mysql relation db for service.
#### 2.mongodb document store for store.
#### 3.redis\hezalcast key/value for service.

>## Messaging
#### 1. kafka for store with service.

>## Cloud Routing
#### 1.Spring Cloud Gateway(NIO)
#### 2.zuul

>## OAuth2
#### 1.OAuth2 for sso-provider 
#### 2.client for sso-client
#### 3.zuul for sso-resource

>## batch+quartz
#### 1. bpay-batch for batch job.

## 架构图
> URL API方式
```mermaid
sequenceDiagram
api ->> zuul: 接口请求
zuul ->> oauth2: 是否有效令牌？
oauth2 -->> zuul: 令牌无效(拒绝)
zuul ->> console: 令牌有效-http请求
console ->> service: dubbo远程服务请求
service -->> api: 响应结果
service -->> store: 消息流
store -->> batch: 批量数据加工
Note right of oauth2: sso 单点登陆
```
> browser 方式
```mermaid
sequenceDiagram
browser ->> client: 请求
client ->> oauth2: 是否有效令牌？
oauth2 -->> client: 令牌无效(跳转登陆页面)
client ->> gateway: 令牌有效
gateway ->> console: http请求
console ->> service: dubbo远程服务请求
service -->> browser: 响应结果
service -->> store: 消息流
store -->> batch: 批量数据加工
Note right of oauth2: sso 单点登陆
```
2018/11/3