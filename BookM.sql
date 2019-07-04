/*
 Navicat Premium Data Transfer

 Source Server         : cdb-4nwq78pt.gz.tencentcdb.com_10050
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-4nwq78pt.gz.tencentcdb.com:10050
 Source Schema         : BookM

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 04/07/2019 14:43:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Announce
-- ----------------------------
DROP TABLE IF EXISTS `Announce`;
CREATE TABLE `Announce`  (
  `infoNum` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `releaseDate` date NULL DEFAULT NULL,
  PRIMARY KEY (`infoNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Announce
-- ----------------------------
INSERT INTO `Announce` VALUES (1, '0173559', '欢迎成为本系统的用户，祝您使用愉快!', '2019-06-14');
INSERT INTO `Announce` VALUES (2, '0173559', 'Hello!0173559，请输入...', '2019-06-24');
INSERT INTO `Announce` VALUES (3, '0173559', '亲爱的同学们，有一批新的书籍将于明天加入图书馆!', '2019-06-24');
INSERT INTO `Announce` VALUES (4, '0173559', 'Hello!0173559，请输入...hj', '2019-06-29');

-- ----------------------------
-- Table structure for BrowInfo
-- ----------------------------
DROP TABLE IF EXISTS `BrowInfo`;
CREATE TABLE `BrowInfo`  (
  `userNum` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bookName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate` date NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of BrowInfo
-- ----------------------------
INSERT INTO `BrowInfo` VALUES ('1002', '0173975', 'college English', 'Jack', '2019-06-13', '2019-07-13');
INSERT INTO `BrowInfo` VALUES ('1002', '0173975', 'college Math', 'Mary', '2019-06-13', '2019-07-13');
INSERT INTO `BrowInfo` VALUES ('1001', 'lgx123', 'C', 'Mark', '2019-05-12', '2019-06-12');
INSERT INTO `BrowInfo` VALUES ('1001', 'lgx123', 'C++', 'lgx', '2019-06-22', '2019-07-22');
INSERT INTO `BrowInfo` VALUES ('1003', '0173559', 'C', 'Mark', '2019-06-24', '2019-07-24');
INSERT INTO `BrowInfo` VALUES ('1003', '0173559', 'college Math', 'Jack', '2019-06-28', '2019-07-28');
INSERT INTO `BrowInfo` VALUES ('1003', '0173559', 'college English', 'Mary', '2019-06-29', '2019-07-29');
INSERT INTO `BrowInfo` VALUES ('1001', 'lgx123', 'Python', 'lgx3', '2019-06-29', '2019-07-29');

-- ----------------------------
-- Table structure for UserInfo
-- ----------------------------
DROP TABLE IF EXISTS `UserInfo`;
CREATE TABLE `UserInfo`  (
  `userNum` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1007 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of UserInfo
-- ----------------------------
INSERT INTO `UserInfo` VALUES (1001, 'lgx123', '123456', '15297879246@163.com', 'student');
INSERT INTO `UserInfo` VALUES (1002, '0173975', '123456', '1978669281@qq.com', 'student');
INSERT INTO `UserInfo` VALUES (1003, '0173559', '123456', '1631274378@163.com', 'admin');
INSERT INTO `UserInfo` VALUES (1005, '0173974', '123456', '16296859433@qq.com', 'student');
INSERT INTO `UserInfo` VALUES (1006, 'trfhrtfgh', '123', 'ersfgt@qq.com', 'student');

-- ----------------------------
-- Table structure for bookInfo
-- ----------------------------
DROP TABLE IF EXISTS `bookInfo`;
CREATE TABLE `bookInfo`  (
  `bookNum` varchar(10) CHARACTER SET big5 COLLATE big5_chinese_ci NOT NULL,
  `bookName` varchar(20) CHARACTER SET big5 COLLATE big5_chinese_ci NOT NULL,
  `author` varchar(20) CHARACTER SET big5 COLLATE big5_chinese_ci NOT NULL,
  `bookType` varchar(10) CHARACTER SET big5 COLLATE big5_chinese_ci NOT NULL,
  `remainNum` int(10) NOT NULL,
  PRIMARY KEY (`bookNum`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookInfo
-- ----------------------------
INSERT INTO `bookInfo` VALUES ('A1001', 'C', 'Mark', 'computer', 8);
INSERT INTO `bookInfo` VALUES ('A1002', 'college English', 'Mary', 'English', 0);
INSERT INTO `bookInfo` VALUES ('A1003', 'college Math', 'Jack', 'math', 10);
INSERT INTO `bookInfo` VALUES ('A1004', 'C++', 'lgx', 'Computer', 4);
INSERT INTO `bookInfo` VALUES ('A1005', 'Java', 'lgx1', 'computer', 3);
INSERT INTO `bookInfo` VALUES ('A1006', 'Python', 'lgx3', 'computer', 5);
INSERT INTO `bookInfo` VALUES ('A1007', 'C#', 'lgx4', 'computer', 8);
INSERT INTO `bookInfo` VALUES ('A1008', 'javaWeb', 'Johy', 'computer', 16);
INSERT INTO `bookInfo` VALUES ('A1009', 'ACM', 'Mark', 'Math', 15);

-- ----------------------------
-- Triggers structure for table BrowInfo
-- ----------------------------
DROP TRIGGER IF EXISTS `add_book`;
delimiter ;;
CREATE TRIGGER `add_book` AFTER INSERT ON `BrowInfo` FOR EACH ROW BEGIN
declare bk varchar(20);
declare rn int;
set bk=new.bookName;
set rn=(select remainNum from bookInfo where bookName=bk);
update bookInfo SET remainNum=rn-1 where bookName=bk;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table BrowInfo
-- ----------------------------
DROP TRIGGER IF EXISTS `reduce_book`;
delimiter ;;
CREATE TRIGGER `reduce_book` AFTER DELETE ON `BrowInfo` FOR EACH ROW BEGIN
declare bk varchar(20);
declare rn int;
set bk=old.bookName;
set rn=(select remainNum from bookInfo where bookName=bk);
update bookInfo SET remainNum=rn+1 where bookName=bk;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
