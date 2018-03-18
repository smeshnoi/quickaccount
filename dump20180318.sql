CREATE DATABASE  IF NOT EXISTS `quickaccount` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `quickaccount`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: quickaccount
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `account_types`
--

DROP TABLE IF EXISTS `account_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type_dc` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_types`
--

LOCK TABLES `account_types` WRITE;
/*!40000 ALTER TABLE `account_types` DISABLE KEYS */;
INSERT INTO `account_types` VALUES (1,'Cash and cash equivalents','DEBIT'),(2,'Accounts receivable (A/R)','DEBIT'),(3,'Current assets','DEBIT'),(4,'Fixed assets','DEBIT'),(5,'Non-current assets','DEBIT'),(6,'Current liabilities','CREDIT'),(7,'Non-current liabilities','CREDIT'),(8,'Owner\'s equity','CREDIT');
/*!40000 ALTER TABLE `account_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) NOT NULL,
  `type_account_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ef8srjaet88qrf7w1mvtop0pp` (`account_name`),
  KEY `FKefnb8rab3vlxx90rs0dqntu8f` (`type_account_id`),
  KEY `FKnjuop33mo69pd79ctplkck40n` (`user_id`),
  CONSTRAINT `FKefnb8rab3vlxx90rs0dqntu8f` FOREIGN KEY (`type_account_id`) REFERENCES `account_types` (`id`),
  CONSTRAINT `FKnjuop33mo69pd79ctplkck40n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'Bank',1,NULL),(2,'Accounts Receivable (A/R)',2,NULL),(3,'Inventory',3,NULL),(4,'Inventory Asset',3,NULL),(5,'Loans to Others',3,NULL),(6,'Loans to Others:Loans to Others',3,NULL),(7,'Prepaid expenses',3,NULL),(8,'Uncategorised Asset',3,NULL),(9,'Property, plant and equipment',4,NULL),(10,'Assets held for sale',5,NULL),(11,'Dividends payable',5,NULL),(12,'Payroll liabilities',6,NULL),(13,'Short-term debit',6,NULL),(14,'Accrued holiday payable',7,NULL),(15,'Dividend disbursed',8,NULL),(16,'Test Account',3,1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `building` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `office` int(11) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `contractor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n9y3meaw9gpkabbtu0ile7nk4` (`contractor_id`),
  CONSTRAINT `FK5ymv27tq7gepi9lgn1phfpf9m` FOREIGN KEY (`contractor_id`) REFERENCES `contractor_legal_entity` (`contractor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `company_phone` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ryckgbr0k2fswi0e58yhj5hqn` (`company_email`),
  KEY `FK9l5d0fem75e59uwf9upwuf9du` (`user_id`),
  CONSTRAINT `FK9l5d0fem75e59uwf9upwuf9du` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Тест','as@as.rr','+375292111111','Надувательство',1);
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractor_individual_entity`
--

DROP TABLE IF EXISTS `contractor_individual_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contractor_individual_entity` (
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `number_passport` varchar(255) NOT NULL,
  `contractor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`contractor_id`),
  UNIQUE KEY `UK_58019htrjow0c2d3y91xbq051` (`number_passport`),
  CONSTRAINT `FKpdv2xyyp62c8tg1elvpy1kv4y` FOREIGN KEY (`contractor_id`) REFERENCES `contractors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractor_individual_entity`
--

LOCK TABLES `contractor_individual_entity` WRITE;
/*!40000 ALTER TABLE `contractor_individual_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `contractor_individual_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractor_legal_entity`
--

DROP TABLE IF EXISTS `contractor_legal_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contractor_legal_entity` (
  `account` varchar(255) DEFAULT NULL,
  `unn` varchar(255) DEFAULT NULL,
  `contractor_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contractor_id`),
  CONSTRAINT `FKql2fbuj3jjgrdbiem2gcyn2xg` FOREIGN KEY (`contractor_id`) REFERENCES `contractors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractor_legal_entity`
--

LOCK TABLES `contractor_legal_entity` WRITE;
/*!40000 ALTER TABLE `contractor_legal_entity` DISABLE KEYS */;
INSERT INTO `contractor_legal_entity` VALUES ('123','123456789',1,'test address');
/*!40000 ALTER TABLE `contractor_legal_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractors`
--

DROP TABLE IF EXISTS `contractors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contractors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractor_name` varchar(45) NOT NULL,
  `type_contractor` varchar(45) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `UK_cyateprafb0re69fg4tdolo77` (`contractor_name`),
  KEY `FKf0abg48lr5lkm3b0shd3p2e6f` (`user_id`),
  CONSTRAINT `FKf0abg48lr5lkm3b0shd3p2e6f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractors`
--

LOCK TABLES `contractors` WRITE;
/*!40000 ALTER TABLE `contractors` DISABLE KEYS */;
INSERT INTO `contractors` VALUES (1,'ddd','COMPANY',1);
/*!40000 ALTER TABLE `contractors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractors_legal_entity`
--

DROP TABLE IF EXISTS `contractors_legal_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contractors_legal_entity` (
  `contractor_id` bigint(20) NOT NULL,
  `account` varchar(45) NOT NULL,
  `unn` varchar(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contractor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractors_legal_entity`
--

LOCK TABLES `contractors_legal_entity` WRITE;
/*!40000 ALTER TABLE `contractors_legal_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `contractors_legal_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `currency_UNIQUE` (`currency`),
  UNIQUE KEY `UK_h90x5sssmlhgwq75bd5q2trhu` (`currency`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'BYN'),(3,'EUR'),(4,'RUB'),(2,'USD');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_rates`
--

DROP TABLE IF EXISTS `currency_rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency_rates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rate` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `currency_in_id` bigint(20) DEFAULT NULL,
  `currency_out_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq4n6tw8y57g2j3yakikgbqplg` (`currency_in_id`),
  KEY `FKidijavmhflyyc9vnj004n964y` (`currency_out_id`),
  KEY `FKiborc3ej2e6l26weqfr6qe4ir` (`user_id`),
  CONSTRAINT `FKiborc3ej2e6l26weqfr6qe4ir` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKidijavmhflyyc9vnj004n964y` FOREIGN KEY (`currency_out_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FKq4n6tw8y57g2j3yakikgbqplg` FOREIGN KEY (`currency_in_id`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_rates`
--

LOCK TABLES `currency_rates` WRITE;
/*!40000 ALTER TABLE `currency_rates` DISABLE KEYS */;
INSERT INTO `currency_rates` VALUES (3,1.2406,'2018-03-17',4,1,1);
/*!40000 ALTER TABLE `currency_rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_role_UNIQUE` (`name_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount_debit` double NOT NULL,
  `amount_credit` double NOT NULL,
  `date` date NOT NULL,
  `type_dc` varchar(255) DEFAULT NULL,
  `account_debit_id` bigint(20) NOT NULL,
  `account_credit_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `contractor_id` bigint(20) NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeyusbb454wshg7tc93rvfkvte` (`company_id`),
  KEY `FK44bie5b6kwr7ujmne5mgqs0tq` (`contractor_id`),
  KEY `FKjv5x1dxk2uql8uvuex1cwvlo5` (`currency_id`),
  KEY `account_credit_id_fk_idx` (`account_credit_id`),
  KEY `FK525kib8yplbq6en6vkidy997t` (`account_debit_id`),
  CONSTRAINT `FK20w7wsg13u9srbq3bd7chfxdh` FOREIGN KEY (`account_debit_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK44bie5b6kwr7ujmne5mgqs0tq` FOREIGN KEY (`contractor_id`) REFERENCES `contractors` (`id`),
  CONSTRAINT `FK525kib8yplbq6en6vkidy997t` FOREIGN KEY (`account_debit_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK8d1luc43qgfb05velw2b34mab` FOREIGN KEY (`account_credit_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FKeyusbb454wshg7tc93rvfkvte` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  CONSTRAINT `FKjv5x1dxk2uql8uvuex1cwvlo5` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `account_credit_id_fk` FOREIGN KEY (`account_credit_id`) REFERENCES `accounts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_accounts`
--

DROP TABLE IF EXISTS `user_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_accounts` (
  `id` bigint(20) NOT NULL,
  `account_name` varchar(45) NOT NULL,
  `type_account_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_name_UNIQUE` (`account_name`),
  KEY `user_id_fk_idx` (`user_id`),
  KEY `user_account_type_account_fk_idx` (`type_account_id`),
  CONSTRAINT `user_account_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_account_type_account_fk` FOREIGN KEY (`type_account_id`) REFERENCES `account_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_accounts`
--

LOCK TABLES `user_accounts` WRITE;
/*!40000 ALTER TABLE `user_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `currency_id` bigint(20) NOT NULL,
  `password` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  UNIQUE KEY `UK_33uo7vet9c79ydfuwg1w848f` (`user_email`),
  UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`),
  KEY `currency_id_idx` (`currency_id`),
  CONSTRAINT `FK6yhw8fh1if5231903p4xjl1o6` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `currency_id` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','Sergey','Zinkevich','s-zinkevich@tut.by',NULL,1,'$2a$10$LwpL7e/dxFDk8ynbwvISqOKgD.ANKv7SAQWmkjfN3z9uQoPjE2CW.'),(2,'user1','sadsa','111','dsad@tut.by','+375291111111',3,'$2a$10$JaFSMtfdSqUEya053hm5ou7lEtQ203HR5A7wVQePGgHF7kNvK9OM6');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_r_fk_idx` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `role_id_r_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_r_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,1),(2,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-18 20:20:51
