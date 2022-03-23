-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: school_demo
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'Math 1','Intro to mathematics'),(2,'Math 2','Advanced matematics'),(3,'English 1','Intro to english language'),(4,'English 2','English literature'),(5,'Spanish 1','Intro to spanish language'),(6,'Spanish 2','Spanish literature'),(7,'Arts','Arts and crafts'),(8,'Sports','Moving, jumping'),(9,'Geography','Planets and whatnot'),(10,'Biology','Nature and animals');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_has_students`
--

DROP TABLE IF EXISTS `class_has_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_has_students` (
  `students_student_id` int NOT NULL,
  `class_class_id` int NOT NULL,
  PRIMARY KEY (`students_student_id`,`class_class_id`),
  KEY `fk_class_has_students_students1_idx` (`students_student_id`),
  KEY `fk_class_has_students_class1_idx` (`class_class_id`),
  CONSTRAINT `fk_class_has_students_class1` FOREIGN KEY (`class_class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_has_students`
--

LOCK TABLES `class_has_students` WRITE;
/*!40000 ALTER TABLE `class_has_students` DISABLE KEYS */;
INSERT INTO `class_has_students` VALUES (1,1),(1,2),(1,3),(1,6),(1,9),(2,1),(2,2),(3,1),(4,1),(4,2),(4,3),(4,4),(4,9),(10,1),(10,2),(10,4);
/*!40000 ALTER TABLE `class_has_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_has_timeslot`
--

DROP TABLE IF EXISTS `class_has_timeslot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_has_timeslot` (
  `class_class_id` int NOT NULL,
  `timeslot_timeslot_id` int NOT NULL,
  PRIMARY KEY (`class_class_id`,`timeslot_timeslot_id`),
  KEY `fk_class_has_timeslot_timeslot1_idx` (`timeslot_timeslot_id`),
  KEY `fk_class_has_timeslot_class1_idx` (`class_class_id`),
  CONSTRAINT `fk_class_has_timeslot_class1` FOREIGN KEY (`class_class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_class_has_timeslot_timeslot1` FOREIGN KEY (`timeslot_timeslot_id`) REFERENCES `timeslot` (`timeslot_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_has_timeslot`
--

LOCK TABLES `class_has_timeslot` WRITE;
/*!40000 ALTER TABLE `class_has_timeslot` DISABLE KEYS */;
INSERT INTO `class_has_timeslot` VALUES (2,0),(7,1),(1,2),(2,5),(3,5),(7,5),(1,8),(7,11),(5,12),(4,16),(4,17),(5,21),(4,26),(4,27),(2,34),(1,36),(6,40);
/*!40000 ALTER TABLE `class_has_timeslot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'John','Doe'),(2,'Mary','Sue'),(3,'Steve','Jobless'),(4,'Mark','Zuckerbot'),(10,'Miha','Gabor');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `teacher_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'John','Wick'),(2,'Bob','Sponge'),(3,'Liam','Nisan'),(4,'Oliver','Ver'),(5,'Chuck','Norton'),(6,'Tom','Ross'),(7,'Steve','Harvy');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers_has_class`
--

DROP TABLE IF EXISTS `teachers_has_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers_has_class` (
  `teachers_teacher_id` int NOT NULL,
  `class_class_id` int NOT NULL,
  PRIMARY KEY (`teachers_teacher_id`,`class_class_id`),
  KEY `fk_teachers_has_class_class1_idx` (`class_class_id`),
  KEY `fk_teachers_has_class_teachers_idx` (`teachers_teacher_id`),
  CONSTRAINT `fk_teachers_has_class_class1` FOREIGN KEY (`class_class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `fk_teachers_has_class_teachers` FOREIGN KEY (`teachers_teacher_id`) REFERENCES `teachers` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers_has_class`
--

LOCK TABLES `teachers_has_class` WRITE;
/*!40000 ALTER TABLE `teachers_has_class` DISABLE KEYS */;
INSERT INTO `teachers_has_class` VALUES (1,1),(1,2),(2,3),(2,4),(3,5),(3,6),(4,7),(5,8),(6,9),(7,10);
/*!40000 ALTER TABLE `teachers_has_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeslot`
--

DROP TABLE IF EXISTS `timeslot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timeslot` (
  `timeslot_id` int NOT NULL,
  `time` int NOT NULL,
  PRIMARY KEY (`timeslot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeslot`
--

LOCK TABLES `timeslot` WRITE;
/*!40000 ALTER TABLE `timeslot` DISABLE KEYS */;
INSERT INTO `timeslot` VALUES (0,0),(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,27),(28,28),(29,29),(30,30),(31,31),(32,32),(33,33),(34,34),(35,35),(36,36),(37,37),(38,38),(39,39),(40,40),(41,41),(42,42),(43,43),(44,44);
/*!40000 ALTER TABLE `timeslot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-23 16:58:44
