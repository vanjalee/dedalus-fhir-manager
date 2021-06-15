CREATE SCHEMA IF NOT EXISTS `dedalus` /*!40100 DEFAULT CHARACTER SET utf8mb4 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dedalus`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: dedalus
-- ------------------------------------------------------
-- Server version 8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `human_name`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `human_name` (
  `id` varchar(255) NOT NULL,
  `created` datetime(6) NOT NULL,
  `family` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `human_name_given`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `human_name_given` (
  `name_id` varchar(255) NOT NULL,
  `given` varchar(255) DEFAULT NULL,
  KEY `FK_human_name_given` (`name_id`),
  CONSTRAINT `FK_human_name_given` FOREIGN KEY (`name_id`) REFERENCES `human_name` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `human_name_prefix`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `human_name_prefix` (
  `name_id` varchar(255) NOT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  KEY `FK_human_name_prefix` (`name_id`),
  CONSTRAINT `FK_human_name_prefix` FOREIGN KEY (`name_id`) REFERENCES `human_name` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `human_name_suffix`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `human_name_suffix` (
  `name_id` varchar(255) NOT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  KEY `FK_human_name_suffix` (`name_id`),
  CONSTRAINT `FK_human_name_suffix` FOREIGN KEY (`name_id`) REFERENCES `human_name` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` varchar(255) NOT NULL,
  `created` datetime(6) NOT NULL,
  `birthday` date DEFAULT NULL,
  `given` varchar(255) DEFAULT NULL,
  `fhir_url` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_patient` (`fhir_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_given`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `patient_given` (
  `patient_id` varchar(255) NOT NULL,
  `given` varchar(255) DEFAULT NULL,
  KEY `FK_patient_given` (`patient_id`),
  CONSTRAINT `FK_patient_given` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_human_name`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `patient_human_name` (
  `Patient_id` varchar(255) NOT NULL,
  `names_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_pgdlhfc1vaguqbqdtjho61syp` (`names_id`),
  KEY `FK_patient_human_name` (`Patient_id`),
  CONSTRAINT `FKj5edpdgrir5em0h42scrp9rlq` FOREIGN KEY (`names_id`) REFERENCES `human_name` (`id`),
  CONSTRAINT `FK_patient_human_name` FOREIGN KEY (`Patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_prefix`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `patient_prefix` (
  `patient_id` varchar(255) NOT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  KEY `FK_patient_prefix` (`patient_id`),
  CONSTRAINT `FK_patient_prefix` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_suffix`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `patient_suffix` (
  `patient_id` varchar(255) NOT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  KEY `FK_patient_suffix` (`patient_id`),
  CONSTRAINT `FK_patient_suffix` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;