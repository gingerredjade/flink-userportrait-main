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

 Date: 24/04/2019 16:18:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userdetailinfo
-- ----------------------------
DROP TABLE IF EXISTS `userdetailinfo`;
CREATE TABLE `userdetailinfo`  (
  `userdetailId` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键，用户详情编号ID',
  `userid` int(20) NULL DEFAULT NULL COMMENT '用户编号ID',
  `edu` int(1) NULL DEFAULT NULL COMMENT '学历',
  `profession` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职业',
  `marriage` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '婚姻状态：1-未婚 2-已婚 3-离异 4-未知',
  `hasChild` int(1) NULL DEFAULT NULL COMMENT '是否有孩子：1-没有 2-有 3-未知',
  `hasCar` int(1) NULL DEFAULT NULL COMMENT '是否有车：1-有 2-没有 3-未知',
  `hasHouse` int(1) NULL DEFAULT NULL COMMENT '是否有房：1-有 2-没有 3-未知',
  `telephonebrand` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机品牌',
  PRIMARY KEY (`userdetailId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息详情补充表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
