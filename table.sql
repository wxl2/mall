/*
 Navicat Premium Data Transfer

 Source Server         : MySQl
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 05/07/2021 10:36:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS mall DEFAULT CHARACTER SET utf8;
USE mall;

-- ----------------------------
-- Table structure for applyStore
-- ----------------------------
DROP TABLE IF EXISTS `applyStore`;
CREATE TABLE `applyStore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `isexists` (`username`),
  CONSTRAINT `isexists` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsid` int(15) NOT NULL,
  `goodsname` varchar(30) NOT NULL,
  `price` float(10,2) NOT NULL,
  `typeid` int(5) NOT NULL,
  `imgurl` varchar(100) NOT NULL,
  `goodsuser` varchar(25) NOT NULL,
  PRIMARY KEY (`goodsid`),
  UNIQUE KEY `goodsid` (`goodsid`),
  KEY `goodsuser` (`goodsuser`),
  KEY `goodstype` (`typeid`),
  CONSTRAINT `goodstype` FOREIGN KEY (`typeid`) REFERENCES `goodstype` (`typeid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goodsuser` FOREIGN KEY (`goodsuser`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeid` int(11) NOT NULL,
  `typename` varchar(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `typeofid` (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for order_
-- ----------------------------
DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_` (
  `orderid` varchar(36) NOT NULL,
  `goodsid` int(5) NOT NULL,
  `username` varchar(25) NOT NULL,
  `orderstatus` int(5) NOT NULL,
  `ordertime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`),
  KEY `userbygoods` (`username`),
  KEY `goodsin` (`goodsid`),
  CONSTRAINT `goodsin` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`goodsid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userbygoods` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` char(1) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
