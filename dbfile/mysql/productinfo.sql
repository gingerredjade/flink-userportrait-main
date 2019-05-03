/*
 Navicat MySQL Data Transfer

 Source Server         : JHy本机
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : jhy_portrait

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/05/2019 23:53:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for productinfo
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品编号ID',
  `producttypeid` int(20) NULL DEFAULT NULL COMMENT '商品类别编号ID',
  `productname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品名称',
  `productdescription` varchar(1500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品描述',
  `price` int(20) NULL DEFAULT NULL COMMENT '价格',
  `num` int(20) NULL DEFAULT NULL COMMENT '库存',
  `createtime` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `mechartid` int(20) NULL DEFAULT NULL COMMENT '商品所属的商家编号ID',
  `producturl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片地址，但是暂时不用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '商品表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
