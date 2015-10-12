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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `fullName` varchar(45) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 In-active\n2 Active\n3 Block',
  `dateOfBirth` date DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_role_idx` (`roleId`),
  CONSTRAINT `fk_account_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'bacsi','123','bacsi@gmail.com','ho bac ten si',2,'1993-08-08',1,NULL),(2,2,'benhnhan','123','benhnhan@gmail.com','ho benh ten nhan',2,'1993-09-09',1,NULL),(1000,1,'system','system','system@gmail.com','I am engine',2,'1993-12-02',1,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `medicalRecordId` int(11) NOT NULL,
  `appointmentDateTime` datetime DEFAULT NULL,
  `appointmentMessage` varchar(200) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT 'status = 1 la cuoc hen da qua y ta va chua duoc gap bac si\nstatus = 2 la cuoc hen da duoc gap bac si\nstatus = 3 la cuoc hen chua qua y ta',
  `nextAppointment` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_appointment_medicalrecord_idx` (`medicalRecordId`),
  KEY `fk_appointment_nextappointment_idx` (`nextAppointment`),
  CONSTRAINT `fk_appointment_medicalrecord` FOREIGN KEY (`medicalRecordId`) REFERENCES `medicalrecord` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_nextappointment` FOREIGN KEY (`nextAppointment`) REFERENCES `appointment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,1,'2015-10-01 00:00:00',NULL,1,NULL,0,0),(2,1,'2015-10-07 00:00:00',NULL,2,1,160,85);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandName` varchar(45) DEFAULT NULL,
  `brandUuid` varchar(45) DEFAULT NULL,
  `numberOfStepUuid` varchar(45) DEFAULT NULL,
  `positionNumberOfStep` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
-- Table structure for table `devicepatient`
--

DROP TABLE IF EXISTS `devicepatient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devicepatient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` int(11) DEFAULT NULL,
  `patientId` int(11) DEFAULT NULL,
  `wristbandAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pd_patient_idx` (`patientId`),
  KEY `fk_pd_device_idx` (`deviceId`),
  CONSTRAINT `fk_pd_device` FOREIGN KEY (`deviceId`) REFERENCES `device` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pd_patient` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devicepatient`
--

LOCK TABLES `devicepatient` WRITE;
/*!40000 ALTER TABLE `devicepatient` DISABLE KEYS */;
/*!40000 ALTER TABLE `devicepatient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL,
  `major` varchar(45) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_doctor_account1_idx` (`accountId`),
  CONSTRAINT `fk_doctor_account1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,1,'dinh duong','101');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'cơm'),(2,'cá'),(3,'thịt'),(4,'rau'),(5,'củ'),(6,'hải sản');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodphase`
--

DROP TABLE IF EXISTS `foodphase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodphase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phaseId` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phase_food_idx` (`phaseId`),
  KEY `fk_phase_food_food_idx` (`foodId`),
  CONSTRAINT `fk_phase_food_food` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_phase_food_phase` FOREIGN KEY (`phaseId`) REFERENCES `phase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodphase`
--

LOCK TABLES `foodphase` WRITE;
/*!40000 ALTER TABLE `foodphase` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodphase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodtreatment`
--

DROP TABLE IF EXISTS `foodtreatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodtreatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodId` int(11) NOT NULL,
  `quantitative` varchar(45) DEFAULT NULL,
  `treatmentId` int(11) DEFAULT NULL,
  `numberOfTime` int(11) DEFAULT NULL,
  `advice` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_food_treatment_food_idx` (`foodId`),
  KEY `fk_ft_t_idx` (`treatmentId`),
  CONSTRAINT `fk_food_treatment_food` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ft_t` FOREIGN KEY (`treatmentId`) REFERENCES `treatment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodtreatment`
--

LOCK TABLES `foodtreatment` WRITE;
/*!40000 ALTER TABLE `foodtreatment` DISABLE KEYS */;
INSERT INTO `foodtreatment` VALUES (1,1,'1 chén',1,5,NULL),(2,2,'ít',1,5,NULL),(3,3,'hạn chế',1,3,NULL),(4,4,'nhiều',1,5,NULL),(5,5,'nhiều',1,3,NULL);
/*!40000 ALTER TABLE `foodtreatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `illness`
--

DROP TABLE IF EXISTS `illness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `illness` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `illness`
--

LOCK TABLES `illness` WRITE;
/*!40000 ALTER TABLE `illness` DISABLE KEYS */;
INSERT INTO `illness` VALUES (1,'beo phi do 1','beo phi'),(2,'beo phi do 2','beo phi');
/*!40000 ALTER TABLE `illness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalrecord`
--

DROP TABLE IF EXISTS `medicalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicalrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `illnessId` int(11) DEFAULT NULL,
  `doctorId` int(11) NOT NULL,
  `patientId` int(11) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT 'status = 1 cho kham\nstatus = 2 khong co benh\nstatus = 3 di kham co benh va dang dieu tri\nstatus = 4 benh da chua tri xong',
  `clinicalSymptoms` varchar(200) DEFAULT NULL,
  `medicalHistory` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medical_record_Doctor1_idx` (`doctorId`),
  KEY `fk_medical_record_patient1_idx` (`patientId`),
  KEY `FK_Illness_MedicalRecord_idx` (`illnessId`),
  CONSTRAINT `FK_Illness_MedicalRecord` FOREIGN KEY (`illnessId`) REFERENCES `illness` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_record_Doctor1` FOREIGN KEY (`doctorId`) REFERENCES `doctor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_record_patient1` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecord`
--

LOCK TABLES `medicalrecord` WRITE;
/*!40000 ALTER TABLE `medicalrecord` DISABLE KEYS */;
INSERT INTO `medicalrecord` VALUES (1,1,1,1,'2015-10-01',NULL,3,'di khong duoc','mo trong mau');
/*!40000 ALTER TABLE `medicalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalrecorddata`
--

DROP TABLE IF EXISTS `medicalrecorddata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicalrecorddata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentId` int(11) NOT NULL,
  `numberOfStep` int(11) DEFAULT NULL,
  `calories` int(11) DEFAULT NULL,
  `distance` float DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT 'type = 1 du lieu kham benh cua benh nhan\ntype = 2 du lieu thu thap duoc cua benh nhan chua duoc tinh toan\ntype = 3 du lieu thu thap duoc cua benh nhan da duoc tinh toan\n',
  `ratioCompletePractice` int(11) DEFAULT NULL,
  `collectedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_data_appointment_idx` (`appointmentId`),
  CONSTRAINT `fk_data_appointment` FOREIGN KEY (`appointmentId`) REFERENCES `appointment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecorddata`
--

LOCK TABLES `medicalrecorddata` WRITE;
/*!40000 ALTER TABLE `medicalrecorddata` DISABLE KEYS */;
INSERT INTO `medicalrecorddata` VALUES (1,2,3000,740,4.6368,3,0,'2015-10-01 00:00:00'),(2,2,8000,846,5.2992,3,1,'2015-10-02 00:00:00'),(3,2,14000,1270,7.9488,3,1,'2015-10-03 00:00:00');
/*!40000 ALTER TABLE `medicalrecorddata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalrecords`
--

DROP TABLE IF EXISTS `medicalrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicalrecords` (
  `Patient` int(11) NOT NULL,
  `medicalRecords` int(11) NOT NULL,
  UNIQUE KEY `UK_sixi9w48om2ewoj5foss8m8d1` (`medicalRecords`),
  KEY `FK_k8q8ggqe920p4xpe6q7ge74li` (`Patient`),
  CONSTRAINT `FK_k8q8ggqe920p4xpe6q7ge74li` FOREIGN KEY (`Patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK_sixi9w48om2ewoj5foss8m8d1` FOREIGN KEY (`medicalRecords`) REFERENCES `medicalrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecords`
--

LOCK TABLES `medicalrecords` WRITE;
/*!40000 ALTER TABLE `medicalrecords` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalrecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `medicineName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,'A'),(2,'B'),(3,'C');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicinephase`
--

DROP TABLE IF EXISTS `medicinephase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicinephase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phaseId` int(11) NOT NULL,
  `medicineId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medicine_phase_phase_idx` (`phaseId`),
  KEY `fk_medicine_phase_medicine_idx` (`medicineId`),
  CONSTRAINT `fk_medicine_phase_medicine` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_phase_phase` FOREIGN KEY (`phaseId`) REFERENCES `phase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicinephase`
--

LOCK TABLES `medicinephase` WRITE;
/*!40000 ALTER TABLE `medicinephase` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicinephase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicinetreatment`
--

DROP TABLE IF EXISTS `medicinetreatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicinetreatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `medicineId` int(11) NOT NULL,
  `quantitative` varchar(40) DEFAULT NULL,
  `treatmentId` int(11) DEFAULT NULL,
  `advice` varchar(120) DEFAULT NULL,
  `numberOfTime` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medicine_treatment_medicine_idx` (`medicineId`),
  KEY `fk_mt_t_idx` (`treatmentId`),
  CONSTRAINT `fk_medicine_treatment_medicine` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mt_t` FOREIGN KEY (`treatmentId`) REFERENCES `treatment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicinetreatment`
--

LOCK TABLES `medicinetreatment` WRITE;
/*!40000 ALTER TABLE `medicinetreatment` DISABLE KEYS */;
INSERT INTO `medicinetreatment` VALUES (1,1,'1 viên',1,'ăn trước khi uống',3),(2,2,'1 gói',1,'uống trước khi ăn',2),(3,3,'1 viên',1,'ăn trước khi uống',3);
/*!40000 ALTER TABLE `medicinetreatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notify`
--

DROP TABLE IF EXISTS `notify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` int(11) NOT NULL,
  `receiverId` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT 'type = 1 notify cua benh nhan den bac si\ntype = 2 notify cua bac si den benh nhan - new prescription\ntype = 3 notify cua bac si den benh nhan - new appointment\ntype = 4 notify cua y ta den bac si\ntype = 5 notify benh nhan chua hoan thanh bai tap cua minh\ntype = 6 notify benh nhan luyen tap qua muc can thiet\ntype = 5 notify benh nhan khong hoan thanh duoc so calories',
  `status` tinyint(4) DEFAULT NULL COMMENT 'status = 1 chua nhan\nstatus = 2 da nhan',
  PRIMARY KEY (`id`),
  KEY `fk_notify_sender_idx` (`senderId`),
  KEY `fk_notify_receiver_idx` (`receiverId`),
  CONSTRAINT `fk_notify_receiver` FOREIGN KEY (`receiverId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notify_sender` FOREIGN KEY (`senderId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notify`
--

LOCK TABLES `notify` WRITE;
/*!40000 ALTER TABLE `notify` DISABLE KEYS */;
INSERT INTO `notify` VALUES (1,1,2,2,1);
/*!40000 ALTER TABLE `notify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_patient_account1_idx` (`accountId`),
  CONSTRAINT `fk_patient_account1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,2);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phase`
--

DROP TABLE IF EXISTS `phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regimenId` int(11) NOT NULL,
  `fromdate` int(11) DEFAULT NULL,
  `todate` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `numberOfDate` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phase_regimen1_idx` (`regimenId`),
  CONSTRAINT `fk_phase_regimen1` FOREIGN KEY (`regimenId`) REFERENCES `regimen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phase`
--

LOCK TABLES `phase` WRITE;
/*!40000 ALTER TABLE `phase` DISABLE KEYS */;
/*!40000 ALTER TABLE `phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `practiceName` varchar(45) DEFAULT NULL,
  `intensity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
INSERT INTO `practice` VALUES (1,'đi bộ',1),(2,'chạy bộ',3),(3,'đạp xe đạp',2);
/*!40000 ALTER TABLE `practice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practicephase`
--

DROP TABLE IF EXISTS `practicephase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practicephase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phaseId` int(11) NOT NULL,
  `practiceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_practice_phase_phase_idx` (`phaseId`),
  KEY `fk_practice_phase_practice_idx` (`practiceId`),
  CONSTRAINT `fk_practice_phase_phase` FOREIGN KEY (`phaseId`) REFERENCES `phase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_practice_phase_practice` FOREIGN KEY (`practiceId`) REFERENCES `practice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicephase`
--

LOCK TABLES `practicephase` WRITE;
/*!40000 ALTER TABLE `practicephase` DISABLE KEYS */;
/*!40000 ALTER TABLE `practicephase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practicetreatment`
--

DROP TABLE IF EXISTS `practicetreatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practicetreatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `practiceId` int(11) DEFAULT NULL,
  `timeDuration` int(11) DEFAULT NULL,
  `treatmentId` int(11) DEFAULT NULL,
  `numberOfTime` int(11) DEFAULT NULL,
  `advice` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_practice_treatment_practice_idx` (`practiceId`),
  KEY `fk_pt_t_idx` (`treatmentId`),
  CONSTRAINT `fk_practice_treatment_practice` FOREIGN KEY (`practiceId`) REFERENCES `practice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pt_t` FOREIGN KEY (`treatmentId`) REFERENCES `treatment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicetreatment`
--

LOCK TABLES `practicetreatment` WRITE;
/*!40000 ALTER TABLE `practicetreatment` DISABLE KEYS */;
INSERT INTO `practicetreatment` VALUES (1,1,20,1,2,NULL),(2,2,10,1,2,NULL),(3,3,10,1,2,NULL);
/*!40000 ALTER TABLE `practicetreatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regimen`
--

DROP TABLE IF EXISTS `regimen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regimen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `illnessId` int(11) NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Illness_Regimen_idx` (`illnessId`),
  CONSTRAINT `FK_Illness_Regimen` FOREIGN KEY (`illnessId`) REFERENCES `illness` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regimen`
--

LOCK TABLES `regimen` WRITE;
/*!40000 ALTER TABLE `regimen` DISABLE KEYS */;
/*!40000 ALTER TABLE `regimen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'bac si',NULL),(2,'benh nhan',NULL),(3,'y ta',NULL),(4,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_648mg4ang0s6mpqshbvchir8a` (`accountId`),
  CONSTRAINT `FK_648mg4ang0s6mpqshbvchir8a` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentId` int(11) NOT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `adviseFood` varchar(400) DEFAULT NULL,
  `adviseMedicine` varchar(400) DEFAULT NULL,
  `advisePractice` varchar(400) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT 'status = 1 treatment dang su dung\nstatus = 0 treatment ko con duoc su dung',
  `caloriesBurnEveryday` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_treatement_appointment_idx` (`appointmentId`),
  CONSTRAINT `fk_treatement_appointment` FOREIGN KEY (`appointmentId`) REFERENCES `appointment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (1,2,'2015-10-01','2015-10-07','Ăn chậm nhai kỹ','Uống thuốc điều độ','Tập luyện đều đặng',1,800);
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

-- Dump completed on 2015-10-12  9:20:05
