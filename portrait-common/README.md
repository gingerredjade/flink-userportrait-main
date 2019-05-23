# 本模块是抽出的公共项目
## 一、公共项目组织介绍
### 用户行为日志结构讲解以及实体定义
```
com.jhy.log/*
```
1. 浏览商品行为：商品id 商品类别id 浏览时间、停留时间、用户id 终端类别,用户ip
2. 收藏商品行为：商品id 商品类别id 操作时间、操作类型（收藏，取消）、用户id、终端类别、用户ip
3. 购物车行为：商品id 商品类别id 、操作时间、操作类型（加入，删除）、用户id、终端类别、用户ip
4. 关注商品：商品id 商品类别id 操作时间、操作类型（关注，取消）、用户id、终端类别、用户ip


### 结果定义
```
com.jhy.entity/*
```
- AnalyResult:接口查询服务-Mongo存储逇数据  分析结果通用实体类
- ViewResultAnaly:前端查询服务响应结果对象类

### 工具类
```
com.jhy.utils/*
```
- MapUtils：Map工具类
- ReadProperties：properties配置文件读取工具类








