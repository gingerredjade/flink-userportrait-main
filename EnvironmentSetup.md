# 基础环境搭建（后期将文档里的详细步骤备份至此）
## MySQL（Windows版）
- 启动服务器
```
net start mysql
```
- 关闭服务器
```
net stop mysql
```

## 关闭防火墙、禁用SELinux、机器免密
1、 关闭防火墙
```
systemctl stop firewalld.service 
systemctl disable firewalld.service
firewall-cmd --state
```
2、 禁用SELinux
```
vim /etc/sysconfig/selinux
设置SELINUX=disabled
```
3、 机器免密
```
ssh-keygen -t rsa
ssh-copy-id portraitmaster（portraitmaster为主机名）
cd .ssh/
more authorized_keys可以看到一串公钥
```

## 安装JDK
```
tar -zxvf jdk-8u191-linux-x64.tar.gz
mv jdk1.8.0_191/ /usr/local/jdk1.8.0_191
```

## Hadoop
### 一、配置
1、 hadoop-env.sh
```
export JAVA_HOME=/usr/local/jdk1.8.0_191
```
2、 core-site.xml
```
<configuration>
        <property>
                <name>fs.default.name</name>
                <value>hdfs://portraitmaster:9000</value>
        </property>
        <property>
                <name>hadoop.tmp.dir</name>
                <value>/home/portrain/hadoop-2.8.5/tmp</value>
        </property>
</configuration>
```
3、 hdfs-site.xml
```
<configuration>
        <property>
                <name>dfs.replication</name>
                <value>1</value>
        </property>
        <property>
                <name>dfs.permissions</name>
                <value>true</value>
        </property>
</configuration>
注意：这里权限是true，否则windows开发会报错。
```
4、 mapred-site.xml
```
cp mapred-site.xml.template mapred-site.xml
vim mapred-site.xml
<configuration>
        <property>
                 <name>mapreduce.framework.name</name>
                 <value>yarn</value>
        </property>
        <property>
                <name>mapreduce.jobhistory.address</name>
                <value>portraitmaster:10020</value>
        </property>
</configuration>
```
5、 yarn-site.xml
```
vim yarn-site.xml
<configuration>

<!-- Site specific YARN configuration properties -->
        <property>
            <name>yarn.resourcemanager.hostname</name>
            <value>portraitmaster</value>
          </property>
        <property>
            <name>yarn.nodemanager.aux-services</name>
            <value>mapreduce_shuffle</value>
        </property>
        <property>
            <name>mapreduce.job.ubertask.enable</name>
            <value>true</value>
        </property>
</configuration>
```
6、配置环境变量
```
vim /etc/profile
export JAVA_HOME=/usr/local/jdk1.8.0_191
export HADOOP_HOME=/home/portrain/hadoop-2.8.5
export PATH=.:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH
source /etc/profile
```

### 二、启动停止
1、启动
- 启动HDFS
```
cd /home/portrain/hadoop-2.8.5/bin
hadoop namenode -format（不能随便用）
hadoop namenode -format
start-dfs.sh
```
- 启动YARN
```
start-yarn.sh
```

2、停止
```
略
```

## ZooKeeper
### 一、配置
1、配置ZK
```
/home/portrain/zookeeper-3.4.14/conf
cp zoo_sample.cfg zoo.cfg
vim zoo.cfg
	将dataDir=/home/portrain/zookeeper-3.4.14/data
```
2、配置环境变量
```
export ZK_HOME=/home/portrain/zookeeper-3.4.14
PATH=.:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$ZK_HOME/bin:$PATH
```

### 二、启动停止
```
cd /home/portrain/zookeeper-3.4.14/bin
zkServer.sh start
zkServer.sh status
```

## HBase
### 一、配置
1、hbase-env.sh
```
指定Java的安装路径
export JAVA_HOME= /usr/local/jdk1.8.0_191
由于HBase自带了ZK的jar包，如果用户需要使用自定义的zk，需要改为false（一般都用自己的zookeeper集群）
export HBASE_MANAGES_ZK=false
```
2、hbase-site.xml
```
——》指定临时目录的存放位置，和hadoop.tmp.dir相似
  <property >
    <name>hbase.tmp.dir</name>
    <value>/home/hadoop/app/hbase/data/tmp</value>
  </property>

——》指定hbase数据文件存储在hdfs上的路径，就是RegionServer的共享目录。默认放在HDFS的根路径即放到hbase底下。也可以放其他路径
  <property >
    <name>hbase.rootdir</name>
    <value>hdfs:// portraitmaster:9000/hbase</value>
  </property>

——》指定是否是分布式的，false就是单机，true就是分布式集群
  <property >
    <name>hbase.cluster.distributed</name>
    <value>true</value>
  </property>

——》指定zookeeper的地址实例
<property>
  <name>hbase.zookeeper.quorum</name>
  <value>portraitmaster</value>
</property>

——》指定HBase的副本数
<property>
  <name>dfs.replication</name>
  <value>1</value>
</property>
```
3、regionservers
```
portraitmaster
```
4、配置环境变量
```
export HBASE_HOME=/home/portrain/hbase-1.4.9
PATH=.:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$ZK_HOME/bin:$HBASE_HOME/bin:$PATH
```

### 二、启动停止
（注意不要在standby的namenode上启动）
```
cd /home/portrain/hbase-1.4.9/
bin/hbase-daemon.sh start master
bin/hbase-daemon.sh start regionserver
或者直接
bin/start-hbase.sh
```

## Mongo
### 一、环境搭建
```
tar -zxvf mongodb-linux-x86_64-4.0.9.tgz
cd mongodb-linux-x86_64-4.0.9/
mkdir data

配置环境变量
export MONGO_HOME=/home/portrain/mongodb-linux-x86_64-4.0.9
PATH=.:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$ZK_HOME/bin:$HBASE_HOME/bin:$MONGO_HOME/bin:$PATH

. /etc/profile

```
### 二、使用说明
- 要管理数据库，必须先开机，开机使用mongod --dbpath 指定数据库文档所在的文件夹 --bind_ip IP地址。
- 管理数据库：mongo(一定要在新的cmd中输入)
- 清屏：cls。
- 查看所有数据库列表：show dbs。
- 使用数据库、创建数据库：use 数据库名字。
    - 如果真的想把这个数据库创建成功，那么必须插入一个数据。数据库中不能直接插入数据，只能往集合(collections)中插入数据。不需要创建集合，只需要写点语法：
    ```
    db.student.insert({“name”:”xiaoming”});
    db.student  系统发现student是一个陌生的集合名字，所以就自动创建了集合。
    ```
    - 删除数据库，删除当前所在的数据库
    ```
    db.dropDatabase();
    ```
    - 插入数据:insert().插入数据，随着数据的插入，数据库创建成功了，集合也创建成功了。
    ```
    db.student.insert({"name":"xiaoming"});
    ```
    - 我们不可能一条一条的insert。所以，我们希望用sublime在外部写好数据库的形式，然后导入数据库
    ```
    mongoimport --db test --collection restaurants --drop --file primer-dataset.json
    
    -db test  想往哪个数据库里面导入
    --collection restaurants  想往哪个集合中导入
    --drop 把集合清空
    --file primer-dataset.json  哪个文件

    ```
    - 查找数据find()
    ```
    - 查找数据用find。find中没有参数，那么将列出这个集合的所有文档：
    db.restaurants.find()
    
    - 精确匹配
    db.student.find({"score.shuxue":70});
    
    - 多个条件
    db.student.find({"score.shuxue":70 , "age":12})
    
    - 大于条件
    db.student.find({"score.yuwen":{$gt:50}});
    
    - 或者，寻找所有年龄是9岁，或者11岁的学生
    db.student.find({$or:[{"age":9},{"age":11}]});
    
    - 查找完毕之后，打点调用sort，表示升降排序。
    db.restaurants.find().sort( { "borough": 1, "address.zipcode": 1 } )
    ```
    - 修改数据update()
        - 修改里面还有查询条件。你要该谁，要告诉mongo。
    ```
    - 查找名字叫做小明的，把年龄更改为16岁
    db.student.update({"name":"小明"},{$set:{"age":16}});
    
    - 查找数学成绩是70，把年龄更改为33岁
    db.student.update({"score.shuxue":70},{$set:{"age":33}});
    
    - 更改所有匹配项目："
    By default, the update() method updates a single document. To update multiple documents, use the multi option in the update() method.
    db.student.update({"sex":"男"},{$set:{"age":33}},{multi: true});
    
    - 完整替换，不出现$set关键字了：
    db.student.update({"name":"小明"},{"name":"大明","age":16});
    ```
    - 删除数据remove()
    ```
    db.restaurants.remove( { "borough": "Manhattan" } )
    
    - By default, the remove() method removes all documents that match the remove condition. Use the justOne option to limit the remove operation to only one of the matching documents.
    db.restaurants.remove( { "borough": "Queens" }, { justOne: true } )
    ```

## Kafka
### 一、配置
```
/home/portrain/kafka_2.11-2.2.0/config
vim server.properties
	作如下修改
log.dirs=/home/portrain/kafka_2.11-2.2.0/kafka-logs
	zookeeper.connect=localhost:2181要确保和自己的ZK匹配
```

### 二、启动停止
```
cd /home/portrain/kafka_2.11-2.2.0
bin/kafka-server-start.sh config/server.properties
```

### 三、创建topics
```
./kafka-topics.sh可以查看kafka topic有哪些参数。然后开始创建topic。
./kafka-topics.sh --create --topic attentionProductLog --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1
./kafka-topics.sh --create --topic buyCartProductLog --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1
./kafka-topics.sh --create --topic collectProductLog --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1
./kafka-topics.sh --create --topic scanProductLog --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1

上述命令分别是创建attentionProductLog/ buyCartProductLog/collectProductLog/scanProductLog topic。
其中的参数说明：
--topic指定topic名称；
--zookeeper执行ZK地址；
--replication-factor指定副本；
--partitions指定分区；
```

### 四、消费者监听topic
```
cd /home/portrain/kafka_2.11-2.2.0
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic attentionProductLog --from-beginning
```
然后通过Postman发送消息到kafka，后台kafka consumer监听就能打印消息。控制台监听结果如下:
```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic attentionProductLog --from-beginning
{"operatortype":0,"productid":2,"producttypeid":0,"userid":0,"usetype":0}##1##1557479919454
消费者监听控制台响应打印的结果。
```

## Flume
### 一、配置Flume
flume-conf.properties
```
scanProductLog.sources = s1
scanProductLog.channels = c1
scanProductLog.sinks = s1

scanProductLog.sources.s1.type = org.apache.flume.source.kafka.KafkaSource
scanProductLog.sources.s1.zookeeperConnect = portraitmaster:2181
scanProductLog.sources.s1.topic = scanProductLog
scanProductLog.sources.s1.groupId = ty1
scanProductLog.sources.s1.channels = c1
scanProductLog.sources.s1.interceptors = i1
scanProductLog.sources.s1.interceptors.i1.type = timestamp
scanProductLog.sources.s1.kafka.consumer.timeout.ms = 1000

scanProductLog.channels.c1.type = memory
scanProductLog.channels.c1.capacity = 1000
scanProductLog.channels.c1.transactionCapacity = 1000

scanProductLog.sinks.s1.type = hdfs
scanProductLog.sinks.s1.hdfs.path = /data/kafka/scanProductLog/%y-%m-%d
scanProductLog.sinks.s1.hdfs.fileType = DataStream
scanProductLog.sinks.s1.hdfs.rollSize = 0
scanProductLog.sinks.s1.hdfs.rollCount = 0
scanProductLog.sinks.s1.hdfs.rollInterval = 30
scanProductLog.sinks.s1.channel = c1

```

### 二、启动停止
```
./bin/flume-ng agent -n scanProductLog -f conf/flume-config.properties
```












