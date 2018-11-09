# 健康监控
http://localhost:9021/actuator
http://localhost:9021/bpay-client/dev

# bus 动态刷新配置
post http://localhost:9021/actuator/bus-refresh

# github webhooks 修改事件自动刷新配置
> 在github bpay-config-repo上配置webhooks，(本地测试可以使用ngrok代理)
>> 1. 安装ngrok

>> 2. 监听9021端口: ./ngrok http 9021

>> 3. Payload URL: https://81218e85.ngrok.io/actuator/bus-refresh
