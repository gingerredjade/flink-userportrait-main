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
- Spring Boot 2.0（2.0.4）
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


# 配置中心服务组件启动顺序
项目源码使用模块编程，client端注册到了eureka服务端，所以启动项目应该也要启动eureka的服务端。
然后再启动config服务端，最后启动config-client端，启动config-client要使用profile方法启动。
1. gis-eureka-server
2. gis-config-server、gis-config-server-ui
3. gis-config-client

# 端口
eureka-server:8761
config-server:7070
config-server-ui:7071
config-client:8080

测试：
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

# run - 启动、停止
## 启动
将jar包上传到指定服务器上，采用 java -jar *.jar 的方式  
linux下可以通过nohup或者supervisor(推荐）进行启动
启动命令中建议加入spring.profiles.active参数，指定使用生产环境的配置，该配置可以application-prod.properties中指定
使用prod模式时，日志文件会自动输出到当前目录的logs文件中，可通过```tail -100f logs/gis-frame.log```进行查看
- nohup java -jar target/xxxx.jar > /dev/null 2>&1 &
- nohup java -jar -Dspring.profiles.active=prod target/xxxx.jar > console.file 2>&1 &
## 停止
- ps -ef|grep eureka
- kill进程以停止服务



# 知识点记录
## Config Client的Controller编写规则
每个Controller类和其内部的方法都要编写详细的API说明，且要做到配置和逻辑分离。interface+implements，注解和注释写在interface中。
可参考gis-config-client.

## Spring Cloud Config简介
- Config Server可横向扩展、集中式的配置服务器，集中管理应用程序各个环境下的配置；
- Config Client是Config Server的客户端，用于操作存储在Config Server中的配置属性；

Spring Cloud Config分服务端和客户端，服务端负责将git/svn/本地中存储的配置文件发布成REST接口，客户端可以从服务端REST接口获取配置。
但客户端并不能主动感知到配置的变化，从而主动去获取新的配置。
Spring Cloud已经给我们提供了客户端主动获取新的配置信息的解决方案，每个客户端通过POST方法触发各自的/refresh。

本地仓库是指将所有的配置文件统一写在Config Server工程目录下。
config server暴露Http API接口，config client通过调用config server的Http API接口来读取配置文件


## 注册中心
```
分布式系统中，服务注册中心是最重要的基础部分
示例：注册中心、A、微服务B：
微服务B启动的时候向注册中心进行注册；
A是如何拿到B的信息呢？两种方法：
1.  客户端发现（由A发起）
    A直接找注册中心，注册中心把它上面所有B服务的信息告诉A
    A从注册中心提供的众多可用的B中通过负载均衡机制（轮询、Hash）挑出一个
    再通过IP地址找到B
    Eureka采用的是该方式    
2.  服务端发现（代理）
    代理帮A从众多B中挑选一个出来，然后A再去找B。
    如下技术采用的是该方式：
    - Nginx
    - Dubbo
    - Kubernetes（集群中的每个节点都运行一个代理来实现服务发现的功能）
```

## 微服务的特点：异构
- 不同语言
- 不同类型的数据库

## 目前流行的通信方式
- REST（Spring Cloud的服务调用方式）
- RPC
- Node.js的eureka-js-client
Eureka、支持将非Java语言实现的服务纳入到自己的服务治理体系中，只需要其他语言实现eureka的客户端程序。

## 使用Swagger自动生成REST API文档
### 5.1. 接口文档
遇到如下场景需要提供接口文档
- 前后端分离
- 第三方合作
### 5.2. 简介
```
https://swagger.io/
Swagger是一个规范和完整的框架，用于生成、描述、调用和可视化RESTful风格的Web服务。http://swagger.io/
springfox的前身是swagger-springmvc，是一个开源的API doc框架，可以将我们的Controller的方法以文档的形式展现，基于Swagger。
http://springfox.github.io/springfox/
```
Swagger Editor：用于编辑REST API文档.支持编辑Swagger API规范yaml文档描述API，并可实时预览API
Swagger Codegen：用于生成REST API文档.一个模板驱动引擎，通过分析用户Swagger资源声明以各种语言生成客户端SDK或Server端桩代码，从而让开发团队能够更好的关注API的实现和调用，提高开发效率
Swagger UI：用于查看REST API文档。API在线文档生成和测试的工具，可显示API描述，并且支持调用API进行测试及验证
```
服务端启动后，能通过Swagger UI在浏览器中查看已发布的REST API文档。

注：Spring Cloud Config Server不能集成Swagger，否则swagger ui的资源加载不了，只能集成在客户端。
```

# Web开发——Spring Boot对静态资源的处理
使用SpringBoot；

**1）、创建SpringBoot应用，选中我们需要的模块；**

**2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来**

**3）、自己编写业务代码；**



**自动配置原理？**

这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？xxx

```
xxxxAutoConfiguration：帮我们给容器中自动配置组件；
xxxxProperties:配置类来封装配置文件的内容；
__
```

## 2、SpringBoot对静态资源的映射规则；
==1）、所有 /webjars/** ，都去 classpath:/META-INF/resources/webjars/ 找资源；==

​	webjars：以jar包的方式引入静态资源；

http://www.webjars.org/

localhost:8080/webjars/jquery/3.3.1/jquery.js

```
<!--引入jquery-webjar-->在访问的时候只需要写webjars下面资源的名称即可
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.3.1</version>
		</dependency>
```



==2）、"/**" 访问当前项目的任何资源，都去（静态资源的文件夹）找映射==

```
"classpath:/META-INF/resources/", 
"classpath:/resources/",
"classpath:/static/", 
"classpath:/public/" 
"/"：当前项目的根路径
```

localhost:8080/abc ===  去静态资源文件夹里面找abc

==3）、欢迎页； 静态资源文件夹下的所有index.html页面；被"/**"映射；==

​	localhost:8080/   找index页面

==4）、所有的 **/favicon.ico  都是在静态资源文件下找；==

## 3、自定义静态资源路径
配置静态文件夹路径，如配成类路径下的hello和gis文件夹**
spring.resources.static-locations=classpath:/hello/,classpath:/gis