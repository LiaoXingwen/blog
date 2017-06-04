/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : test
Target Host     : localhost:3306
Target Database : test
Date: 2017-06-04 18:45:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `hasComment` tinyint(1) NOT NULL,
  `html` longtext NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `md` longtext NOT NULL,
  `saveTime` datetime NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `thumbsdown` int(11) NOT NULL DEFAULT '0',
  `thumbsup` int(11) NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `viewCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`aid`),
  KEY `FK8FEAC02B2393456E` (`uid`),
  CONSTRAINT `FK8FEAC02B2393456E` FOREIGN KEY (`uid`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_article
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `hasComment` tinyint(1) NOT NULL,
  `oid` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_failemail
-- ----------------------------
DROP TABLE IF EXISTS `t_failemail`;
CREATE TABLE `t_failemail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_failemail
-- ----------------------------

-- ----------------------------
-- Table structure for t_filemapping
-- ----------------------------
DROP TABLE IF EXISTS `t_filemapping`;
CREATE TABLE `t_filemapping` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `size` float NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `uploadTime` datetime NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_filemapping
-- ----------------------------

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow` (
  `toid` int(11) NOT NULL,
  `fromid` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`toid`,`fromid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_follow
-- ----------------------------

-- ----------------------------
-- Table structure for t_thumbs
-- ----------------------------
DROP TABLE IF EXISTS `t_thumbs`;
CREATE TABLE `t_thumbs` (
  `uid` int(11) NOT NULL,
  `oid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`,`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_thumbs
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brithDay` datetime DEFAULT NULL,
  `code` varchar(25) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `headURl` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `registerTime` datetime NOT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `state` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_action
-- ----------------------------
DROP TABLE IF EXISTS `t_user_action`;
CREATE TABLE `t_user_action` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `action` int(11) NOT NULL,
  `data` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `fromIP` varchar(255) DEFAULT NULL,
  `objId` int(11) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user_action
-- ----------------------------

-- ----------------------------
-- Table structure for t_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo` (
  `uid` int(11) NOT NULL,
  `articles` int(11) DEFAULT NULL,
  `comments` int(11) DEFAULT NULL,
  `fileSize` int(11) DEFAULT NULL,
  `followers` int(11) DEFAULT NULL,
  `following` int(11) DEFAULT NULL,
  `thumbsDown` int(11) DEFAULT NULL,
  `thumbsUp` int(11) DEFAULT NULL,
  `viewcount` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_userinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_userstyle
-- ----------------------------
DROP TABLE IF EXISTS `t_userstyle`;
CREATE TABLE `t_userstyle` (
  `uid` int(11) NOT NULL,
  `bgcolor` varchar(7) DEFAULT NULL,
  `fontcolor` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_userstyle
-- ----------------------------

-- ----------------------------
-- View structure for v_commet_user
-- ----------------------------
DROP VIEW IF EXISTS `v_commet_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_commet_user` AS select `t_comment`.`cid` AS `cid`,`t_comment`.`content` AS `content`,`t_comment`.`date` AS `date`,`t_comment`.`hasComment` AS `hasComment`,`t_comment`.`oid` AS `oid`,`t_comment`.`type` AS `type`,`t_comment`.`uid` AS `uid`,`t_user`.`username` AS `username`,`t_user`.`headURl` AS `headURL` from (`t_comment` join `t_user`) where (`t_comment`.`uid` = `t_user`.`id`);

