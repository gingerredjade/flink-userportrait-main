# 本模块是数据收集服务模块

## 日志收集服务REST测试
通过Postman发送POST请求，以收集关注商品行为日志为例：
```
输入请求地址：http://127.0.0.1:8762/infolog/receivelog
设定parameter：
    - key：recevicelog
    - value：AttentionProductLog:{"productid":2}

数据处理结果：{"message":"{\"operatortype\":0,\"productid\":2,\"producttypeid\":0,\"userid\":0,\"usetype\":0}","status":"success"}

存储至Kafka：send


```

    






