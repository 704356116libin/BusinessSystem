-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_mydatabase
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Current Database: `db_mydatabase`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_mydatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_mydatabase`;

--
-- Table structure for table `tb_backshop`
--

DROP TABLE IF EXISTS `tb_backshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_backshop` (
  `id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `sum_money` decimal(10,2) DEFAULT NULL,
  `back_state` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_backshop`
--

LOCK TABLES `tb_backshop` WRITE;
/*!40000 ALTER TABLE `tb_backshop` DISABLE KEYS */;
INSERT INTO `tb_backshop` VALUES ('TH0001','SP003',10,5000.00,'退货中'),('TH0002','SP001',10,5000.00,'退货中');
/*!40000 ALTER TABLE `tb_backshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_buyshop`
--

DROP TABLE IF EXISTS `tb_buyshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_buyshop` (
  `id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  `sum_money` decimal(10,2) NOT NULL,
  `date` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_buyshop`
--

LOCK TABLES `tb_buyshop` WRITE;
/*!40000 ALTER TABLE `tb_buyshop` DISABLE KEYS */;
INSERT INTO `tb_buyshop` VALUES ('JH0001','SP002',10,789.00,'2017-07-09'),('JH0002','SP004',10,888.00,'2017-07-09'),('JH0003','SP003',10,456.00,'2017-07-09');
/*!40000 ALTER TABLE `tb_buyshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_client`
--

DROP TABLE IF EXISTS `tb_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_client` (
  `id` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `tel` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_client`
--

LOCK TABLES `tb_client` WRITE;
/*!40000 ALTER TABLE `tb_client` DISABLE KEYS */;
INSERT INTO `tb_client` VALUES ('KH0002','客户2','1111111','北京市海淀区'),('KH0004','客户4','1111111','北京市海淀区');
/*!40000 ALTER TABLE `tb_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_clientbuy`
--

DROP TABLE IF EXISTS `tb_clientbuy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_clientbuy` (
  `id` varchar(32) NOT NULL,
  `client_id` varchar(20) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  `sum_money` decimal(10,2) DEFAULT NULL,
  `pay_kind` varchar(10) NOT NULL,
  `state` varchar(10) NOT NULL,
  `operator_id` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_clientbuy`
--

LOCK TABLES `tb_clientbuy` WRITE;
/*!40000 ALTER TABLE `tb_clientbuy` DISABLE KEYS */;
INSERT INTO `tb_clientbuy` VALUES ('DD001','KH0002','SP002',10,6000.00,'网银','派送中','OP001'),('DD002','KH0004','SP005',1,1.00,'网银','派送中','OP003');
/*!40000 ALTER TABLE `tb_clientbuy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_department`
--

DROP TABLE IF EXISTS `tb_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_department` (
  `id` varchar(10) NOT NULL,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_department`
--

LOCK TABLES `tb_department` WRITE;
/*!40000 ALTER TABLE `tb_department` DISABLE KEYS */;
INSERT INTO `tb_department` VALUES ('BM001','后勤部'),('BM002','技术部');
/*!40000 ALTER TABLE `tb_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_operator`
--

DROP TABLE IF EXISTS `tb_operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_operator` (
  `id` varchar(15) NOT NULL,
  `name` varchar(10) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `department_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_operator`
--

LOCK TABLES `tb_operator` WRITE;
/*!40000 ALTER TABLE `tb_operator` DISABLE KEYS */;
INSERT INTO `tb_operator` VALUES ('OP001','操作员1','152435464','BM001'),('OP002','操作员2','146786786','BM001'),('OP003','操作员3','146786786','BM002');
/*!40000 ALTER TABLE `tb_operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_problem`
--

DROP TABLE IF EXISTS `tb_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_problem` (
  `id` varchar(10) NOT NULL,
  `problem` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_problem`
--

LOCK TABLES `tb_problem` WRITE;
/*!40000 ALTER TABLE `tb_problem` DISABLE KEYS */;
INSERT INTO `tb_problem` VALUES ('001','你最喜爱的人是谁？'),('002','你最喜欢的球星是谁？'),('003','你的生日'),('004','你的爱人姓名');
/*!40000 ALTER TABLE `tb_problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shopprovider`
--

DROP TABLE IF EXISTS `tb_shopprovider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shopprovider` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `address` varchar(100) NOT NULL,
  `fax` varchar(15) NOT NULL,
  `post_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shopprovider`
--

LOCK TABLES `tb_shopprovider` WRITE;
/*!40000 ALTER TABLE `tb_shopprovider` DISABLE KEYS */;
INSERT INTO `tb_shopprovider` VALUES ('GH00001','小米科技','111111','杭州商会大厦','无','55555'),('GH00002','天翼网络','222222','杭州商会大厦','无','55555'),('GH00003','华为公司','333333','杭州商会大厦','无','55555'),('GH0004','中兴科技','444444','北京海淀区','456','1131325');
/*!40000 ALTER TABLE `tb_shopprovider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shops`
--

DROP TABLE IF EXISTS `tb_shops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shops` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `model` varchar(10) NOT NULL,
  `ph` varchar(32) NOT NULL,
  `pzwh` varchar(50) NOT NULL,
  `provider_id` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shops`
--

LOCK TABLES `tb_shops` WRITE;
/*!40000 ALTER TABLE `tb_shops` DISABLE KEYS */;
INSERT INTO `tb_shops` VALUES ('SP001','小米5',500.00,'500g','ph45646','k454556','GH00004',10),('SP002','大保健',600.00,'500g','ph74813515','DD564611','GH00003',10),('SP003','腾讯电视',1000.00,'500g','ph74813515','DD564611','GH00003',10),('SP004','乐事手机',800.00,'500g','ph74813515','DD564611','GH00002',10),('SP005','乐事手',800.00,'500g','ph74813515','DD564611','GH00003',10);
/*!40000 ALTER TABLE `tb_shops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_users` (
  `name` varchar(32) NOT NULL,
  `answer` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `problem_id` varchar(10) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES ('demo123456','你','456123zxc','001'),('libin704356116','媳妇','704356116...','001'),('z12345678','你','123456789','001');
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-11 10:03:08
