-- --------------------------------------------------------
-- 호스트:                          localhost
-- 서버 버전:                        10.5.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- test 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;

-- 테이블 test.lc_answer 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_answer` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_answer` varchar(255) NOT NULL,
  `q_id` int(10) NOT NULL,
  `s_id` int(10) NOT NULL,
  `a_writer` varchar(255) NOT NULL,
  `a_datetime` datetime NOT NULL,
  `u_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_answer:~22 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_answer` DISABLE KEYS */;
INSERT INTO `lc_answer` (`a_id`, `a_answer`, `q_id`, `s_id`, `a_writer`, `a_datetime`, `u_id`) VALUES
	(19, 'def', 15, 9, '박엘컴', '2022-11-01 09:52:54', 'zxc'),
	(20, 'dfrgee', 16, 9, '박엘컴', '2022-11-01 09:52:54', 'zxc'),
	(21, '2', 17, 9, '박엘컴', '2022-11-01 09:52:54', 'zxc'),
	(22, '3', 17, 9, '박엘컴', '2022-11-01 09:52:54', 'zxc'),
	(23, 'title', 18, 9, '박엘컴', '2022-11-01 09:52:54', 'zxc'),
	(24, 'ghi', 15, 9, '김엘컴', '2022-11-01 09:54:37', 'asd'),
	(25, 'nrrrex', 16, 9, '김엘컴', '2022-11-01 09:54:37', 'asd'),
	(26, '2', 17, 9, '김엘컴', '2022-11-01 09:54:37', 'asd'),
	(27, '1', 17, 9, '김엘컴', '2022-11-01 09:54:37', 'asd'),
	(28, '4', 17, 9, '김엘컴', '2022-11-01 09:54:37', 'asd'),
	(29, 'name', 18, 9, '김엘컴', '2022-11-01 09:54:37', 'asd'),
	(35, 'abc', 15, 9, '이엘컴', '2022-11-01 10:04:12', 'qwe'),
	(36, 'nrtjut', 16, 9, '이엘컴', '2022-11-01 10:04:12', 'qwe'),
	(37, '3', 17, 9, '이엘컴', '2022-11-01 10:04:12', 'qwe'),
	(38, '4', 17, 9, '이엘컴', '2022-11-01 10:04:12', 'qwe'),
	(39, 'date', 18, 9, '이엘컴', '2022-11-01 10:04:12', 'qwe'),
	(40, '30', 36, 22, '김자바', '2022-11-07 09:40:17', 'z'),
	(41, 'programmer', 37, 22, '김자바', '2022-11-07 09:40:17', 'z'),
	(42, '20', 36, 22, '김엘컴', '2022-11-07 09:40:45', 'asd'),
	(43, 'teacher', 37, 22, '김엘컴', '2022-11-07 09:40:45', 'asd'),
	(44, '20', 36, 22, '이엘컴', '2022-11-07 09:40:59', 'qwe'),
	(45, 'etc', 37, 22, '이엘컴', '2022-11-07 09:40:59', 'qwe');
/*!40000 ALTER TABLE `lc_answer` ENABLE KEYS */;

-- 테이블 test.lc_auth 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_auth` (
  `u_id` varchar(50) DEFAULT NULL,
  `u_auth` varchar(50) DEFAULT NULL,
  KEY `FK_lc_auth_lc_user` (`u_id`),
  CONSTRAINT `FK_lc_auth_lc_user` FOREIGN KEY (`u_id`) REFERENCES `lc_user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_auth:~11 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_auth` DISABLE KEYS */;
INSERT INTO `lc_auth` (`u_id`, `u_auth`) VALUES
	('tontonjeong', 'ROLE_USER'),
	('saram', 'ROLE_USER'),
	('leesin', 'ROLE_USER'),
	('graves', 'ROLE_USER'),
	('darius', 'ROLE_USER'),
	('z', 'ROLE_USER'),
	('asd', 'ROLE_USER'),
	('qwe', 'ROLE_USER'),
	('zxc', 'ROLE_USER'),
	('a', 'ROLE_ADMIN'),
	('a', 'ROLE_USER');
/*!40000 ALTER TABLE `lc_auth` ENABLE KEYS */;

-- 테이블 test.lc_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_board` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(50) DEFAULT NULL,
  `b_content` text DEFAULT NULL,
  `b_writer` varchar(50) DEFAULT NULL,
  `b_datetime` datetime DEFAULT current_timestamp(),
  `b_brdhit` int(11) DEFAULT NULL,
  `b_group` int(11) DEFAULT NULL,
  `b_order` int(11) DEFAULT NULL,
  `b_depth` int(11) DEFAULT NULL,
  `u_id` varchar(255) NOT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_board:~12 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_board` DISABLE KEYS */;
INSERT INTO `lc_board` (`b_id`, `b_title`, `b_content`, `b_writer`, `b_datetime`, `b_brdhit`, `b_group`, `b_order`, `b_depth`, `u_id`) VALUES
	(1, 'asdf', 'wwwwwwwww', '관리자', '2022-05-09 09:38:45', 9, 1, 1, 0, 'a'),
	(2, 'qqqq', 'sdqwewqrewqe', '엄준식', '2022-05-11 09:57:52', 20, 2, 1, 0, 'saram'),
	(3, 'asdasdssaf', 'ggywqtsacfdfd', '관리자', '2022-05-24 09:59:34', 6, 3, 1, 0, 'a'),
	(4, 'qfdfafewrewtqewtqwerwer', 'wqeqweeqwr', '관리자', '2022-05-24 10:12:17', 1, 4, 1, 0, 'a'),
	(5, 'hefdgsdfwet', 'wqeqweeqwr', '관리자', '2022-05-24 10:12:24', 20, 5, 1, 0, 'a'),
	(13, 'zxc', 'zxccc', '김자바', '2022-10-07 11:06:15', 414, 13, 1, 0, 'z'),
	(25, 'qwe', 'qwe', '김자바', '2022-10-11 10:04:12', 2, 13, 5, 1, 'z'),
	(26, 'dfg', 'dfg', '김자바', '2022-10-11 10:04:25', 2, 13, 3, 1, 'z'),
	(27, 'tyu', 'tyu', '김자바', '2022-10-11 10:04:47', 14, 13, 2, 1, 'z'),
	(28, 'jkl', 'jkl', '김자바', '2022-10-11 10:05:05', 0, 13, 4, 2, 'z'),
	(29, 'eee', 'fvgwe', '박엘컴', '2022-11-04 12:02:14', 83, 29, 1, 0, 'zxc'),
	(151, 'zxcz', 'zxcz', '김자바', '2022-11-22 10:32:17', 30, 151, 1, 0, 'z');
/*!40000 ALTER TABLE `lc_board` ENABLE KEYS */;

-- 테이블 test.lc_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_comment` (
  `c_num` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) DEFAULT NULL,
  `c_content` varchar(255) DEFAULT NULL,
  `c_writer` varchar(50) DEFAULT NULL,
  `c_group` int(11) DEFAULT NULL,
  `c_order` int(11) DEFAULT NULL,
  `c_depth` int(11) DEFAULT NULL,
  `c_datetime` datetime DEFAULT NULL,
  `c_showreply` int(10) DEFAULT 0,
  `c_showedit` int(10) DEFAULT 0,
  `u_id` varchar(255) NOT NULL,
  PRIMARY KEY (`c_num`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_comment:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_comment` DISABLE KEYS */;
INSERT INTO `lc_comment` (`c_num`, `b_id`, `c_content`, `c_writer`, `c_group`, `c_order`, `c_depth`, `c_datetime`, `c_showreply`, `c_showedit`, `u_id`) VALUES
	(1, 13, 'abc', '김자바', 1, 1, 0, '2022-10-13 09:53:03', 0, 0, 'z'),
	(2, 13, '123', '김자바', 2, 1, 0, '2022-10-13 10:39:42', 0, 0, 'z'),
	(3, 13, 'ABC', '김자바', 3, 1, 0, '2022-10-13 10:44:08', 0, 0, 'z'),
	(7, 13, '345', '김자바', 7, 1, 0, '2022-10-13 10:58:07', 0, 0, 'z'),
	(48, 13, '222', '김자바', 48, 1, 0, '2022-10-14 09:26:31', 0, 0, 'z'),
	(59, 13, 'test', '김자바', 48, 6, 1, '2022-10-17 11:57:30', 0, 0, 'z'),
	(60, 13, 'test2', '김자바', 48, 5, 1, '2022-10-17 11:59:31', 0, 0, 'z'),
	(61, 13, 'test3', '김자바', 48, 3, 1, '2022-10-18 09:08:12', 0, 0, 'z'),
	(69, 29, 'bbb', '김자바', 69, 1, 0, '2022-11-10 10:48:51', 0, 0, 'z'),
	(70, 29, 'eee', '이엘컴', 70, 1, 0, '2022-11-10 11:59:26', 0, 0, 'qwe');
/*!40000 ALTER TABLE `lc_comment` ENABLE KEYS */;

-- 테이블 test.lc_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_file` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) DEFAULT NULL,
  `fileName` varchar(1000) DEFAULT NULL,
  `fileRealName` varchar(1000) DEFAULT NULL,
  `fileUrl` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `b_id` (`b_id`),
  CONSTRAINT `FK_lc_file_lc_board` FOREIGN KEY (`b_id`) REFERENCES `lc_board` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_file:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `lc_file` ENABLE KEYS */;

-- 테이블 test.lc_item 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_item` (
  `i_id` int(10) NOT NULL AUTO_INCREMENT,
  `q_id` int(10) NOT NULL,
  `i_item` varchar(255) NOT NULL,
  `s_id` int(10) NOT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_item:~18 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_item` DISABLE KEYS */;
INSERT INTO `lc_item` (`i_id`, `q_id`, `i_item`, `s_id`) VALUES
	(29, 15, 'abc', 9),
	(30, 15, 'def', 9),
	(31, 15, 'ghi', 9),
	(32, 17, '1', 9),
	(33, 17, '2', 9),
	(34, 17, '3', 9),
	(35, 17, '4', 9),
	(36, 18, 'name', 9),
	(37, 18, 'date', 9),
	(38, 18, 'title', 9),
	(54, 36, '10', 22),
	(55, 36, '20', 22),
	(56, 36, '30', 22),
	(57, 36, '40', 22),
	(58, 37, 'programmer', 22),
	(59, 37, 'police', 22),
	(60, 37, 'teacher', 22),
	(61, 37, 'etc', 22);
/*!40000 ALTER TABLE `lc_item` ENABLE KEYS */;

-- 테이블 test.lc_question 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_question` (
  `q_id` int(10) NOT NULL AUTO_INCREMENT,
  `s_id` int(10) NOT NULL,
  `q_title` varchar(255) NOT NULL,
  `q_type` int(10) NOT NULL,
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_question:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_question` DISABLE KEYS */;
INSERT INTO `lc_question` (`q_id`, `s_id`, `q_title`, `q_type`) VALUES
	(15, 9, 'Q1', 1),
	(16, 9, 'Q2', 2),
	(17, 9, 'Q3', 3),
	(18, 9, 'Q4', 4),
	(36, 22, 'age', 1),
	(37, 22, 'job', 1);
/*!40000 ALTER TABLE `lc_question` ENABLE KEYS */;

-- 테이블 test.lc_reply 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_reply` (
  `r_idx` int(10) NOT NULL AUTO_INCREMENT,
  `r_title` varchar(255) NOT NULL,
  `r_content` varchar(255) NOT NULL,
  `r_writer` varchar(255) NOT NULL,
  `r_datetime` varchar(255) DEFAULT NULL,
  `r_brdhit` int(10) NOT NULL,
  `r_group` int(10) NOT NULL,
  `r_order` int(10) NOT NULL,
  `r_depth` int(10) NOT NULL,
  PRIMARY KEY (`r_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_reply:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `lc_reply` ENABLE KEYS */;

-- 테이블 test.lc_survey 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_survey` (
  `s_id` int(10) NOT NULL AUTO_INCREMENT,
  `s_title` varchar(255) NOT NULL,
  `s_subtitle` longtext NOT NULL DEFAULT '',
  `s_writer` varchar(255) NOT NULL,
  `s_datetime` datetime NOT NULL DEFAULT current_timestamp(),
  `u_id` varchar(255) NOT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_survey:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_survey` DISABLE KEYS */;
INSERT INTO `lc_survey` (`s_id`, `s_title`, `s_subtitle`, `s_writer`, `s_datetime`, `u_id`) VALUES
	(9, 'Survey', 'test survey ', '김자바', '2022-10-27 10:46:08', 'z'),
	(22, 'Survey2', 'gwrrrrrrrrrgwx', '박엘컴', '2022-11-07 09:39:53', 'zxc');
/*!40000 ALTER TABLE `lc_survey` ENABLE KEYS */;

-- 테이블 test.lc_upload 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_upload` (
  `l_id` int(10) NOT NULL AUTO_INCREMENT,
  `b_id` int(10) NOT NULL,
  `l_upload` varchar(255) NOT NULL,
  `l_filename` varchar(255) NOT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_upload:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_upload` DISABLE KEYS */;
INSERT INTO `lc_upload` (`l_id`, `b_id`, `l_upload`, `l_filename`) VALUES
	(88, 151, 'd3a709a8-4cf1-4ec5-8556-6c9576467bb6.png', 'main_section02_01.png'),
	(89, 151, '39dc0284-0e9d-49a8-b2db-3b42ab45346d.jpg', 'shutterstock_524181190.jpg');
/*!40000 ALTER TABLE `lc_upload` ENABLE KEYS */;

-- 테이블 test.lc_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `lc_user` (
  `u_id` varchar(50) NOT NULL,
  `u_password` varchar(200) DEFAULT NULL,
  `u_name` varchar(50) DEFAULT NULL,
  `u_phone` varchar(50) DEFAULT NULL,
  `u_datetime` datetime DEFAULT current_timestamp(),
  `u_isAccountNonExpired` tinyint(4) DEFAULT NULL,
  `u_isAccountNonLocked` tinyint(4) DEFAULT NULL,
  `u_isCredentialNonExpired` tinyint(4) DEFAULT NULL,
  `u_isEnabled` tinyint(4) DEFAULT NULL,
  `u_grade` varchar(255) DEFAULT '회원',
  `u_level` int(10) DEFAULT 1,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.lc_user:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lc_user` DISABLE KEYS */;
INSERT INTO `lc_user` (`u_id`, `u_password`, `u_name`, `u_phone`, `u_datetime`, `u_isAccountNonExpired`, `u_isAccountNonLocked`, `u_isCredentialNonExpired`, `u_isEnabled`, `u_grade`, `u_level`) VALUES
	('a', '$2a$10$PvXyzyy8D4b4PnMS11zayOuHzMmeOWRkh60WZA51gvpQRFKIOvody', '관리자', '01011111111', '2022-10-19 11:38:09', 1, 1, 1, 1, '관리자', 2),
	('asd', '$2a$10$CCOT5r3rXvI1snpYHdssRe1rYx2SgvNiHoGWEMrWp9QiNDWo46xu.', '김엘컴', '01065218784', '2022-11-01 09:54:10', 1, 1, 1, 1, '회원', 1),
	('darius', '$2a$10$4XpZIUqlnMzofdJlHFmtGuZ23jn7IYKDU4elNPIke.SpTkIbJXmlC', '다리우스1', '01020204982', '2022-04-27 09:42:15', 1, 1, 1, 1, '회원', 1),
	('graves', '$2a$10$fvhoSjl/Wif1cZpu4G9gbO13JZfCUfTLz0ZDPVMPJEwVeTV0myFHy', '그레이브즈', '01030203020', '2022-04-26 11:30:51', 1, 1, 1, 1, '회원', 1),
	('leesin', '$2a$10$.lD6n0EeDDQ0sHONiuNaj.BHRSbZGNLpbN0oPAtSZZg2.RoiVNFf.', '리신', '01000000000', '2022-04-26 11:19:58', 1, 1, 1, 1, '회원', 1),
	('qwe', '$2a$10$Ppb8q7d1S8.CjfQXVINBOOYw4kVM/6.5o1kLFv9QOAMhkk/L9LAXq', '이엘컴', '01023456789', '2022-11-01 10:01:11', 1, 1, 1, 1, '회원', 1),
	('saram', '$2a$10$S.aEnRnt6zLbF2GPmazIB.OqM4SwV0k/kMjScE826343Z.Q/LveWi', '엄준식', '01044444444', '2022-02-15 09:19:38', 1, 1, 1, 1, '회원', 1),
	('tontonjeong', '$2a$10$z.NRJaYaQ.jQZa8g/KiXbOaFHHXkfiGxkMKVe.Vt8MpGgI/Gf6OKG', '톤톤', '01012345678', '2022-02-14 10:07:45', 1, 1, 1, 1, '회원', 1),
	('z', '$2a$10$Np03E8Ri0yCTQJaFWjNMAuVcKlYvjSOyrLVWpvAtDiC4QQ60./eZ6', '김자바', '01066842697', '2022-09-30 11:47:26', 1, 1, 1, 1, '회원', 1),
	('zxc', '$2a$10$UEmkpy3JdrcQq7wRL4K4OO2Vs2EDebwbaAoWJ9z/CmqYollY1ttyK', '박엘컴', '01098534866', '2022-10-05 11:42:22', 1, 1, 1, 1, '회원', 1);
/*!40000 ALTER TABLE `lc_user` ENABLE KEYS */;

-- 테이블 test.spring_session 구조 내보내기
CREATE TABLE IF NOT EXISTS `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- 테이블 데이터 test.spring_session:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;

-- 테이블 test.spring_session_attributes 구조 내보내기
CREATE TABLE IF NOT EXISTS `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- 테이블 데이터 test.spring_session_attributes:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;

-- 테이블 test.vue_reanswers 구조 내보내기
CREATE TABLE IF NOT EXISTS `vue_reanswers` (
  `s_idx` int(11) DEFAULT NULL,
  `q_idx` int(11) DEFAULT NULL,
  `r_idx` int(11) DEFAULT NULL,
  `a` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.vue_reanswers:~29 rows (대략적) 내보내기
/*!40000 ALTER TABLE `vue_reanswers` DISABLE KEYS */;
INSERT INTO `vue_reanswers` (`s_idx`, `q_idx`, `r_idx`, `a`) VALUES
	(2, 9, 1, '닭갈비'),
	(2, 10, 2, '초밥'),
	(2, 9, 3, '닭갈비'),
	(2, 10, 4, '돈가스'),
	(2, 9, 5, '닭갈비'),
	(2, 10, 6, '초밥'),
	(2, 10, 6, '돈가스'),
	(1, 4, 17, '가렌'),
	(1, 5, 18, '니달리'),
	(1, 5, 18, '엘리스'),
	(1, 6, 19, '아리'),
	(1, 7, 20, '베인'),
	(1, 8, 21, '쓰레쉬'),
	(1, 4, 22, '이렐리아'),
	(1, 5, 23, '니달리'),
	(1, 5, 23, '리 신'),
	(1, 6, 24, '카타리나'),
	(1, 7, 25, '징크스'),
	(1, 8, 26, '레오나'),
	(1, 4, 27, '가렌'),
	(1, 5, 28, '니달리'),
	(1, 6, 29, '카타리나'),
	(1, 7, 30, '트리스타나'),
	(1, 8, 31, '알리스타'),
	(1, 4, 32, '다리우스'),
	(1, 5, 33, '니달리'),
	(1, 6, 34, '빅토르'),
	(1, 7, 35, '케이틀린'),
	(1, 8, 36, '블리츠크랭크');
/*!40000 ALTER TABLE `vue_reanswers` ENABLE KEYS */;

-- 테이블 test.vue_response 구조 내보내기
CREATE TABLE IF NOT EXISTS `vue_response` (
  `sub_idx` int(11) DEFAULT NULL,
  `q_idx` int(11) DEFAULT NULL,
  `r_idx` int(11) DEFAULT NULL,
  `t` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.vue_response:~34 rows (대략적) 내보내기
/*!40000 ALTER TABLE `vue_response` DISABLE KEYS */;
INSERT INTO `vue_response` (`sub_idx`, `q_idx`, `r_idx`, `t`) VALUES
	(NULL, NULL, 0, NULL),
	(11, 9, 1, 'RadioQ'),
	(11, 10, 2, 'CheckQ'),
	(12, 9, 3, 'RadioQ'),
	(12, 10, 4, 'CheckQ'),
	(13, 9, 5, 'RadioQ'),
	(13, 10, 6, 'CheckQ'),
	(14, 4, 7, 'RadioQ'),
	(14, 5, 8, 'CheckQ'),
	(14, 0, 9, 'DropQ'),
	(14, 0, 10, 'ShortQ'),
	(14, 0, 11, 'LongQ'),
	(15, 4, 12, 'RadioQ'),
	(15, 5, 13, 'CheckQ'),
	(15, 0, 14, 'DropQ'),
	(15, 0, 15, 'ShortQ'),
	(15, 0, 16, 'LongQ'),
	(16, 4, 17, 'RadioQ'),
	(16, 5, 18, 'CheckQ'),
	(16, 6, 19, 'DropQ'),
	(16, 7, 20, 'ShortQ'),
	(16, 8, 21, 'LongQ'),
	(17, 4, 22, 'RadioQ'),
	(17, 5, 23, 'CheckQ'),
	(17, 6, 24, 'DropQ'),
	(17, 7, 25, 'ShortQ'),
	(17, 8, 26, 'LongQ'),
	(18, 4, 27, 'RadioQ'),
	(18, 5, 28, 'CheckQ'),
	(18, 6, 29, 'DropQ'),
	(18, 7, 30, 'ShortQ'),
	(18, 8, 31, 'LongQ'),
	(19, 4, 32, 'RadioQ'),
	(19, 5, 33, 'CheckQ'),
	(19, 6, 34, 'DropQ'),
	(19, 7, 35, 'ShortQ'),
	(19, 8, 36, 'LongQ');
/*!40000 ALTER TABLE `vue_response` ENABLE KEYS */;

-- 테이블 test.vue_submission 구조 내보내기
CREATE TABLE IF NOT EXISTS `vue_submission` (
  `s_idx` int(11) DEFAULT NULL,
  `sub_idx` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sub_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 테이블 데이터 test.vue_submission:~8 rows (대략적) 내보내기
/*!40000 ALTER TABLE `vue_submission` DISABLE KEYS */;
INSERT INTO `vue_submission` (`s_idx`, `sub_idx`) VALUES
	(2, 11),
	(2, 12),
	(2, 13),
	(1, 14),
	(1, 15),
	(1, 16),
	(1, 17),
	(1, 18),
	(1, 19);
/*!40000 ALTER TABLE `vue_submission` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
