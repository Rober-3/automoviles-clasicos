-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: automoviles_clasicos
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Current Database: `automoviles_clasicos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `automoviles_clasicos` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `automoviles_clasicos`;

--
-- Table structure for table `clasicos`
--

DROP TABLE IF EXISTS `clasicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasicos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `modelo` varchar(50) DEFAULT NULL,
  `marca` varchar(25) NOT NULL,
  `anio` int(11) NOT NULL,
  `foto` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `clasicos_UN` (`modelo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasicos`
--

LOCK TABLES `clasicos` WRITE;
/*!40000 ALTER TABLE `clasicos` DISABLE KEYS */;
INSERT INTO `clasicos` VALUES (1,'Mustang Fastback','Ford',1967,'https://motokiller.files.wordpress.com/2013/04/mump_1101_10_ford_1967_mustang_gt_fastback_left_quarter.jpg'),(2,'Bel Air','Chevrolet',1957,'http://i.ebayimg.com/00/s/MTIwOFgxNjAw/z/He0AAOSwX61ZFKN0/$_57.JPG?set_id=8800005007'),(3,'Cobra','Shelby',1965,'https://cdn.barrett-jackson.com/staging/carlist/items/Fullsize/Cars/60975/60975_Front_3-4_Web.jpg'),(4,'Mercury','Ford',1951,'https://i.pinimg.com/originals/33/01/e4/3301e443ebdf1c2e0683b87333576183.jpg'),(5,'Fairlane 500 Skyliner','Ford',1957,'https://www.supercars.net/blog/wp-content/uploads/2016/04/1957_Ford_Fairlane500Skyliner1.jpg'),(6,'Gran Torino','Ford',1975,'http://carphotos.cardomain.com/images/0000/21/37/401273.JPG?v=16045488?v=3'),(7,'Charger 500','Dodge',1970,'https://i.ytimg.com/vi/VuhotvebUvU/maxresdefault.jpg'),(10,'DMC-12','DeLorean Motor Company',1981,'https://s1.cdn.autoevolution.com/images/gallery/DeLorean-DMC-12-3031_26.jpg');
/*!40000 ALTER TABLE `clasicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `contrasena` varchar(25) NOT NULL,
  `imagen` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Armando','111','aaa'),(2,'Basilio','222','bbb'),(3,'Carmelo','333','ccc');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-12  9:11:47
