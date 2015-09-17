/*
MySQL Data Transfer
Source Host: localhost
Source Database: travel
Target Host: localhost
Target Database: travel
Date: 2015/1/27 2:13:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(255) NOT NULL,
  `describe` varchar(255) NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  `width` float NOT NULL,
  `height` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for district_user
-- ----------------------------
DROP TABLE IF EXISTS `district_user`;
CREATE TABLE `district_user` (
  `id` int(11) NOT NULL auto_increment,
  `district_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  `msg` char(255) NOT NULL,
  `audio_url` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for map
-- ----------------------------
DROP TABLE IF EXISTS `map`;
CREATE TABLE `map` (
  `id` int(11) NOT NULL auto_increment,
  `district_id` int(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for poi
-- ----------------------------
DROP TABLE IF EXISTS `poi`;
CREATE TABLE `poi` (
  `id` int(11) NOT NULL auto_increment,
  `district_id` int(11) NOT NULL,
  `name` char(255) NOT NULL,
  `addr` varchar(255) NOT NULL,
  `tel` char(255) NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(255) NOT NULL,
  `sex` char(255) NOT NULL,
  `age` int(11) NOT NULL,
  `vip` int(11) NOT NULL,
  `icon` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `district` VALUES ('1', '人民广场', '1', '10', '10', '50', '50');
INSERT INTO `district_user` VALUES ('1', '1', '1', '23', '34', '啊', 'http://localhost/travel/audio/1/123.wav');
INSERT INTO `district_user` VALUES ('2', '1', '2', '34', '24', '哦', 'http://localhost/travel/audio/1/456.wav');
INSERT INTO `district_user` VALUES ('3', '1', '3', '43', '25', '吖', 'http://localhost/travel/audio/1/123.wav');
INSERT INTO `district_user` VALUES ('4', '2', '4', '24', '53', '嗯', 'http://localhost/travel/audio/1/123.wav');
INSERT INTO `map` VALUES ('1', '1', 'http://localhost/travel/map/1/123.jpg', '0', '10', '10');
INSERT INTO `map` VALUES ('2', '1', 'http://localhost/travel/map/1/456.jpg', '1', '10', '10');
INSERT INTO `poi` VALUES ('1', '1', '如家酒店', '宛平南路', '18512345678', '20', '26', '1');
INSERT INTO `poi` VALUES ('2', '1', '家乐福', '零陵路', '18512345679', '34', '48', '2');
INSERT INTO `user` VALUES ('1', '李明', '男', '123', '0', 'http://localhost/travel/icon/1/123.jpg');
INSERT INTO `user` VALUES ('2', '王红', '女', '22', '3', 'http://localhost/travel/icon/1/456.jpg');
INSERT INTO `user` VALUES ('3', '张斌', '男', '32', '2', 'http://localhost/travel/icon/1/456.jpg');
INSERT INTO `user` VALUES ('4', '韩梓', '女', '18', '5', 'http://localhost/travel/icon/1/123.jpg');
