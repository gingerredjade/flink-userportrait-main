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

 Date: 03/05/2019 23:55:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for producttypeinfo
-- ----------------------------
DROP TABLE IF EXISTS `producttypeinfo`;
CREATE TABLE `producttypeinfo`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品类别编号ID',
  `producttypename` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品类别名称',
  `producttypedecription` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品类别描述',
  `producttypelevel` int(2) NULL DEFAULT NULL COMMENT '商品类别等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '商品类别表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
