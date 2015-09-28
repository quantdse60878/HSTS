CREATE DATABASE  IF NOT EXISTS `hsts` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hsts`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: hsts
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `username` varchar(25) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fullName` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `roleID` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_account_role_idx` (`roleID`),
  CONSTRAINT `fk_account_role` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('anh','123','nhat anh','aaaaaaa',2,1),('khuong','123','man huynh khuong','khuong beo',3,0),('quan','123','tran dang quan','kun sip vang',1,1),('quy','123','ha kim quy','113 de xom',3,1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointmentID` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentDateTime` datetime(1) DEFAULT NULL,
  `appointmentMessage` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `medical_recordID` int(11) DEFAULT NULL,
  PRIMARY KEY (`appointmentID`),
  KEY `fk_appointment_medicalrecord_idx` (`medical_recordID`),
  CONSTRAINT `fk_appointment_medicalrecord` FOREIGN KEY (`medical_recordID`) REFERENCES `medical_record` (`medical_recordID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2015-09-29 00:00:00.0',NULL,1,1),(2,'2015-10-01 00:00:00.0',NULL,1,1),(3,'2015-10-05 00:00:00.0',NULL,1,1);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `iddevice` int(11) NOT NULL AUTO_INCREMENT,
  `brandName` varchar(45) DEFAULT NULL,
  `brandUUID` varchar(45) DEFAULT NULL,
  `numberOfStepUUID` varchar(45) DEFAULT NULL,
  `positionNumberOfStep` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddevice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctorID` int(11) NOT NULL AUTO_INCREMENT,
  `account_Username` varchar(25) NOT NULL,
  `major` varchar(45) NOT NULL,
  PRIMARY KEY (`doctorID`),
  KEY `fk_doctor_account1_idx` (`account_Username`),
  CONSTRAINT `fk_doctor_account1` FOREIGN KEY (`account_Username`) REFERENCES `account` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'quan','dinh duong');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `foodID` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`foodID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Rau, củ',NULL),(2,'Cơm',NULL),(3,'Bánh mì',NULL),(4,'Cá',NULL),(5,'Thịt',NULL),(6,'Sữa',NULL),(7,'Đường',NULL),(8,'Muối',NULL);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_phase`
--

DROP TABLE IF EXISTS `food_phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_phase` (
  `food_phaseID` int(11) NOT NULL AUTO_INCREMENT,
  `phaseID` int(11) DEFAULT NULL,
  `foodID` int(11) DEFAULT NULL,
  PRIMARY KEY (`food_phaseID`),
  KEY `fk_phase_food_idx` (`phaseID`),
  KEY `fk_phase_food_food_idx` (`foodID`),
  CONSTRAINT `fk_phase_food_food` FOREIGN KEY (`foodID`) REFERENCES `food` (`foodID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_phase_food_phase` FOREIGN KEY (`phaseID`) REFERENCES `phase` (`phaseID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_phase`
--

LOCK TABLES `food_phase` WRITE;
/*!40000 ALTER TABLE `food_phase` DISABLE KEYS */;
INSERT INTO `food_phase` VALUES (1,1,1),(2,1,4),(3,1,6),(4,1,5);
/*!40000 ALTER TABLE `food_phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_treatment`
--

DROP TABLE IF EXISTS `food_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_treatment` (
  `food_treatmentID` int(11) NOT NULL AUTO_INCREMENT,
  `foodID` int(11) DEFAULT NULL,
  `quantitative` varchar(45) DEFAULT NULL,
  `mealID` int(11) DEFAULT NULL,
  PRIMARY KEY (`food_treatmentID`),
  KEY `fk_food_treatment_food_idx` (`foodID`),
  KEY `fk_food_treatment_meal_idx` (`mealID`),
  CONSTRAINT `fk_food_treatment_food` FOREIGN KEY (`foodID`) REFERENCES `food` (`foodID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_treatment_meal` FOREIGN KEY (`mealID`) REFERENCES `meal` (`mealID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_treatment`
--

LOCK TABLES `food_treatment` WRITE;
/*!40000 ALTER TABLE `food_treatment` DISABLE KEYS */;
INSERT INTO `food_treatment` VALUES (1,1,'nhiều',1),(2,2,'ít',1),(3,4,'nhiều',1),(4,7,'hạn chế',1);
/*!40000 ALTER TABLE `food_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `mealID` int(11) NOT NULL AUTO_INCREMENT,
  `treatmentID` int(11) DEFAULT NULL,
  `timeStart` time DEFAULT NULL,
  PRIMARY KEY (`mealID`),
  KEY `fk_meal_treatment_idx` (`treatmentID`),
  CONSTRAINT `fk_meal_treatment` FOREIGN KEY (`treatmentID`) REFERENCES `treatment` (`treatmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES (1,1,'07:00:00'),(2,1,'10:00:00'),(3,1,'12:00:00'),(4,1,'15:00:00'),(5,1,'18:00:00'),(6,1,'21:00:00');
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_record` (
  `medical_recordID` int(11) NOT NULL AUTO_INCREMENT,
  `regimenID` int(11) NOT NULL,
  `doctorID` int(11) NOT NULL,
  `patientID` int(11) NOT NULL,
  `startTime` date NOT NULL,
  `status` int(11) DEFAULT NULL,
  `medicalHistory` varchar(100) DEFAULT NULL,
  `clinicalSymptoms` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`medical_recordID`),
  KEY `fk_medical_record_regimen_idx` (`regimenID`),
  KEY `fk_medical_record_Doctor1_idx` (`doctorID`),
  KEY `fk_medical_record_patient1_idx` (`patientID`),
  CONSTRAINT `fk_medical_record_Doctor1` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_record_patient1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_record_regimen` FOREIGN KEY (`regimenID`) REFERENCES `regimen` (`regimenID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT INTO `medical_record` VALUES (1,1,1,1,'2015-09-29',1,'tiểu đường, đau dạ dày','khả năng vận động kém, có dấu hiệu tiểu đường tuýp 2');
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record_data`
--

DROP TABLE IF EXISTS `medical_record_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_record_data` (
  `medical_record_dataID` int(11) NOT NULL AUTO_INCREMENT,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `numberOfStep` int(11) DEFAULT NULL,
  `calories` int(11) DEFAULT NULL,
  `dateCollectData` date DEFAULT NULL,
  `appointmentID` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`medical_record_dataID`),
  KEY `fk_data_appointment_idx` (`appointmentID`),
  CONSTRAINT `fk_data_appointment` FOREIGN KEY (`appointmentID`) REFERENCES `appointment` (`appointmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record_data`
--

LOCK TABLES `medical_record_data` WRITE;
/*!40000 ALTER TABLE `medical_record_data` DISABLE KEYS */;
INSERT INTO `medical_record_data` VALUES (1,167,52,0,0,'2015-09-29',1,1),(2,NULL,52,2120,131,'2015-09-30',1,2),(3,167,51,NULL,NULL,'2015-10-01',2,1);
/*!40000 ALTER TABLE `medical_record_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `medicineID` int(11) NOT NULL AUTO_INCREMENT,
  `MedicineName` varchar(45) DEFAULT NULL,
  `Producer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`medicineID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,'sibutramine','bidiphar'),(2,'orlistat','binh minh'),(3,'tiffy','bidiphar');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_phase`
--

DROP TABLE IF EXISTS `medicine_phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine_phase` (
  `medicine_phaseID` int(11) NOT NULL AUTO_INCREMENT,
  `phaseID` int(11) DEFAULT NULL,
  `medicineID` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicine_phaseID`),
  KEY `fk_medicine_phase_phase_idx` (`phaseID`),
  KEY `fk_medicine_phase_medicine_idx` (`medicineID`),
  CONSTRAINT `fk_medicine_phase_medicine` FOREIGN KEY (`medicineID`) REFERENCES `medicine` (`medicineID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_phase_phase` FOREIGN KEY (`phaseID`) REFERENCES `phase` (`phaseID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_phase`
--

LOCK TABLES `medicine_phase` WRITE;
/*!40000 ALTER TABLE `medicine_phase` DISABLE KEYS */;
INSERT INTO `medicine_phase` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `medicine_phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_time`
--

DROP TABLE IF EXISTS `medicine_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine_time` (
  `medicine_timeID` int(11) NOT NULL AUTO_INCREMENT,
  `timeUse` time DEFAULT NULL,
  `treatmentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicine_timeID`),
  KEY `fk_medicine_time_treatment_idx` (`treatmentID`),
  CONSTRAINT `fk_medicine_time_treatment` FOREIGN KEY (`treatmentID`) REFERENCES `treatment` (`treatmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_time`
--

LOCK TABLES `medicine_time` WRITE;
/*!40000 ALTER TABLE `medicine_time` DISABLE KEYS */;
INSERT INTO `medicine_time` VALUES (1,'07:00:00',1),(2,'12:00:00',1),(3,'18:00:00',1);
/*!40000 ALTER TABLE `medicine_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_treatment`
--

DROP TABLE IF EXISTS `medicine_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine_treatment` (
  `medicine_treatmentID` int(11) NOT NULL AUTO_INCREMENT,
  `medicineID` int(11) DEFAULT NULL,
  `numberOfMedicine` int(11) DEFAULT NULL,
  `medicine_timeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicine_treatmentID`),
  KEY `fk_medicine_treatment_medicine_idx` (`medicineID`),
  KEY `fk_medicine_treatment_medicineTime_idx` (`medicine_timeID`),
  CONSTRAINT `fk_medicine_treatment_medicine` FOREIGN KEY (`medicineID`) REFERENCES `medicine` (`medicineID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_treatment_medicineTime` FOREIGN KEY (`medicine_timeID`) REFERENCES `medicine_time` (`medicine_timeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_treatment`
--

LOCK TABLES `medicine_treatment` WRITE;
/*!40000 ALTER TABLE `medicine_treatment` DISABLE KEYS */;
INSERT INTO `medicine_treatment` VALUES (1,1,1,1),(2,2,2,1),(3,1,1,2),(4,2,1,2),(5,1,1,3),(6,2,2,3);
/*!40000 ALTER TABLE `medicine_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patientID` int(11) NOT NULL AUTO_INCREMENT,
  `account_username` varchar(25) NOT NULL,
  `wristbandID` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`patientID`),
  KEY `fk_patient_account1_idx` (`account_username`),
  CONSTRAINT `fk_patient_account1` FOREIGN KEY (`account_username`) REFERENCES `account` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'quy',NULL,1),(2,'khuong',NULL,1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phase`
--

DROP TABLE IF EXISTS `phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phase` (
  `phaseID` int(11) NOT NULL AUTO_INCREMENT,
  `numberOfDate` varchar(45) DEFAULT NULL,
  `regimenID` int(11) NOT NULL,
  PRIMARY KEY (`phaseID`),
  KEY `fk_phase_regimen1_idx` (`regimenID`),
  CONSTRAINT `fk_phase_regimen1` FOREIGN KEY (`regimenID`) REFERENCES `regimen` (`regimenID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phase`
--

LOCK TABLES `phase` WRITE;
/*!40000 ALTER TABLE `phase` DISABLE KEYS */;
INSERT INTO `phase` VALUES (1,'20',1),(2,'10',1);
/*!40000 ALTER TABLE `phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice` (
  `practiceID` int(11) NOT NULL AUTO_INCREMENT,
  `practiceName` varchar(45) DEFAULT NULL,
  `intensity` int(11) DEFAULT NULL,
  PRIMARY KEY (`practiceID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
INSERT INTO `practice` VALUES (1,'Đi bộ',1),(2,'Chạy chậm',2),(3,'Chạy nhanh',3),(4,'Đạp xe đạp',2);
/*!40000 ALTER TABLE `practice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice_phase`
--

DROP TABLE IF EXISTS `practice_phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice_phase` (
  `practice_phaseID` int(11) NOT NULL AUTO_INCREMENT,
  `phaseID` int(11) DEFAULT NULL,
  `practiceID` int(11) DEFAULT NULL,
  PRIMARY KEY (`practice_phaseID`),
  KEY `fk_practice_phase_phase_idx` (`phaseID`),
  KEY `fk_practice_phase_practice_idx` (`practiceID`),
  CONSTRAINT `fk_practice_phase_phase` FOREIGN KEY (`phaseID`) REFERENCES `phase` (`phaseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_practice_phase_practice` FOREIGN KEY (`practiceID`) REFERENCES `practice` (`practiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice_phase`
--

LOCK TABLES `practice_phase` WRITE;
/*!40000 ALTER TABLE `practice_phase` DISABLE KEYS */;
INSERT INTO `practice_phase` VALUES (1,1,1),(2,1,2),(3,1,4);
/*!40000 ALTER TABLE `practice_phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice_time`
--

DROP TABLE IF EXISTS `practice_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice_time` (
  `practicetimeID` int(11) NOT NULL AUTO_INCREMENT,
  `timeStart` time DEFAULT NULL,
  `treatmentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`practicetimeID`),
  KEY `fk_practice_time_treatment_idx` (`treatmentID`),
  CONSTRAINT `fk_practice_time_treatment` FOREIGN KEY (`treatmentID`) REFERENCES `treatment` (`treatmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice_time`
--

LOCK TABLES `practice_time` WRITE;
/*!40000 ALTER TABLE `practice_time` DISABLE KEYS */;
INSERT INTO `practice_time` VALUES (1,'06:00:00',1),(2,'17:00:00',1),(3,'21:30:00',1);
/*!40000 ALTER TABLE `practice_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice_treatment`
--

DROP TABLE IF EXISTS `practice_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice_treatment` (
  `practice_treatmentID` int(11) NOT NULL AUTO_INCREMENT,
  `practiceID` int(11) DEFAULT NULL,
  `timeDuration` int(11) DEFAULT NULL,
  `practice_timeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`practice_treatmentID`),
  KEY `fk_practice_treatment_practice_idx` (`practiceID`),
  KEY `fk_practice_treatment_practiceTime_idx` (`practice_timeID`),
  CONSTRAINT `fk_practice_treatment_practice` FOREIGN KEY (`practiceID`) REFERENCES `practice` (`practiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_practice_treatment_practiceTime` FOREIGN KEY (`practice_timeID`) REFERENCES `practice_time` (`practicetimeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice_treatment`
--

LOCK TABLES `practice_treatment` WRITE;
/*!40000 ALTER TABLE `practice_treatment` DISABLE KEYS */;
INSERT INTO `practice_treatment` VALUES (1,1,15,1),(2,2,10,1),(3,1,10,2),(4,4,20,2);
/*!40000 ALTER TABLE `practice_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regimen`
--

DROP TABLE IF EXISTS `regimen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regimen` (
  `regimenID` int(11) NOT NULL AUTO_INCREMENT,
  `illnessName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`regimenID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regimen`
--

LOCK TABLES `regimen` WRITE;
/*!40000 ALTER TABLE `regimen` DISABLE KEYS */;
INSERT INTO `regimen` VALUES (1,'fat');
/*!40000 ALTER TABLE `regimen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'doctor','bac si'),(2,'nurse','y ta'),(3,'patient','benh nhan'),(4,'staff','nhan vien'),(5,'admin','ai ti'),(6,'doctor manager','bac si cap cao');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `treatmentID` int(11) NOT NULL AUTO_INCREMENT,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `appointientID` int(11) NOT NULL,
  `advise` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`treatmentID`),
  KEY `fk_treatement_appointment_idx` (`appointientID`),
  CONSTRAINT `fk_treatement_appointment` FOREIGN KEY (`appointientID`) REFERENCES `appointment` (`appointmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (1,'2015-09-29','2015-10-01',1,'Ăn nhiều bữa, không bỏ bữa, ăn đúng giờ, hạn chế đồ ăn đã qua chế biến|Uống thuốc sau khi ăn, uống nhiều nước, không uống thuốc với sữa, không sử dụng các loại thuốc khác để giảm cân như thuốc lợi tiểu'),(2,'2015-10-01','2015-10-05',2,'Vận động tích cực, tập 30 phút mỗi lần, 2 lần sáng và tối.');
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-28 16:31:06
