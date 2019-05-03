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

 Date: 03/05/2019 23:55:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号ID',
  `productid` int(20) NULL DEFAULT NULL COMMENT '商品编号ID',
  `producttypeid` int(20) NULL DEFAULT NULL COMMENT '商品类别编号ID',
  `createtime` timestamp(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `amount` double(20, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `paytype` int(2) NULL DEFAULT NULL COMMENT '订单支付类型',
  `paytime` timestamp(0) NULL DEFAULT NULL COMMENT '订单支付时间',
  `paystatus` int(2) NULL DEFAULT NULL COMMENT '订单支付状态-0、未支付 1、已支付 2、已退款',
  `couponamount` double(20, 2) NULL DEFAULT NULL COMMENT '使用优惠券金额',
  `totalamount` double(20, 2) NULL DEFAULT NULL COMMENT '订单支付总金额',
  `refundamount` double(20, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `num` int(20) NULL DEFAULT NULL COMMENT '商品数量',
  `userid` int(20) NOT NULL COMMENT '用户id，最后要给用户打上标签用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
