# 框架搭建环境
````
- 编译器：IDEA 2019.1
- Maven：3.6.0
- JDK：1.8.0_191
- 系统：Win 10
````
# 数据库
## MySQL
```
- 版本：mysql-installer-community-8.0.15.0.msi
- 账户名:root
- 密码：jianghongyu
- 端口：3306
- 数据库名：jhy_portrait
- 数据库表结构：见flink-user-portrait-main项目根目录/dbfile/mysql/
```

## MongoDB
```
- 版本：mongodb-linux-x86_64-4.0.9
- 可视化工具Robomongo：robo3t-1.3.1 Windows版
```

# 大数据组件
```
- Hadoop：hadoop-2.8.5.tar.gz
- HBase：hbase-1.4.9.tar.gz
- ZK：zookeeper-3.4.14.tar.gz
- Flink：
- Kafka：kafka_2.11-2.2.0.tgz
- Flume：apache-flume-1.9.0-bin.tar.gz
```

# 前端组件
```
- Vue.js
- Node.js
- 
```

# 其他软件
````
- Git客户端：Git-2.21.0 for Windows
- Navicat：Navicat 12 for MySQL Windows版
- Postman
- 
````


# 开发框架
- Spring Boot 2.0（2.0.4、2.0.3、2.0.2均涉及）
```
1. 新增特性
2. 代码重构
3. 配置变更
```
```
http://spring.io
https://github.com/spring-projects/spring-boot/wiki可查看发布日志
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Configuration-Changelog查看配置的改变
```

# flink-userportrait-main组件结构及[端口]
- portrait-analy-service    [无]
```
[Flink画像分析模块]

```
- portrait-common           [无]
```
[提取的公共项目]
含用户行为及日志结构定义、配置文件读取等等。

```
- portrait-register-center  [8761]
```
[注册中心模块]

```
- portrait-info-in-service  [8762]
```
[数据收集服务]

```
- portrait-search-info      [8763]
```
[接口查询服务]

```
- portrait-view-service     [8764]
```
[前端查询服务]
前端查询服务调用接口查询服务获取数据。

```
- dbfile 
```
数据库相关文件、脚本等。
```
前端查询服务模块调用接口查询服务模块，获取数据后与vuejs项目对接，vuejs通过http方式请求前端查询接口。


# flink-userportrait-main启动顺序
项目源码使用模块编程，client端注册到了eureka服务端，所以启动项目应该也要启动eureka的服务端。
1. 安装portrait-common
2. 启动portrait-register-center
3. 启动
4. 启动
5. 启动

# 测试
1. 注册中心测试http://localhost:8761
2. 数据收集测试

    **1）**

    **2）**

3. 


