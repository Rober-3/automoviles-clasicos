-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: automoviles_clasicos
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
  `modelo` varchar(50) NOT NULL,
  `id_marca` int(11) NOT NULL,
  `anio` int(11) NOT NULL,
  `foto` varchar(150) NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_validacion` datetime DEFAULT NULL COMMENT 'Si su valor es null el clásico no es visible en la parte pública. Tiene que ser validado por un administrador',
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `clasicos_UN` (`modelo`),
  KEY `FK_id_marca` (`id_marca`),
  KEY `FK_id_usuario` (`id_usuario`),
  CONSTRAINT `FK_id_marca` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id`),
  CONSTRAINT `FK_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasicos`
--

LOCK TABLES `clasicos` WRITE;
/*!40000 ALTER TABLE `clasicos` DISABLE KEYS */;
INSERT INTO `clasicos` VALUES (1,'Mustang Fastback',4,1967,'img/FordMustangFastback.jpg','2020-07-13 12:28:38','2020-07-13 12:45:00',1),(2,'Bel Air',1,1957,'img/ChevroletBelAir.JPG','2020-07-13 12:28:38','2020-07-13 12:45:00',1),(3,'Cobra',5,1965,'img/ShelbyCobra.jpg','2020-07-13 12:28:38','2020-07-13 12:45:00',1),(4,'Mercury',4,1951,'img/FordMercury.jpg','2020-07-13 12:28:38','2020-10-08 12:34:09',3),(5,'Fairlane 500 Skyliner',4,1957,'img/FordFairlane500Skyliner.jpg','2020-07-13 12:28:38',NULL,3),(6,'Gran Torino',4,1975,'img/FordGranTorino.JPG','2020-07-13 12:28:38',NULL,3),(7,'Charger 500',3,1970,'img/DodgeCharger500.jpg','2020-07-13 12:28:38','2020-07-13 13:03:14',3),(10,'DMC-12',2,1981,'img/DeLoreanDMC-12.jpg','2020-07-13 12:28:38',NULL,1),(15,'Corvette',1,1980,'aaa','2020-07-13 12:28:38',NULL,3);
/*!40000 ALTER TABLE `clasicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `marca` (`marca`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,'Chevrolet'),(2,'DeLorean Motor Company'),(3,'Dodge'),(4,'Ford'),(5,'Shelby');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_UN` (`rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'administrador'),(1,'usuario');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
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
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_nombre` (`nombre`),
  KEY `FK_roles` (`id_rol`),
  CONSTRAINT `FK_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1568 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Armando','111','aaa',1),(2,'Basilio','222','bbb',1),(3,'Carmelo','333','ccc',1),(4,'admin','admin','admin',2),(1470,'Magee','111','aaa',1),(1471,'Kermit','111','aaa',1),(1472,'Oleg','111','aaa',1),(1473,'Cassidy','111','aaa',1),(1474,'Aaron','111','aaa',1),(1475,'Hedwig','111','aaa',1),(1476,'Alan','111','aaa',1),(1477,'Emery','111','aaa',1),(1478,'Macy','111','aaa',1),(1479,'Walker','111','aaa',1),(1480,'Xavier','111','aaa',1),(1481,'Dale','111','aaa',1),(1482,'Cole','111','aaa',1),(1483,'Zephr','111','aaa',1),(1484,'Charissa','111','aaa',1),(1485,'Lance','111','aaa',1),(1486,'David','111','aaa',1),(1487,'Ezra','111','aaa',1),(1488,'Quinn','111','aaa',1),(1489,'Cherokee','111','aaa',1),(1490,'Cody','111','aaa',1),(1491,'Katelyn','111','aaa',1),(1492,'Carly','111','aaa',1),(1493,'Asher','111','aaa',1),(1494,'Larissa','111','aaa',1),(1495,'Teegan','111','aaa',1),(1496,'Courtney','111','aaa',1),(1497,'Rhona','111','aaa',1),(1498,'Caldwell','111','aaa',1),(1499,'Wendy','111','aaa',1),(1500,'Rajah','111','aaa',1),(1501,'Evan','111','aaa',1),(1502,'Danielle','111','aaa',1),(1503,'Camilla','111','aaa',1),(1504,'Tana','111','aaa',1),(1505,'Lunea','111','aaa',1),(1506,'Leslie','111','aaa',1),(1507,'Cruz','111','aaa',1),(1508,'Eden','111','aaa',1),(1509,'Byron','111','aaa',1),(1510,'Ryder','111','aaa',1),(1511,'Ronan','111','aaa',1),(1512,'Norman','111','aaa',1),(1513,'Whoopi','111','aaa',1),(1514,'Honorato','111','aaa',1),(1515,'Tallulah','111','aaa',1),(1516,'Gloria','111','aaa',1),(1517,'Ira','111','aaa',1),(1518,'Barry','111','aaa',1),(1519,'Callum','111','aaa',1),(1520,'Zia','111','aaa',1),(1521,'Logan','111','aaa',1),(1522,'Xena','111','aaa',1),(1523,'Paul','111','aaa',1),(1524,'Elmo','111','aaa',1),(1525,'Gil','111','aaa',1),(1526,'Eliana','111','aaa',1),(1527,'Ursula','111','aaa',1),(1528,'Colby','111','aaa',1),(1529,'Basil','111','aaa',1),(1530,'Justine','111','aaa',1),(1531,'Craig','111','aaa',1),(1532,'Candace','111','aaa',1),(1533,'Burton','111','aaa',1),(1534,'Sean','111','aaa',1),(1535,'Urielle','111','aaa',1),(1536,'Tanek','111','aaa',1),(1537,'Guy','111','aaa',1),(1538,'Uta','111','aaa',1),(1539,'Desiree','111','aaa',1),(1540,'Kenneth','111','aaa',1),(1541,'Talon','111','aaa',1),(1542,'Yuli','111','aaa',1),(1543,'Jennifer','111','aaa',1),(1544,'Camille','111','aaa',1),(1545,'Amos','111','aaa',1),(1546,'Fallon','111','aaa',1),(1547,'Micah','111','aaa',1),(1548,'Darius','111','aaa',1),(1549,'Audra','111','aaa',1),(1550,'Jonas','111','aaa',1),(1552,'Daryl','111','aaa',1),(1553,'Xyla','111','aaa',1),(1554,'Caesar','111','aaa',1),(1555,'Carolyn','111','aaa',1),(1556,'Wanda','111','aaa',1),(1557,'Porter','111','aaa',1),(1558,'Jordan','111','aaa',1),(1559,'Cadman','111','aaa',1),(1560,'Hiram','111','aaa',1),(1562,'Oscar','111','aaa',1),(1563,'Levi','111','aaa',1),(1564,'Bell','111','aaa',1),(1565,'Shoshana','111','aaa',1),(1566,'Lani','111','aaa',1),(1567,'ander','111','',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_clasicos_usuario`
--

DROP TABLE IF EXISTS `v_clasicos_usuario`;
/*!50001 DROP VIEW IF EXISTS `v_clasicos_usuario`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_clasicos_usuario` AS SELECT 
 1 AS `id_usuario`,
 1 AS `total`,
 1 AS `aprobados`,
 1 AS `pendientes`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `automoviles_clasicos`
--

USE `automoviles_clasicos`;

--
-- Final view structure for view `v_clasicos_usuario`
--

/*!50001 DROP VIEW IF EXISTS `v_clasicos_usuario`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`debian-sys-maint`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_clasicos_usuario` AS select `clasicos`.`id_usuario` AS `id_usuario`,count(`clasicos`.`id_usuario`) AS `total`,count(`clasicos`.`fecha_validacion`) AS `aprobados`,sum(isnull(`clasicos`.`fecha_validacion`)) AS `pendientes` from `clasicos` group by `clasicos`.`id_usuario` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-12 23:11:25
