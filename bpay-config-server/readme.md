# 健康监控
http://localhost:9021/actuator
http://localhost:9021/bpay-client/dev

# bus 动态刷新配置
post http://localhost:9021/actuator/bus-refresh

# github webhooks
> 在github上配置webhooks自动触发，(本地测试可以使用ngrok代理)
>> 1. 安装ngrok
>> 2. ./ngrok http 9021
>> 3. Payload URL: https://81218e85.ngrok.io/actuator/bus-refresh
