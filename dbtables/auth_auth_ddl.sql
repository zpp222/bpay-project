/*
 Navicat MySQL Data Transfer

 Source Server         : docker
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3308
 Source Schema         : auth

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 11/10/2018 17:20:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_authorities_users` (`username`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of authorities
-- ----------------------------
BEGIN;
INSERT INTO `authorities` VALUES ('zpp', 'ROLE_ADMIN', 0000000001);
INSERT INTO `authorities` VALUES ('zpp', 'ROLE_USER', 0000000002);
INSERT INTO `authorities` VALUES ('zpp', 'ROLE_ENDPOINT_ADMIN', 0000000004);
COMMIT;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`),
  KEY `token_id_index` (`token_id`),
  KEY `authentication_id_index` (`authentication_id`),
  KEY `user_name_index` (`user_name`),
  KEY `client_id_index` (`client_id`),
  KEY `refresh_token_index` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('app', NULL, '{noop}secret', 'openid', 'password,client_credentials,refresh_token', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '{noop}secret', 'openid', 'authorization_code,refresh_token,password', NULL, NULL, 300, 1800, NULL, '1');
INSERT INTO `oauth_client_details` VALUES ('h5', NULL, '', 'openid', 'implicit', '', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `token_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` blob,
  `authentication` blob,
  PRIMARY KEY (`token_id`),
  KEY `token_id_index` (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('zpp', '$2a$10$uuqto3OiU/5KfhMV.V1Qm.D8UuumrAOl/ku/Hh5s.RAkk9eh4Tta.', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
