/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : weiwangzhe

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-31 01:18:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) DEFAULT NULL,
  `zone_area_id` int(11) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `game_name` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `ladder_score` float DEFAULT NULL,
  `rank_desc` int(11) DEFAULT NULL,
  `rank_star` int(11) DEFAULT NULL,
  `winning_percentage` float DEFAULT NULL,
  `win_desc` float DEFAULT NULL,
  `game_svr_entity` varchar(20) DEFAULT NULL,
  `game_seq` varchar(20) DEFAULT NULL,
  `relay_svr_entity` varchar(20) DEFAULT NULL,
  `is_grab` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;
