-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: server
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail` (
  `num` int unsigned NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `userid` int unsigned NOT NULL,
  `io` int NOT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES (2,'2024-04-07 10:33:43',1,500000,'本金'),(3,'2024-04-07 11:28:02',1,-50000,NULL),(4,'2024-04-07 20:15:40',1,5000,'错误已改'),(5,'2024-04-07 20:22:41',1,20000,NULL),(7,'2024-04-07 20:32:13',1,-20000,NULL),(8,'2024-04-07 20:33:00',1,-500,NULL),(9,'2024-04-07 20:33:24',1,-30,NULL),(10,'2024-04-08 19:52:10',1,-1000,NULL),(11,'2024-04-08 20:03:00',1,-10,NULL),(12,'2024-04-08 21:35:54',1,-20,NULL),(13,'2024-04-11 14:24:33',1,-10,NULL),(14,'2024-04-11 15:16:13',1,51,'错误已改'),(15,'2024-04-11 15:16:19',1,515119,'错误已改'),(16,'2024-04-11 15:16:38',1,91155,'错误已改'),(17,'2024-04-11 15:33:11',1,-666,NULL),(18,'2024-04-25 15:37:45',1,-100,NULL),(19,'2024-04-25 17:15:39',1,-213,NULL),(20,'2024-05-12 20:58:59',1,-1234,NULL),(21,'2024-05-12 21:08:51',1,-100000,NULL),(22,'2024-05-12 21:09:00',1,-20065,NULL),(23,'2024-05-18 20:22:01',1,-1000,NULL);
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_user_sum` AFTER INSERT ON `detail` FOR EACH ROW BEGIN
    DECLARE total_io INT;
    
    -- 计算新的总收入和总支出
    SELECT SUM(io) INTO total_io FROM detail WHERE userid = NEW.userid;

    -- 更新user表中对应uid的sum值
    UPDATE user SET sum = total_io WHERE userid = NEW.userid;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-18 21:06:01
