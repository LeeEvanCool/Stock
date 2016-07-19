create table stock;
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : stock

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2016-07-18 18:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xx_stock_code_table
-- ----------------------------
DROP TABLE IF EXISTS `xx_stock_code_table`;
CREATE TABLE `xx_stock_code_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(20) DEFAULT NULL,
  `stock_name` varchar(30) DEFAULT NULL,
  `stock_table` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_stock_code_table
-- ----------------------------
INSERT INTO `xx_stock_code_table` VALUES ('11', 'sz002312', '三泰控股', 'xx_stock_data_santai_holding');
INSERT INTO `xx_stock_code_table` VALUES ('12', 'sh600130', '波导股份', 'xx_stock_data_bodao_gufen');

-- ----------------------------
-- Table structure for xx_stock_data
-- ----------------------------
DROP TABLE IF EXISTS `xx_stock_data`;
CREATE TABLE `xx_stock_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `stock_value_date` varchar(10) NOT NULL COMMENT '数据日期',
  `stock_value_time` varchar(8) NOT NULL COMMENT '数据时间',
  `stock_code` varchar(20) DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(50) DEFAULT NULL COMMENT '股票名称',
  `stock_value` decimal(21,6) NOT NULL COMMENT '当前价格',
  `today_open_value` decimal(21,6) NOT NULL COMMENT '今日开盘价',
  `yesterday_close_value` decimal(21,6) NOT NULL COMMENT '昨日收盘价',
  `today_high_value` decimal(21,6) NOT NULL COMMENT '目前最高价',
  `today_low_value` decimal(21,6) NOT NULL COMMENT '目前最低价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_stock_data
-- ----------------------------
INSERT INTO `xx_stock_data` VALUES ('7', '2016-07-18 00:00:00', '2016-07-18', '15:00:00', '大同煤业', '大同煤业', '6.110000', '6.210000', '6.270000', '6.210000', '6.030000');
INSERT INTO `xx_stock_data` VALUES ('8', '2016-07-18 00:00:00', '2016-07-18', '15:00:00', '大同煤业', '大同煤业', '6.110000', '6.210000', '6.270000', '6.210000', '6.030000');
INSERT INTO `xx_stock_data` VALUES ('9', '2016-07-18 00:00:00', '2016-07-18', '15:00:00', 'sh601001', '大同煤业', '6.110000', '6.210000', '6.270000', '6.210000', '6.030000');
INSERT INTO `xx_stock_data` VALUES ('10', '2016-07-18 00:00:00', '2016-07-18', '15:09:03', 'sz002312', '三泰控股', '12.540000', '12.100000', '12.190000', '12.680000', '12.100000');

-- ----------------------------
-- Table structure for xx_stock_data_santai_holding
-- ----------------------------
DROP TABLE IF EXISTS `xx_stock_data_santai_holding`;
CREATE TABLE `xx_stock_data_santai_holding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `stock_value_date` varchar(10) NOT NULL COMMENT '数据日期',
  `stock_value_time` varchar(8) NOT NULL COMMENT '数据时间',
  `stock_code` varchar(20) DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(50) DEFAULT NULL COMMENT '股票名称',
  `stock_value` decimal(21,6) NOT NULL COMMENT '当前价格',
  `today_open_value` decimal(21,6) NOT NULL COMMENT '今日开盘价',
  `yesterday_close_value` decimal(21,6) NOT NULL COMMENT '昨日收盘价',
  `today_high_value` decimal(21,6) NOT NULL COMMENT '目前最高价',
  `today_low_value` decimal(21,6) NOT NULL COMMENT '目前最低价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_stock_data_santai_holding
-- ----------------------------
