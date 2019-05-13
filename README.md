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
- 数据库名：jhy_portrait
- 数据库表结构：见项目根目录/dbfile/mysql/
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
- Kafka：
- Flume：
```

# 前端组件
```
- Vue.js
- Node.js
```

# 其他软件
````
- Git客户端：Git-2.21.0 for Windows
- Navicat：Navicat 12 for MySQL Windows版
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

# maven使用办法
1. 拷贝maven仓库至本地环境（默认仓库位置/自定义位置）
2. 准备maven环境
 - 2.1 本机安装maven环境
    - 2.2.1 配置maven中仓库的位置（默认/自定义配置）
 
 - 2.2 本机可以不用安装maven环境(Linux中方法同)
    - 2.2.1 将maven压缩包放到某个目录下"E:/apache-maven-3.6.0-bin.zip"
    - 2.2.2 编辑项目中的.mvn/wrapper/maven-wrapper.properties
    - 2.2.3 distributionUrl设为第二步设置的目录"E:/apache-maven-3.6.0-bin.zip"

# flink-userportrait-main组件结构
- portrait-analy-service
- portrait-common
- portrait-info-in-service
- portrait-register-center注册中心模块
- portrait-search-info
- portrait-view-service
    
# flink-userportrait-main其他目录结构
- dbfile 数据库相关文件、脚本等。

# flink-userportrait-main启动顺序
项目源码使用模块编程，client端注册到了eureka服务端，所以启动项目应该也要启动eureka的服务端。
1. gis-eureka-server
2. gis-config-server、gis-config-server-ui
3. gis-config-client

# 端口
- portrait-analy-service                        []
- portrait-common                               []
- portrait-info-in-service                      []
- portrait-register-center注册中心模块          []
- portrait-search-info                          []
- portrait-view-service                         []

# 测试
1. http://localhost:8761
2. 查看服务都安配置信息
```
http://localhost:7070/mswss-146/dev
```
3. http://localhost:8080/testhello/redis_server_port
4. 修改配置-->/bus-refresh
5. http://localhost:8080/testhello/redis_server_port已获取最新配置信息


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

## 注册中心
```
分布式系统中，服务注册中心是最重要的基础部分
1.  客户端发现（由A发起）
    A直接找注册中心，注册中心把它上面所有B服务的信息告诉A
2.  服务端发现（代理）
    如下技术采用的是该方式：
    - Nginx
    - Dubbo
```

## 使用Swagger自动生成REST API文档
### 5.1. 接口文档
遇到如下场景需要提供接口文档
- 第三方合作
### 5.2. 简介
```
https://swagger.io/
```
Swagger Editor：用于编辑REST API文档.支持编辑Swagger API规范yaml文档描述API，并可实时预览API
```
服务端启动后，能通过Swagger UI在浏览器中查看已发布的REST API文档。
```

# Web开发——Spring Boot对静态资源的处理
使用SpringBoot；

**1）、创建SpringBoot应用，选中我们需要的模块；**

**2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来**


**自动配置原理？**

这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？xxx

```
xxxxAutoConfiguration：帮我们给容器中自动配置组件；
__
```

## 2、SpringBoot对静态资源的映射规则；
==1）、所有 /webjars/** ，都去 classpath:/META-INF/resources/webjars/ 找资源；==

​	webjars：以jar包的方式引入静态资源；

http://www.webjars.org/

localhost:8080/webjars/jquery/3.3.1/jquery.js
