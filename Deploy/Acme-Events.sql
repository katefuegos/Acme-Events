start transaction;
drop database if exists `Acme-Events`; create database `Acme-Events`;
use `Acme-Events`;
create user 'acme-user'@'%' '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';
by password by password
grant select, insert, update, delete on `Acme-Events`.* to 'acme-user'@'%'; grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view
on `Acme-Events`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Events
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
  KEY `ID2` (`is_suspicious`),
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
  KEY `administratorID2` (`is_suspicious`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (10,0,'C/Calle, 0','elena@gmail.com','\0','\0','admin','Admin','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','admin',9);
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
  KEY `ID1Box` (`name`,`actor`),
  KEY `FK_nu763pc0qdysx0gwsm2kt5twq` (`rootbox`),
  CONSTRAINT `FK_nu763pc0qdysx0gwsm2kt5twq` FOREIGN KEY (`rootbox`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (11,0,'','trash box',10,NULL),(12,0,'','in box',10,NULL),(13,0,'','out box',10,NULL),(14,0,'','spam box',10,NULL),(15,0,'','notification box',10,NULL);
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
  KEY `FK_r1w66keuinl1h36rknyjhgd8` (`root_category`),
  CONSTRAINT `FK_r1w66keuinl1h36rknyjhgd8` FOREIGN KEY (`root_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
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
  KEY `clientID2` (`is_suspicious`),
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
  KEY `FK_il0ov6ys8rysoclfsdnky9dyw` (`manager`),
  CONSTRAINT `FK_il0ov6ys8rysoclfsdnky9dyw` FOREIGN KEY (`manager`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (16,0,'http://www.tinyurl.com/acme-madruga',34,10,'Acme Events');
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
INSERT INTO `configuration_priorities` VALUES (16,'HIGH'),(16,'LOW'),(16,'NEUTRAL');
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
INSERT INTO `configuration_spam_words` VALUES (16,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0sext\0viagrat\0cialist\0nigeriat\0one milliont\0you\'ve been selectedx','EN'),(16,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0sexot\0viagrat\0cialist\0nigeriat\0	un millont\0has sido seleccionadox','ES');
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
INSERT INTO `configuration_welcome_message` VALUES (16,'Welcome to Acme Events! The best place to make and go to\n						events.\n					','EN'),(16,'¬°Bienvenidos a Acme Events! El major sitio para publicitar y\n						asistir a eventos.\n					','ES');
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
  KEY `FK_k2j62qo9688ahct3ve15n0u1c` (`category`),
  KEY `FK_73krrlbpm4r0ro4c6b5s5o7fr` (`club`),
  CONSTRAINT `FK_73krrlbpm4r0ro4c6b5s5o7fr` FOREIGN KEY (`club`) REFERENCES `club` (`id`),
  CONSTRAINT `FK_k2j62qo9688ahct3ve15n0u1c` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
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
  KEY `FK_2bp6xs3i5e87dh0sbvlk8qedk` (`client`),
  CONSTRAINT `FK_2bp6xs3i5e87dh0sbvlk8qedk` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
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
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
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
  KEY `managerID2` (`is_suspicious`),
  CONSTRAINT `FK_7fkueol8lcnvrbgwmmmyvsytv` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
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
  KEY `FK_auie6a7aaqn3ua7auwb4j5opj` (`box`),
  CONSTRAINT `FK_auie6a7aaqn3ua7auwb4j5opj` FOREIGN KEY (`box`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
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
  KEY `FK_9etsq0ta123tt3pnehvu52nen` (`client`),
  CONSTRAINT `FK_9etsq0ta123tt3pnehvu52nen` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinion`
--

LOCK TABLES `opinion` WRITE;
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
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
  KEY `FK_rmr50ofaqao1fpdrmo6w3fb3k` (`client`),
  CONSTRAINT `FK_rmr50ofaqao1fpdrmo6w3fb3k` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation_event`
--

LOCK TABLES `participation_event` WRITE;
/*!40000 ALTER TABLE `participation_event` DISABLE KEYS */;
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
  KEY `publiciterID2` (`is_suspicious`),
  CONSTRAINT `FK_636y0tiiv5o4u1mpycgavw8oy` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publiciter`
--

LOCK TABLES `publiciter` WRITE;
/*!40000 ALTER TABLE `publiciter` DISABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
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
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (9,0,NULL,'21232f297a57a5a743894a0e4a801fc3','admin');
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
INSERT INTO `user_account_authorities` VALUES (9,'ADMIN');
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

-- Dump completed on 2019-06-03 22:43:00
