CREATE DATABASE  IF NOT EXISTS `bd_estudiante` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_estudiante`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_estudiante
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estudiante` (
  `matricula` varchar(9) NOT NULL,
  `paterno` varchar(25) NOT NULL,
  `materno` varchar(25) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES ('S16054832','MESSI','CUCCITTINI','LIONEL ANDRES'),('S16489047','ANDRADE','GARCIA','RAFAEL'),('S17012953','ANDRADE','MENDEZ','RAFAEL'),('S17012954','ANDRADE','MENDEZ','CHUCHO'),('S17012955','MENDEZ','GUZMAN','ILIANA'),('S17012978','VAZQUEZ','POZOS','DIEGO'),('S17012997','GUZMAN','RAMOS','FILIBERTA');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencia_educativa`
--

DROP TABLE IF EXISTS `experiencia_educativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `experiencia_educativa` (
  `idExperienciaEducativa` int(11) NOT NULL AUTO_INCREMENT,
  `nrc` varchar(6) NOT NULL,
  `creditos` int(11) DEFAULT NULL,
  `horasTeoricas` int(11) DEFAULT NULL,
  `horasPracticas` int(11) DEFAULT NULL,
  `nombreEE` varchar(40) NOT NULL,
  PRIMARY KEY (`idExperienciaEducativa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencia_educativa`
--

LOCK TABLES `experiencia_educativa` WRITE;
/*!40000 ALTER TABLE `experiencia_educativa` DISABLE KEYS */;
INSERT INTO `experiencia_educativa` VALUES (4,'124',10,200,150,'Redes');
/*!40000 ALTER TABLE `experiencia_educativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarioee`
--

DROP TABLE IF EXISTS `horarioee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horarioee` (
  `idHorarioEE` int(11) NOT NULL AUTO_INCREMENT,
  `salon` varchar(10) DEFAULT NULL,
  `horaInicio` varchar(10) DEFAULT NULL,
  `horaFin` varchar(10) DEFAULT NULL,
  `dia` varchar(10) DEFAULT NULL,
  `idExperienciaEducativa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idHorarioEE`),
  KEY `idExperienciaEducativa` (`idExperienciaEducativa`),
  CONSTRAINT `horarioee_ibfk_1` FOREIGN KEY (`idExperienciaEducativa`) REFERENCES `experiencia_educativa` (`idexperienciaeducativa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarioee`
--

LOCK TABLES `horarioee` WRITE;
/*!40000 ALTER TABLE `horarioee` DISABLE KEYS */;
INSERT INTO `horarioee` VALUES (2,'CC3','7:00','9:00','LUNES',4),(5,'CC2','9:00','11:00','MARTES',4),(6,'CC3','7:00','9:00','MIERCOLES',4);
/*!40000 ALTER TABLE `horarioee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_estudiante'
--

--
-- Dumping routines for database 'bd_estudiante'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18 23:22:17
