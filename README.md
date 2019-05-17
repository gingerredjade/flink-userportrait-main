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

# flink-userportrait-main组件结构
- portrait-analy-service    
```
[Flink画像分析模块]

```
- portrait-common           
```
[提取的公共项目]
含用户行为及日志结构定义、配置文件读取等等。

```
- portrait-register-center
```
[注册中心模块]

```
- portrait-info-in-service
```
[数据收集服务]

```
- portrait-search-info 
```
[]

```
- portrait-view-service
```
[]

```
- dbfile 
```
数据库相关文件、脚本等。
```

# flink-userportrait-main启动顺序
项目源码使用模块编程，client端注册到了eureka服务端，所以启动项目应该也要启动eureka的服务端。
1. portrait-register-center
2. 
3. 

# 端口
- portrait-analy-service                        []
- portrait-common                               []
- portrait-register-center注册中心模块          [8761]
- portrait-info-in-service                      [8762]
- portrait-search-info                          [8763]
- portrait-view-service                         [8764]

# 测试
1. 注册中心测试http://localhost:8761
2. 数据收集测试

    **1）**

    **2）**

3. 


# build - 打包
## 1. IDEA界面
## 2. 命令行
```
2.1 进入项目
2.2 mvn clean package或mvn clean install
```

# 知识点记录
## Flink流处理程序包括5个部分
1. 获取运行时(Flink执行环境的上下文)  [set up the execution environment]
2. 添加Source  [get input data]
3. 定义算子
4. 定义Sink
5. 启动程序


