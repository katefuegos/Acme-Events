start transaction;

drop database if exists `Acme-Events`;

create database `Acme-Events`;

use `Acme-Events`;

create user 'acme-user'@'%' 
	identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' 
	identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete
on `Acme-Events`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view
on `Acme-Events`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: acme-events
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  KEY `I1` (`is_suspicious`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  KEY `administratorI1` (`is_suspicious`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1004,0,'C/Calle, 0','elena@gmail.com','\0','\0','admin','Admin','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','admin',997);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_club`
--

DROP TABLE IF EXISTS `application_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_club` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `commentary` varchar(255) DEFAULT NULL,
  `moment_accepted` datetime DEFAULT NULL,
  `moment_rejected` datetime DEFAULT NULL,
  `moment_submitted` datetime DEFAULT NULL,
  `reason_reject` varchar(255) DEFAULT NULL,
  `publiciter` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c45htx3223xow43wli6njc8cu` (`publiciter`),
  CONSTRAINT `FK_c45htx3223xow43wli6njc8cu` FOREIGN KEY (`publiciter`) REFERENCES `publiciter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_club`
--

LOCK TABLES `application_club` WRITE;
/*!40000 ALTER TABLE `application_club` DISABLE KEYS */;
INSERT INTO `application_club` VALUES (1059,0,'commentary1','2019-05-19 12:00:00',NULL,'2019-05-19 00:00:00',NULL,1007),(1060,0,'commentary2',NULL,'2019-05-19 12:00:00','2019-05-19 00:00:00','reason1',1007),(1061,0,'commentary3','2019-05-19 12:00:00',NULL,'2019-05-19 00:00:00',NULL,1008),(1062,0,'commentary4',NULL,'2019-05-19 12:00:00','2019-05-19 00:00:00','reason2',1008);
/*!40000 ALTER TABLE `application_club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_job`
--

DROP TABLE IF EXISTS `application_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_job` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mai3dtvmi6qm8bmshr9fyxi6q` (`client`),
  CONSTRAINT `FK_mai3dtvmi6qm8bmshr9fyxi6q` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_job`
--

LOCK TABLES `application_job` WRITE;
/*!40000 ALTER TABLE `application_job` DISABLE KEYS */;
INSERT INTO `application_job` VALUES (1091,0,'comments1','2019-05-19 12:00:00',1009),(1092,0,'comments1','2019-05-19 12:00:00',1010);
/*!40000 ALTER TABLE `application_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  `rootbox` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`name`),
  KEY `I2` (`actor`),
  KEY `I3` (`is_system`),
  KEY `I4` (`name`),
  KEY `FK_nu763pc0qdysx0gwsm2kt5twq` (`rootbox`),
  CONSTRAINT `FK_nu763pc0qdysx0gwsm2kt5twq` FOREIGN KEY (`rootbox`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (1011,0,'','trash box',1004,NULL),(1012,0,'','in box',1004,NULL),(1013,0,'','out box',1004,NULL),(1014,0,'','spam box',1004,NULL),(1015,0,'','notification box',1004,NULL),(1016,0,'','trash box',1005,NULL),(1017,0,'','in box',1005,NULL),(1018,0,'','out box',1005,NULL),(1019,0,'','spam box',1005,NULL),(1020,0,'','notification box',1005,NULL),(1021,0,'\0','custom box',1005,NULL),(1022,0,'','trash box',1006,NULL),(1023,0,'','in box',1006,NULL),(1024,0,'','out box',1006,NULL),(1025,0,'','spam box',1006,NULL),(1026,0,'','notification box',1006,NULL),(1027,0,'\0','custom box',1006,NULL),(1028,0,'','trash box',1007,NULL),(1029,0,'','in box',1007,NULL),(1030,0,'','out box',1007,NULL),(1031,0,'','spam box',1007,NULL),(1032,0,'','notification box',1007,NULL),(1033,0,'','trash box',1008,NULL),(1034,0,'','in box',1008,NULL),(1035,0,'','out box',1008,NULL),(1036,0,'','spam box',1008,NULL),(1037,0,'','notification box',1008,NULL),(1038,0,'','trash box',1009,NULL),(1039,0,'','in box',1009,NULL),(1040,0,'','out box',1009,NULL),(1041,0,'','spam box',1009,NULL),(1042,0,'','notification box',1009,NULL),(1043,0,'','trash box',1010,NULL),(1044,0,'','in box',1010,NULL),(1045,0,'','out box',1010,NULL),(1046,0,'','spam box',1010,NULL),(1047,0,'','notification box',1010,NULL);
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_subboxes`
--

DROP TABLE IF EXISTS `box_subboxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_subboxes` (
  `box` int(11) NOT NULL,
  `subboxes` int(11) NOT NULL,
  UNIQUE KEY `UK_en3mmxysveh9grqcs2402kf2u` (`subboxes`),
  KEY `FK_1f04mowpefxnkgpd12cg12qm0` (`box`),
  CONSTRAINT `FK_1f04mowpefxnkgpd12cg12qm0` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_en3mmxysveh9grqcs2402kf2u` FOREIGN KEY (`subboxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_subboxes`
--

LOCK TABLES `box_subboxes` WRITE;
/*!40000 ALTER TABLE `box_subboxes` DISABLE KEYS */;
/*!40000 ALTER TABLE `box_subboxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyed_offer`
--

DROP TABLE IF EXISTS `buyed_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyed_offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fcnbwd9y6g4wuxeh0l5y6q7i6` (`client`),
  CONSTRAINT `FK_fcnbwd9y6g4wuxeh0l5y6q7i6` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyed_offer`
--

LOCK TABLES `buyed_offer` WRITE;
/*!40000 ALTER TABLE `buyed_offer` DISABLE KEYS */;
INSERT INTO `buyed_offer` VALUES (1093,0,'4172711227177926','2019-05-19 15:00:00',1009),(1094,0,'4950731573628957','2019-05-19 15:30:00',1010);
/*!40000 ALTER TABLE `buyed_offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `root` bit(1) NOT NULL,
  `root_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`root`),
  KEY `FK_r1w66keuinl1h36rknyjhgd8` (`root_category`),
  CONSTRAINT `FK_r1w66keuinl1h36rknyjhgd8` FOREIGN KEY (`root_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1095,0,'',NULL),(1096,0,'\0',1095),(1097,1,'\0',1099),(1098,0,'\0',1095),(1099,0,'\0',1095),(1100,0,'\0',1099),(1101,0,'\0',1099),(1102,0,'\0',1099),(1103,0,'\0',1099),(1104,0,'\0',1099),(1105,0,'\0',1098),(1106,0,'\0',1098),(1107,0,'\0',1098),(1108,0,'\0',1098),(1109,0,'\0',1096),(1110,0,'\0',1096);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_title`
--

DROP TABLE IF EXISTS `category_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_title` (
  `category` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `title_key` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`category`,`title_key`),
  CONSTRAINT `FK_geeokps9ryonllm6codorphrk` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_title`
--

LOCK TABLES `category_title` WRITE;
/*!40000 ALTER TABLE `category_title` DISABLE KEYS */;
INSERT INTO `category_title` VALUES (1095,'CATEGORY','EN'),(1095,'CATEGORIA','ES'),(1096,'POPULAR','EN'),(1096,'POPULAR','ES'),(1097,'REGGAETON','EN'),(1097,'REGGAETON','ES'),(1098,'CLASSIC','EN'),(1098,'CL√ÅSICO','ES'),(1099,'MODERN','EN'),(1099,'MODERNO','ES'),(1100,'ELECTRO','EN'),(1100,'ELECTRO','ES'),(1101,'DEEP','EN'),(1101,'DEEP','ES'),(1102,'TECH','EN'),(1102,'TECH','ES'),(1103,'TRAP','EN'),(1103,'TRAP','ES'),(1104,'RAP','EN'),(1104,'RAP','ES'),(1105,'ORCHEST','EN'),(1105,'ORQUESTA','ES'),(1106,'PIANO','EN'),(1106,'PIANO','ES'),(1107,'VIOLIN','EN'),(1107,'VIOL√çN','ES'),(1108,'GUITAR','EN'),(1108,'GUITARRA','ES'),(1109,'ROCK','EN'),(1109,'ROCK','ES'),(1110,'POP','EN'),(1110,'POP','ES');
/*!40000 ALTER TABLE `category_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `credit_card` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jbaq00w3smkqgr08wl4q8cou0` (`user_account`),
  KEY `clientI1` (`is_suspicious`),
  KEY `FK_eie18dckx8ndm2113uhqfiuso` (`credit_card`),
  CONSTRAINT `FK_jbaq00w3smkqgr08wl4q8cou0` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_eie18dckx8ndm2113uhqfiuso` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1009,1,'C/Calle, 5','client1@gmail.com','\0','\0','Jacko','Jack','+34(456)567890123','https://images-na.ssl-images-amazon.com/images/I/51X8XmOiVDL.jpg','Johnson',1002,'00000000A',1111),(1010,1,'C/Calle, 6','client2@gmail.com','\0','\0','Snow','John','+34(456)678901234','http://image3.redbull.com/rbcom/010/2015-08-24/1331743117947_2/0012/0/66/597/623/1154/1500/2/john-jackson-portrait-wings-for-life-world-run-santa-clara-2015.jpg','Jackson',1003,'11111111A',1112);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `accepted` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `draft_mode` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `reason_reject` varchar(255) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `manager` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`draft_mode`),
  KEY `I2` (`accepted`),
  KEY `I3` (`manager`),
  KEY `I4` (`reason_reject`),
  CONSTRAINT `FK_il0ov6ys8rysoclfsdnky9dyw` FOREIGN KEY (`manager`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1063,0,'','Paseo mar√≠timo , Islantilla Huelva (Espa√±a)','Un club m√°gico en primera linea de la playa','\0','Santa Pura Club','https://media-cdn.tripadvisor.com/media/photo-s/0f/02/77/17/bonita-rides.jpg',NULL,8,1005),(1064,0,'','C/Calle falsa, 124, Sevilla','Nada como pasar un buen rato en el mejor nightclub de toda Sevilla.','\0','Drai\'s Club','https://web.ccpgamescdn.com/newssystem/media/73282/1/drais-night-club-event-photo-2.jpg',NULL,5,1006),(1065,0,'\0','adress3','description3','\0','Club3','https://www.logodesignteam.com/blog/wp-content/uploads/2016/11/Night_Club_and_Bar_Logos-770x360.jpg','reasonReject3',NULL,1006),(1066,0,'\0','adress4','description4','','Club4','https://www.logodesignteam.com/blog/wp-content/uploads/2016/11/Night_Club_and_Bar_Logos-770x360.jpg',NULL,NULL,1005),(1067,0,'\0','adress5','description5','','Club5','http://www.club5.com',NULL,NULL,1006),(1068,0,'','C/Calle falsa, 456, Madrid','Ba√±ate en la piscina de Long Island Club.','\0','Long Island Club',NULL,NULL,NULL,1006),(1069,0,'\0','C/Calle falsa, 456, Sevilla','Las mejores vistas de Sevilla.','\0','Beach Cocoa Club',NULL,NULL,NULL,1006);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_applications_club`
--

DROP TABLE IF EXISTS `club_applications_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_applications_club` (
  `club` int(11) NOT NULL,
  `applications_club` int(11) NOT NULL,
  UNIQUE KEY `UK_lrsn7sv79ilan5x63v7uw765k` (`applications_club`),
  KEY `FK_mu7vm1s6anqk0uugfgkcrihke` (`club`),
  CONSTRAINT `FK_mu7vm1s6anqk0uugfgkcrihke` FOREIGN KEY (`club`) REFERENCES `club` (`id`),
  CONSTRAINT `FK_lrsn7sv79ilan5x63v7uw765k` FOREIGN KEY (`applications_club`) REFERENCES `application_club` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_applications_club`
--

LOCK TABLES `club_applications_club` WRITE;
/*!40000 ALTER TABLE `club_applications_club` DISABLE KEYS */;
INSERT INTO `club_applications_club` VALUES (1063,1059),(1064,1061);
/*!40000 ALTER TABLE `club_applications_club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_follows`
--

DROP TABLE IF EXISTS `club_follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_follows` (
  `club` int(11) NOT NULL,
  `follows` int(11) NOT NULL,
  UNIQUE KEY `UK_q6ran7sn9s9uwd57q0n2osi0d` (`follows`),
  KEY `FK_qjgwui2rks3srt6e1fpeb7bgg` (`club`),
  CONSTRAINT `FK_qjgwui2rks3srt6e1fpeb7bgg` FOREIGN KEY (`club`) REFERENCES `club` (`id`),
  CONSTRAINT `FK_q6ran7sn9s9uwd57q0n2osi0d` FOREIGN KEY (`follows`) REFERENCES `follow` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_follows`
--

LOCK TABLES `club_follows` WRITE;
/*!40000 ALTER TABLE `club_follows` DISABLE KEYS */;
INSERT INTO `club_follows` VALUES (1063,1055),(1063,1057),(1064,1056),(1064,1058);
/*!40000 ALTER TABLE `club_follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `country_code` int(11) NOT NULL,
  `finder_max_results` int(11) NOT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `var_tax` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (1116,0,'https://www.milton.ca/en/live/resources/Events.jpg',34,10,'Acme Events',21);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_priorities`
--

DROP TABLE IF EXISTS `configuration_priorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_priorities` (
  `configuration` int(11) NOT NULL,
  `priorities` varchar(255) DEFAULT NULL,
  KEY `FK_jbxc1iogmimkjfe6rfflsbrk4` (`configuration`),
  CONSTRAINT `FK_jbxc1iogmimkjfe6rfflsbrk4` FOREIGN KEY (`configuration`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_priorities`
--

LOCK TABLES `configuration_priorities` WRITE;
/*!40000 ALTER TABLE `configuration_priorities` DISABLE KEYS */;
INSERT INTO `configuration_priorities` VALUES (1116,'HIGH'),(1116,'LOW'),(1116,'NEUTRAL');
/*!40000 ALTER TABLE `configuration_priorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_spam_words`
--

DROP TABLE IF EXISTS `configuration_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_spam_words` (
  `configuration` int(11) NOT NULL,
  `spam_words` tinyblob,
  `spam_words_key` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`configuration`,`spam_words_key`),
  CONSTRAINT `FK_qk3m1jkx4rq87ofjee19f3b33` FOREIGN KEY (`configuration`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_spam_words`
--

LOCK TABLES `configuration_spam_words` WRITE;
/*!40000 ALTER TABLE `configuration_spam_words` DISABLE KEYS */;
INSERT INTO `configuration_spam_words` VALUES (1116,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0sext\0viagrat\0cialist\0nigeriat\0one milliont\0you\'ve been selectedx','EN'),(1116,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0sexot\0viagrat\0cialist\0nigeriat\0	un millont\0has sido seleccionadox','ES');
/*!40000 ALTER TABLE `configuration_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_welcome_message`
--

DROP TABLE IF EXISTS `configuration_welcome_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_welcome_message` (
  `configuration` int(11) NOT NULL,
  `welcome_message` varchar(255) DEFAULT NULL,
  `welcome_message_key` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`configuration`,`welcome_message_key`),
  CONSTRAINT `FK_elnslrt6ishgh9y89wbg4vybr` FOREIGN KEY (`configuration`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_welcome_message`
--

LOCK TABLES `configuration_welcome_message` WRITE;
/*!40000 ALTER TABLE `configuration_welcome_message` DISABLE KEYS */;
INSERT INTO `configuration_welcome_message` VALUES (1116,'Welcome to Acme Events! The best place to make and go to\n						events.\n					','EN'),(1116,'¬°Bienvenidos a Acme Events! El mejor sitio para publicitar y\n						asistir a eventos.\n					','ES');
/*!40000 ALTER TABLE `configuration_welcome_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cvvcode` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (1111,0,110,'VISA',9,2021,'holderName1','4172711227177926'),(1112,0,308,'MASTER',4,2020,'holderName2','4950731573628957');
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `copy` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gent7yxpsyplweumuql7kiyqh` (`ticker`),
  KEY `FK_esw2wlji9ohfrygsjnkay4wgr` (`client`),
  CONSTRAINT `FK_esw2wlji9ohfrygsjnkay4wgr` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
INSERT INTO `curricula` VALUES (1075,0,'\0','email1@gmail.com','fullname1','http://www.link1.com','666666666','http://www.photo1.com','190519-1DR4RV',1009),(1076,0,'','email1@gmail.com','fullname1','http://www.link1.com','666666666','http://www.photo1.com','190519-4587SD',1009),(1077,0,'\0','email2@gmail.com','fullname2','http://www.link2.com','777777777','http://www.photo2.com','190519-4587DI',1010),(1078,0,'','email2@gmail.com','fullname2','http://www.link2.com','777777777','http://www.photo2.com','190519-589SDF',1010);
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_record`
--

DROP TABLE IF EXISTS `education_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `end_moment` datetime DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ed1v5mssvl0q0srpvf01kfkp7` (`curricula`),
  CONSTRAINT `FK_ed1v5mssvl0q0srpvf01kfkp7` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_record`
--

LOCK TABLES `education_record` WRITE;
/*!40000 ALTER TABLE `education_record` DISABLE KEYS */;
INSERT INTO `education_record` VALUES (1079,0,'comments1','2019-02-12 12:00:00','institution1','http://www.link1.com','2018-03-23 12:00:00','title1',1075),(1080,0,'comments1','2019-02-12 12:00:00','institution1','http://www.link1.com','2018-03-23 12:00:00','title1',1076),(1081,0,'comments2','2019-01-12 12:00:00','institution2','http://www.link2.com','2018-04-23 12:00:00','title2',1077),(1082,0,'comments2','2019-01-12 12:00:00','institution2','http://www.link2.com','2018-04-23 12:00:00','title2',1078);
/*!40000 ALTER TABLE `education_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `draft_mode` bit(1) NOT NULL,
  `moment_end` datetime DEFAULT NULL,
  `moment_published` datetime DEFAULT NULL,
  `moment_start` datetime DEFAULT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `score` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category` int(11) NOT NULL,
  `club` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qlvbe0jq039b5d9232k7sn86i` (`ticker`),
  KEY `I1` (`draft_mode`),
  KEY `I2` (`category`),
  KEY `I3` (`club`),
  KEY `I4` (`status`,`price`),
  CONSTRAINT `FK_73krrlbpm4r0ro4c6b5s5o7fr` FOREIGN KEY (`club`) REFERENCES `club` (`id`),
  CONSTRAINT `FK_k2j62qo9688ahct3ve15n0u1c` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1117,0,'---','Nada como una fiesta de la espuma para empezar el verano. Vente, no te arrepentir√°s.','\0','2019-05-25 07:00:00','2019-05-19 00:00:00','2019-05-25 00:00:00','http://postermywall.com.s3.amazonaws.com/posterpreviews/foam-party-flyer-template-31ce881859841e557c054cc708acf77f.jpg?ts=1456095158',20,8,'AVAILABLE','190519-AKD123','Fiesta de la espuma',1097,1063),(1118,0,'Sala 2','Noche de bailes, noches de dejarte la piel en la pista. No te lo pienses, vente.','\0','2019-05-25 06:00:00','2019-05-19 12:00:00','2019-05-24 22:00:00','http://designcrown.com/uploads/posts/2016-06/1466002594_designcrown.com_pocsm3lk6jpxgcv.jpeg',40,5,'AVAILABLE','190519-JD89SE','DubStep NIGHT',1098,1064),(1119,0,'---','Noche m√°gica','\0','2019-07-29 07:00:00','2019-02-19 00:00:00','2019-07-29 00:00:00','https://i.pinimg.com/originals/d4/fb/d5/d4fbd5a261c60d9ce8227861d59071b5.jpg',50,NULL,'AVAILABLE','190729-AKD126','Deep in techno',1101,1063),(1120,0,'---','Beach Party Summer. YoUr PaRtY!','\0','2019-08-28 07:00:00','2019-02-19 00:00:00','2019-08-28 00:00:00','https://media.istockphoto.com/vectors/beach-party-poster-for-music-festival-electronic-music-cover-for-or-vector-id825937736',50,NULL,'AVAILABLE','190828-AKD103','Beach Party Summer',1101,1063),(1121,0,'Sala 2','La mejor fiesta del mundo.','','2019-09-25 06:00:00',NULL,'2019-09-24 22:00:00',NULL,40,NULL,'AVAILABLE','190618-JDH4H4','Rap Night',1098,1064),(1122,0,'Sala 2','Ven a sentir la m√∫sica.','','2019-09-25 06:00:00',NULL,'2019-09-24 23:00:00',NULL,40,NULL,'AVAILABLE','210918-EUR467','House Night',1099,1063);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_opinions`
--

DROP TABLE IF EXISTS `event_opinions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_opinions` (
  `event` int(11) NOT NULL,
  `opinions` int(11) NOT NULL,
  UNIQUE KEY `UK_cycf4jyvh6fe9gb8nlfwu4y57` (`opinions`),
  KEY `FK_ixw6kb5y74k5rklnutoce9t6i` (`event`),
  CONSTRAINT `FK_ixw6kb5y74k5rklnutoce9t6i` FOREIGN KEY (`event`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_cycf4jyvh6fe9gb8nlfwu4y57` FOREIGN KEY (`opinions`) REFERENCES `opinion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_opinions`
--

LOCK TABLES `event_opinions` WRITE;
/*!40000 ALTER TABLE `event_opinions` DISABLE KEYS */;
INSERT INTO `event_opinions` VALUES (1117,1073),(1118,1074);
/*!40000 ALTER TABLE `event_opinions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_participations_event`
--

DROP TABLE IF EXISTS `event_participations_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_participations_event` (
  `event` int(11) NOT NULL,
  `participations_event` int(11) NOT NULL,
  UNIQUE KEY `UK_mhmfr4v6ol7gqcg0xwytmxvxw` (`participations_event`),
  KEY `FK_eyd7scxc8g0ia6mckdjg2r9tr` (`event`),
  CONSTRAINT `FK_eyd7scxc8g0ia6mckdjg2r9tr` FOREIGN KEY (`event`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_mhmfr4v6ol7gqcg0xwytmxvxw` FOREIGN KEY (`participations_event`) REFERENCES `participation_event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_participations_event`
--

LOCK TABLES `event_participations_event` WRITE;
/*!40000 ALTER TABLE `event_participations_event` DISABLE KEYS */;
INSERT INTO `event_participations_event` VALUES (1117,1070),(1117,1071),(1118,1072);
/*!40000 ALTER TABLE `event_participations_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`client`),
  CONSTRAINT `FK_2bp6xs3i5e87dh0sbvlk8qedk` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1055,0,'2019-05-19 00:00:00',1009),(1056,0,'2019-05-19 00:00:00',1009),(1057,0,'2019-05-19 00:00:00',1010),(1058,0,'2019-05-19 00:00:00',1010);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',2);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_offer`
--

DROP TABLE IF EXISTS `job_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `requirements` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  `event` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jsgg52tt3uiaoydd08de6uw6h` (`curricula`),
  UNIQUE KEY `UK_c82nhuwnvpm6v5y8f1c29jqi6` (`ticker`),
  KEY `FK_kpq2u1mf49973tdrkcofc33ty` (`event`),
  CONSTRAINT `FK_kpq2u1mf49973tdrkcofc33ty` FOREIGN KEY (`event`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_jsgg52tt3uiaoydd08de6uw6h` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_offer`
--

LOCK TABLES `job_offer` WRITE;
/*!40000 ALTER TABLE `job_offer` DISABLE KEYS */;
INSERT INTO `job_offer` VALUES (1123,0,'description1','requirements1',200,'OPEN','190519-1258SD','JobOffer1',1076,1117),(1124,0,'description2','requirements2',300,'OPEN','190519-128SED','JobOffer2',1078,1118);
/*!40000 ALTER TABLE `job_offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_offer_applications_job`
--

DROP TABLE IF EXISTS `job_offer_applications_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_offer_applications_job` (
  `job_offer` int(11) NOT NULL,
  `applications_job` int(11) NOT NULL,
  UNIQUE KEY `UK_2ikwq4fh27doc83u1ifbunhfm` (`applications_job`),
  KEY `FK_y9xuu083k5j4xp8nnk2hcdkp` (`job_offer`),
  CONSTRAINT `FK_y9xuu083k5j4xp8nnk2hcdkp` FOREIGN KEY (`job_offer`) REFERENCES `job_offer` (`id`),
  CONSTRAINT `FK_2ikwq4fh27doc83u1ifbunhfm` FOREIGN KEY (`applications_job`) REFERENCES `application_job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_offer_applications_job`
--

LOCK TABLES `job_offer_applications_job` WRITE;
/*!40000 ALTER TABLE `job_offer_applications_job` DISABLE KEYS */;
INSERT INTO `job_offer_applications_job` VALUES (1123,1091),(1124,1092);
/*!40000 ALTER TABLE `job_offer_applications_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7fkueol8lcnvrbgwmmmyvsytv` (`user_account`),
  KEY `managerI1` (`is_suspicious`),
  CONSTRAINT `FK_7fkueol8lcnvrbgwmmmyvsytv` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1005,0,'C/Calle falsa, 123','manager1@gmail.com','\0','\0','Snow','Jonh','+34(456)123456789','https://i.pinimg.com/736x/f3/35/30/f33530530e6a544f5838f2c98cda3f7e--kit-harrington-john-snow.jpg','Snow',998),(1006,0,'C/Calle, 2','manager2@gmail.com','\0','\0','C4pt41n M4rv3l','Brie','+34(456)234567890','https://i.pinimg.com/originals/89/da/f9/89daf9c74648fcfb6a7dd7dfb583210e.jpg','Larson',999);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `box` int(11) NOT NULL,
  `recipient` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`sender`),
  KEY `I2` (`box`),
  KEY `I3` (`recipient`),
  CONSTRAINT `FK_auie6a7aaqn3ua7auwb4j5opj` FOREIGN KEY (`box`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1048,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1017,1005,1004),(1049,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1023,1006,1004),(1050,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1029,1007,1004),(1051,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1034,1008,1004),(1052,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1039,1009,1004),(1053,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1044,1010,1004),(1054,0,'Bienvenido a Acme Events','2019-05-19 00:00:00','HIGH','Bienvenido al sistema','bienvenido,welcome',1012,1004,1004);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_record`
--

DROP TABLE IF EXISTS `miscellaneous_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3lv8jdb31kqlrvixjamveejhp` (`curricula`),
  CONSTRAINT `FK_3lv8jdb31kqlrvixjamveejhp` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_record`
--

LOCK TABLES `miscellaneous_record` WRITE;
/*!40000 ALTER TABLE `miscellaneous_record` DISABLE KEYS */;
INSERT INTO `miscellaneous_record` VALUES (1087,0,'comments1','http://www.link1.com','title1',1075),(1088,0,'comments1','http://www.link1.com','title1',1076),(1089,0,'comments2','http://www.link2.com','title2',1077),(1090,0,'comments2','http://www.link2.com','title2',1078);
/*!40000 ALTER TABLE `miscellaneous_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `draft_mode` bit(1) NOT NULL,
  `moment_published` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `event` int(11) NOT NULL,
  `publiciter` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iex7e8fs0fh89yxpcnm1orjkm` (`ticker`),
  KEY `FK_kr698efjh0pj6gnp0shwq81r7` (`event`),
  KEY `FK_bdot7aw656yjb7pktfpkfa8le` (`publiciter`),
  CONSTRAINT `FK_bdot7aw656yjb7pktfpkfa8le` FOREIGN KEY (`publiciter`) REFERENCES `publiciter` (`id`),
  CONSTRAINT `FK_kr698efjh0pj6gnp0shwq81r7` FOREIGN KEY (`event`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (1125,0,'description1','\0','2019-05-19 08:30:00',23,'190519-587KSD','offer1',1117,1007),(1126,0,'description2','\0','2019-05-19 08:45:00',33,'190519-587HSJ','offer2',1118,1008);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer_buyed_offers`
--

DROP TABLE IF EXISTS `offer_buyed_offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer_buyed_offers` (
  `offer` int(11) NOT NULL,
  `buyed_offers` int(11) NOT NULL,
  UNIQUE KEY `UK_rob5l8pmc30qv4ak3en8dbhc0` (`buyed_offers`),
  KEY `FK_g3e5h0qhc0sh5nvr6t5dapjj5` (`offer`),
  CONSTRAINT `FK_g3e5h0qhc0sh5nvr6t5dapjj5` FOREIGN KEY (`offer`) REFERENCES `offer` (`id`),
  CONSTRAINT `FK_rob5l8pmc30qv4ak3en8dbhc0` FOREIGN KEY (`buyed_offers`) REFERENCES `buyed_offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer_buyed_offers`
--

LOCK TABLES `offer_buyed_offers` WRITE;
/*!40000 ALTER TABLE `offer_buyed_offers` DISABLE KEYS */;
INSERT INTO `offer_buyed_offers` VALUES (1125,1093),(1126,1094);
/*!40000 ALTER TABLE `offer_buyed_offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinion`
--

DROP TABLE IF EXISTS `opinion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `score` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`client`),
  CONSTRAINT `FK_9etsq0ta123tt3pnehvu52nen` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinion`
--

LOCK TABLES `opinion` WRITE;
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
INSERT INTO `opinion` VALUES (1073,0,'Mis amigos y yo pasamos unos d√≠as en Islantilla, no me esperaba encontrar un club as√≠. Fue una grata sorpresa','2019-05-19 00:00:00',8,'Mejor club de islantilla',1009),(1074,0,'La noche fue muy buena, pero el lugar apenas tenia sitio para aparcar, es algo que valoro mucho.','2019-05-19 00:00:00',5,'No estuvo mal',1010);
/*!40000 ALTER TABLE `opinion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation_event`
--

DROP TABLE IF EXISTS `participation_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participation_event` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`client`),
  CONSTRAINT `FK_rmr50ofaqao1fpdrmo6w3fb3k` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation_event`
--

LOCK TABLES `participation_event` WRITE;
/*!40000 ALTER TABLE `participation_event` DISABLE KEYS */;
INSERT INTO `participation_event` VALUES (1070,0,'4172711227177926','2019-05-19 00:00:00',1009),(1071,0,'4950731573628957','2019-05-19 00:00:00',1010),(1072,0,'4950731573628957','2019-05-19 01:00:00',1010);
/*!40000 ALTER TABLE `participation_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professional_record`
--

DROP TABLE IF EXISTS `professional_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `end_moment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iudff7yi7f456uc1xrd1md7sw` (`curricula`),
  CONSTRAINT `FK_iudff7yi7f456uc1xrd1md7sw` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_record`
--

LOCK TABLES `professional_record` WRITE;
/*!40000 ALTER TABLE `professional_record` DISABLE KEYS */;
INSERT INTO `professional_record` VALUES (1083,0,'http://www.attachment1.com','comments1','2019-02-12 12:00:00','name1','role1','2018-03-23 12:00:00',1075),(1084,0,'http://www.attachment1.com','comments1','2019-02-12 12:00:00','name1','role1','2018-03-23 12:00:00',1076),(1085,0,'http://www.attachment2.com','comments2','2019-06-11 12:00:00','name2','role2','2018-04-12 12:00:00',1077),(1086,0,'http://www.attachment2.com','comments2','2019-06-11 12:00:00','name2','role2','2018-04-12 12:00:00',1078);
/*!40000 ALTER TABLE `professional_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publiciter`
--

DROP TABLE IF EXISTS `publiciter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publiciter` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_636y0tiiv5o4u1mpycgavw8oy` (`user_account`),
  KEY `publiciterI1` (`is_suspicious`),
  CONSTRAINT `FK_636y0tiiv5o4u1mpycgavw8oy` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publiciter`
--

LOCK TABLES `publiciter` WRITE;
/*!40000 ALTER TABLE `publiciter` DISABLE KEYS */;
INSERT INTO `publiciter` VALUES (1007,0,'C/Calle, 3','publiciter1@gmail.com','\0','\0','middleName3','Publiciter1','+34(456)345678901','https://www.publiciter1.com','surname3',1000),(1008,0,'C/Calle, 4','publiciter2@gmail.com','\0','\0','middleName4','Publiciter2','+34(456)456789012','https://www.publiciter2.com','surname4',1001);
/*!40000 ALTER TABLE `publiciter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_profile`
--

DROP TABLE IF EXISTS `social_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I1` (`actor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
INSERT INTO `social_profile` VALUES (1113,0,'http://www.url1.com','twitter','nick1',1009),(1114,0,'http://www.url2.com','facebook','nick2',1005),(1115,0,'http://www.url3.com','instagram','nick3',1007);
/*!40000 ALTER TABLE `social_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`),
  KEY `I1` (`username`),
  KEY `I2` (`enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (997,0,'','21232f297a57a5a743894a0e4a801fc3','admin'),(998,0,'','c240642ddef994358c96da82c0361a58','manager1'),(999,0,'','8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(1000,0,'','914517413a414b8ddc2e7825c4bd718e','publiciter1'),(1001,0,'','438c18f959a282cf5f5cef724ff710d4','publiciter2'),(1002,0,'','a165dd3c2e98d5d607181d0b87a4c66b','client1'),(1003,0,'','2c66045d4e4a90814ce9280272e510ec','client2');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (997,'ADMIN'),(998,'MANAGER'),(999,'MANAGER'),(1000,'PUBLICITER'),(1001,'PUBLICITER'),(1002,'CLIENT'),(1003,'CLIENT');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 14:47:45

commit;