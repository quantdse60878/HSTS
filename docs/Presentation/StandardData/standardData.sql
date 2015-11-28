-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: hsts
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
  `dateOfBirth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `gender` tinyint(4) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o7tsx5y3lbs54tllg8dyq7p9h` (`roleId`),
  CONSTRAINT `FK_o7tsx5y3lbs54tllg8dyq7p9h` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'1993-12-02 00:00:00','doctor@gmail.com','Ngô Tấn Sĩ',2,'4297f44b13955235245b2497399d7a93',2,NULL,'doctor',1),(2,'1993-12-02 00:00:00','nutrition@gmail.com','Huỳnh Văn Dưỡng',2,'4297f44b13955235245b2497399d7a93',2,NULL,'nutrition',7),(3,'1993-12-02 00:00:00','nurse@gmail.com','Nguyễn Thị Ý',2,'4297f44b13955235245b2497399d7a93',2,NULL,'nurse',3),(4,'1993-12-02 00:00:00','staff@gmail.com','Nguyễn Tố Viên',2,'4297f44b13955235245b2497399d7a93',2,NULL,'staff',5),(6,'2015-11-01 00:00:00','abc@gmail.com','Hồ Văn Bác',1,'4297f44b13955235245b2497399d7a93',2,'2015-11-01 21:28:49','doctor1',1),(16,'2015-11-01 00:00:00','ffffff@gmail.com','Nguyễn Thanh Dinh',1,'4297f44b13955235245b2497399d7a93',2,'2015-11-11 00:00:00','nutrition1',7),(17,'2015-11-01 00:00:00','doctormanager@gmail.com','Huỳnh Văn Sao',1,'4297f44b13955235245b2497399d7a93',1,'2015-11-11 00:00:00','manager',4),(18,'2015-11-01 00:00:00','admin@gmail.com','Hồ Văn Trị',1,'4297f44b13955235245b2497399d7a93',2,'2015-11-11 00:00:00','admin',6),(64,'1995-01-01 00:00:00','quyha212@gmail.com','aaaa',1,'f4cfeb74369fccf3796f772a3548d45c',1,'2015-11-28 00:39:21','aaaa',2);
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
  `appointmentDateTime` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `medicalRecordId` int(11) NOT NULL,
  `nextAppointment` int(11) DEFAULT NULL,
  `appointmentMessage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6ciexxiu11immx0bdvn0uujkp` (`medicalRecordId`),
  KEY `FK_a0ei95cok5pnaru5cueeyc4np` (`nextAppointment`),
  CONSTRAINT `FK_6ciexxiu11immx0bdvn0uujkp` FOREIGN KEY (`medicalRecordId`) REFERENCES `medicalrecord` (`id`),
  CONSTRAINT `FK_a0ei95cok5pnaru5cueeyc4np` FOREIGN KEY (`nextAppointment`) REFERENCES `appointment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
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
  `brandName` varchar(255) DEFAULT NULL,
  `brandUuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'77,97,110,117,102,97,99,116,117,114,101,114,32,78,97,109,101,0,0,0','1'),(2,'113,113,113,113,113,113','sds');
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
  `wristbandAddress` varchar(255) DEFAULT NULL,
  `deviceId` int(11) NOT NULL,
  `patientId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eqql5002vk0t9d1ks24ckbqpy` (`deviceId`),
  KEY `FK_mohdu7n4ag7jata7b636jqnub` (`patientId`),
  CONSTRAINT `FK_eqql5002vk0t9d1ks24ckbqpy` FOREIGN KEY (`deviceId`) REFERENCES `device` (`id`),
  CONSTRAINT `FK_mohdu7n4ag7jata7b636jqnub` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`)
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
  `department` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_140irhatdv373uvx6gbqtau72` (`accountId`),
  CONSTRAINT `FK_140irhatdv373uvx6gbqtau72` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'điều trị','dinh dưỡng',1),(2,'khảo sát','dinh dưỡng',2),(3,'khảo sát','dinh dưỡng',16),(4,'điều trị','dinh dưỡng',6);
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
  `foodName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Cơm'),(2,'Cá thu'),(3,'Thịt bò'),(4,'Thịt gà'),(5,'Trái cây'),(6,'Bún bò huế'),(7,'Cá ngừ'),(8,'Bắp cải'),(9,'Rau ngót'),(10,'Cơm tấm sườn'),(11,'Bún thịt nướng'),(14,'Nước Ngọt'),(15,'Phở bò'),(16,'Hủ tíu thịt heo'),(17,'Bánh mì'),(18,'Hột vịt lộn'),(19,'Trứng gà'),(20,'Cà phê đen'),(21,'Sữa đậu nành'),(22,'Chanh'),(23,'Bia'),(24,'Bột mì'),(25,'Sữa tươi'),(26,'Bột gạo'),(27,'Gạo nếp'),(28,'Gạo thường'),(29,'3333');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodingredient`
--

DROP TABLE IF EXISTS `foodingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodingredient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `animalFat` float NOT NULL,
  `animalProtein` float NOT NULL,
  `breakTimeAfternoon` float NOT NULL,
  `breakTimeMorning` float NOT NULL,
  `breakfast` float NOT NULL,
  `calcium` float NOT NULL,
  `dinner` float NOT NULL,
  `eatLateAtNight` float NOT NULL,
  `fat` float NOT NULL,
  `fiber` float NOT NULL,
  `iron` float NOT NULL,
  `lunch` float NOT NULL,
  `protein` float NOT NULL,
  `sodium` float NOT NULL,
  `starch` float NOT NULL,
  `vitaminB1` float NOT NULL,
  `vitaminB2` float NOT NULL,
  `vitaminC` float NOT NULL,
  `vitaminPP` float NOT NULL,
  `zinc` float NOT NULL,
  `appointmentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m0py0h5oy55wy81kh2xe2ir9p` (`appointmentId`),
  CONSTRAINT `FK_m0py0h5oy55wy81kh2xe2ir9p` FOREIGN KEY (`appointmentId`) REFERENCES `appointment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodingredient`
--

LOCK TABLES `foodingredient` WRITE;
/*!40000 ALTER TABLE `foodingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodphase`
--

DROP TABLE IF EXISTS `foodphase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodphase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodId` int(11) NOT NULL,
  `phaseId` int(11) NOT NULL,
  `advice` varchar(255) DEFAULT NULL,
  `numberOfTime` int(11) NOT NULL,
  `unitName` varchar(255) DEFAULT NULL,
  `quantitative` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e6ken19xl2b27beofr45l6u4t` (`foodId`),
  KEY `FK_3ncqhve8d2sej1vpakaxui3n5` (`phaseId`),
  CONSTRAINT `FK_3ncqhve8d2sej1vpakaxui3n5` FOREIGN KEY (`phaseId`) REFERENCES `phase` (`id`),
  CONSTRAINT `FK_e6ken19xl2b27beofr45l6u4t` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodphase`
--

LOCK TABLES `foodphase` WRITE;
/*!40000 ALTER TABLE `foodphase` DISABLE KEYS */;
INSERT INTO `foodphase` VALUES (1,1,1,'ăn ít',5,'bát',2),(2,2,1,'ăn nhiều',3,'gam',3),(3,3,1,'hạn chế',3,'gam',1),(4,4,1,'càng nhiều càng tốt',5,'gam',4),(5,5,1,'ăn trái cây có nhiều nước',3,'gam',3),(6,1,2,'ăn ít',5,'bát',2),(7,2,2,'ăn nhiều',3,'gam',3),(8,3,2,'hạn chế',3,'gam',1),(9,4,2,'càng nhiều càng tốt',5,'gam',4),(10,5,2,'ăn trái cây có nhiều nước',3,'gam',3),(11,1,3,'ăn ít',5,'bát',2),(12,2,3,'ăn nhiều',3,'gam',3),(13,3,3,'hạn chế',3,'gam',1),(14,4,3,'càng nhiều càng tốt',5,'gam',4),(15,5,3,'ăn trái cây có nhiều nước',3,'gam',3);
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
  `advice` varchar(255) DEFAULT NULL,
  `numberOfTime` int(11) NOT NULL,
  `quantitative` varchar(255) DEFAULT NULL,
  `foodId` int(11) NOT NULL,
  `treatmentId` int(11) NOT NULL,
  `unitName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4sil2ru0amkukqyhh1fvh5eio` (`foodId`),
  KEY `FK_al5pg659nf9t7vgwarbjfjx2h` (`treatmentId`),
  CONSTRAINT `FK_4sil2ru0amkukqyhh1fvh5eio` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`),
  CONSTRAINT `FK_al5pg659nf9t7vgwarbjfjx2h` FOREIGN KEY (`treatmentId`) REFERENCES `treatment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodtreatment`
--

LOCK TABLES `foodtreatment` WRITE;
/*!40000 ALTER TABLE `foodtreatment` DISABLE KEYS */;
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
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `illness`
--

LOCK TABLES `illness` WRITE;
/*!40000 ALTER TABLE `illness` DISABLE KEYS */;
INSERT INTO `illness` VALUES (1,'Fat 1','Béo phì cấp độ 1'),(2,'Fat 2','Béo phì cấp độ 2'),(3,'Fat 3','Béo phì cấp độ 3');
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
  `endTime` datetime DEFAULT NULL,
  `medicalHistory` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `clinicalSymptoms` varchar(255) DEFAULT NULL,
  `doctorId` int(11) NOT NULL,
  `illnessId` int(11) DEFAULT NULL,
  `patientId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9w4j4nqi98up5qqj5my6q005` (`doctorId`),
  KEY `FK_jxea6j6fm9viv3ojnlfed8hm5` (`illnessId`),
  KEY `FK_krk6v5uihsmaop15xcq454mv4` (`patientId`),
  CONSTRAINT `FK_9w4j4nqi98up5qqj5my6q005` FOREIGN KEY (`doctorId`) REFERENCES `doctor` (`id`),
  CONSTRAINT `FK_jxea6j6fm9viv3ojnlfed8hm5` FOREIGN KEY (`illnessId`) REFERENCES `illness` (`id`),
  CONSTRAINT `FK_krk6v5uihsmaop15xcq454mv4` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecord`
--

LOCK TABLES `medicalrecord` WRITE;
/*!40000 ALTER TABLE `medicalrecord` DISABLE KEYS */;
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
  `collectedDate` datetime DEFAULT NULL,
  `ratioCompletePractice` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `appointmentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jy73vtu9l6sqddrsswfla78ag` (`appointmentId`),
  CONSTRAINT `FK_jy73vtu9l6sqddrsswfla78ag` FOREIGN KEY (`appointmentId`) REFERENCES `appointment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecorddata`
--

LOCK TABLES `medicalrecorddata` WRITE;
/*!40000 ALTER TABLE `medicalrecorddata` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalrecorddata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `medicineName` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,'Sibutramine','viên'),(2,'Orlistat','viên'),(3,'Bluepine','Viên'),(4,'Rosufar','viên'),(18,'','');
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
  `quantitative` varchar(255) DEFAULT NULL,
  `medicineId` int(11) NOT NULL,
  `phaseId` int(11) NOT NULL,
  `advice` varchar(255) DEFAULT NULL,
  `numberOfTime` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rc6e0rho1va0kq09qn3l337eb` (`medicineId`),
  KEY `FK_r6kh7vtgpls9d85ew3bfj771r` (`phaseId`),
  CONSTRAINT `FK_r6kh7vtgpls9d85ew3bfj771r` FOREIGN KEY (`phaseId`) REFERENCES `phase` (`id`),
  CONSTRAINT `FK_rc6e0rho1va0kq09qn3l337eb` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicinephase`
--

LOCK TABLES `medicinephase` WRITE;
/*!40000 ALTER TABLE `medicinephase` DISABLE KEYS */;
INSERT INTO `medicinephase` VALUES (1,'1',1,1,'uống trước khi ăn',2),(2,'1',2,1,'uống sau khi ăn',2),(3,'1',1,2,'uống trước khi ăn',3),(4,'1',2,2,'uống sau khi ăn',3),(5,'1',3,2,'uống sau khi ăn',2),(6,'1',1,3,'uống trước khi ăn',3),(7,'1',2,3,'uống sau khi ăn',3),(8,'1',3,3,'uống sau khi ăn',2),(9,'1',4,3,'uống trước khi tập',1);
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
  `advice` varchar(255) DEFAULT NULL,
  `numberOfTime` int(11) NOT NULL,
  `quantitative` int(11) NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `medicineId` int(11) NOT NULL,
  `treatmentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4pdwmgu4nf488rf85bl10yswl` (`medicineId`),
  KEY `FK_ht7qtv3ge0q0secdrjro0vhkx` (`treatmentId`),
  CONSTRAINT `FK_4pdwmgu4nf488rf85bl10yswl` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`),
  CONSTRAINT `FK_ht7qtv3ge0q0secdrjro0vhkx` FOREIGN KEY (`treatmentId`) REFERENCES `treatment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicinetreatment`
--

LOCK TABLES `medicinetreatment` WRITE;
/*!40000 ALTER TABLE `medicinetreatment` DISABLE KEYS */;
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
  `message` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `receiverId` int(11) NOT NULL,
  `senderId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g4rk8qdkj27gixlde7q0pjxyc` (`receiverId`),
  KEY `FK_pn31u6r1j8yaqr7w0h5pa9ddy` (`senderId`),
  CONSTRAINT `FK_g4rk8qdkj27gixlde7q0pjxyc` FOREIGN KEY (`receiverId`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_pn31u6r1j8yaqr7w0h5pa9ddy` FOREIGN KEY (`senderId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notify`
--

LOCK TABLES `notify` WRITE;
/*!40000 ALTER TABLE `notify` DISABLE KEYS */;
INSERT INTO `notify` VALUES (267,'57',2,4,1,3),(268,'57',1,2,64,1),(269,'57',1,4,1,3);
/*!40000 ALTER TABLE `notify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parammeasurement`
--

DROP TABLE IF EXISTS `parammeasurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parammeasurement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `measurementMaxRange` int(11) NOT NULL,
  `measurementMinRange` int(11) NOT NULL,
  `measurementName` varchar(255) DEFAULT NULL,
  `measurementType` varchar(255) DEFAULT NULL,
  `positionHaveValue` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `deviceId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cxv7cx7x71vy0hor03ka4sfrl` (`deviceId`),
  CONSTRAINT `FK_cxv7cx7x71vy0hor03ka4sfrl` FOREIGN KEY (`deviceId`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parammeasurement`
--

LOCK TABLES `parammeasurement` WRITE;
/*!40000 ALTER TABLE `parammeasurement` DISABLE KEYS */;
INSERT INTO `parammeasurement` VALUES (1,-1,-1,'NumberOfStep','int',3,1,'00002a25-0000-1000-8000-00805f9b34fb',1),(2,-1,-1,'Calories','String',0,0,'0',1),(3,-1,-1,'Distance','String',0,0,'0',1),(4,-1,-1,'NumberOfStep','1',3,1,'00002a25',2);
/*!40000 ALTER TABLE `parammeasurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4hlbhpbudsvmawf3ib7i99ecf` (`accountId`),
  CONSTRAINT `FK_4hlbhpbudsvmawf3ib7i99ecf` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (57,'40400570',64);
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
  `fromDate` int(11) NOT NULL,
  `toDate` int(11) NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `regimenId` int(11) NOT NULL,
  `numberOfDay` int(11) NOT NULL,
  `phaseOrder` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e1g1c5hi501376t7v8jkltemn` (`regimenId`),
  CONSTRAINT `FK_e1g1c5hi501376t7v8jkltemn` FOREIGN KEY (`regimenId`) REFERENCES `regimen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phase`
--

LOCK TABLES `phase` WRITE;
/*!40000 ALTER TABLE `phase` DISABLE KEYS */;
INSERT INTO `phase` VALUES (1,0,14,NULL,1,14,1),(2,0,14,NULL,2,14,1),(3,0,14,NULL,3,14,1);
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
  `intensity` int(11) NOT NULL,
  `practiceName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
INSERT INTO `practice` VALUES (1,1,'Đi bộ'),(2,2,'Chạy bộ'),(3,3,'Đạp xe đạp'),(4,4,'Khác'),(5,5,'Bật cóc'),(6,6,'Gập bụng');
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
  `practiceId` int(11) NOT NULL,
  `advice` varchar(255) DEFAULT NULL,
  `numberOfTime` int(11) NOT NULL,
  `timeDuration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tm1k4dgtxnvodgg799d7pqlae` (`phaseId`),
  KEY `FK_rfsis7b5skye9c0gbh4mn2wch` (`practiceId`),
  CONSTRAINT `FK_rfsis7b5skye9c0gbh4mn2wch` FOREIGN KEY (`practiceId`) REFERENCES `practice` (`id`),
  CONSTRAINT `FK_tm1k4dgtxnvodgg799d7pqlae` FOREIGN KEY (`phaseId`) REFERENCES `phase` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicephase`
--

LOCK TABLES `practicephase` WRITE;
/*!40000 ALTER TABLE `practicephase` DISABLE KEYS */;
INSERT INTO `practicephase` VALUES (1,1,1,'chọn giày phù hợp',2,'30 phút'),(2,1,2,'chạy chậm, nhẹ nhàng',2,'10 phút'),(3,1,3,'chọn xe có chiều cao phù hợp',2,'30 phút'),(4,2,1,'chọn giày phù hợp',2,'20 phút'),(5,2,2,'chạy chậm, nhẹ nhàng',2,'20 phút'),(6,2,3,'chọn xe có chiều cao phù hợp',2,'20 phút'),(7,3,1,'chọn giày phù hợp',2,'20 phút'),(8,3,2,'chạy chậm, nhẹ nhàng',2,'10 phút'),(9,3,3,'chọn xe có chiều cao phù hợp',2,'30 phút'),(10,1,6,'số lượng tùy theo khả năng',2,'20 phút'),(11,2,6,'số lượng tùy theo khả năng',2,'15 phút'),(12,3,6,'số lượng tùy theo khả năng',2,'15 phút');
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
  `advice` varchar(255) DEFAULT NULL,
  `numberOfTime` int(11) NOT NULL,
  `timeDuration` varchar(255) DEFAULT NULL,
  `practiceId` int(11) NOT NULL,
  `treatmentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8dxt7at4ktdejhu60gg4df49p` (`practiceId`),
  KEY `FK_qa9wkeh3na0g3uj0tfvqiy49w` (`treatmentId`),
  CONSTRAINT `FK_8dxt7at4ktdejhu60gg4df49p` FOREIGN KEY (`practiceId`) REFERENCES `practice` (`id`),
  CONSTRAINT `FK_qa9wkeh3na0g3uj0tfvqiy49w` FOREIGN KEY (`treatmentId`) REFERENCES `treatment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicetreatment`
--

LOCK TABLES `practicetreatment` WRITE;
/*!40000 ALTER TABLE `practicetreatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `practicetreatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preventioncheck`
--

DROP TABLE IF EXISTS `preventioncheck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preventioncheck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `basalMetabolicRate` int(11) NOT NULL,
  `bloodPressure` int(11) NOT NULL,
  `bmi` float NOT NULL,
  `bodyFat` float NOT NULL,
  `bodyWater` float NOT NULL,
  `heartBeat` int(11) NOT NULL,
  `height` double NOT NULL,
  `impedance` int(11) NOT NULL,
  `muscleMass` float NOT NULL,
  `phaseAngle` float NOT NULL,
  `visceralFat` tinyint(4) NOT NULL,
  `waists` int(11) NOT NULL,
  `weight` double NOT NULL,
  `appointmentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2lurw6wup63mjc7ix8882kf97` (`appointmentId`),
  CONSTRAINT `FK_2lurw6wup63mjc7ix8882kf97` FOREIGN KEY (`appointmentId`) REFERENCES `appointment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preventioncheck`
--

LOCK TABLES `preventioncheck` WRITE;
/*!40000 ALTER TABLE `preventioncheck` DISABLE KEYS */;
/*!40000 ALTER TABLE `preventioncheck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propertyrecord`
--

DROP TABLE IF EXISTS `propertyrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertyrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paramMeasurementValue` varchar(255) DEFAULT NULL,
  `medicalRecordDataId` int(11) NOT NULL,
  `paramMeasurementId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r062k2q538bf1o4fp8ecoxowl` (`medicalRecordDataId`),
  KEY `FK_6fdn0oh1yb1ld5akum13wafmd` (`paramMeasurementId`),
  CONSTRAINT `FK_6fdn0oh1yb1ld5akum13wafmd` FOREIGN KEY (`paramMeasurementId`) REFERENCES `parammeasurement` (`id`),
  CONSTRAINT `FK_r062k2q538bf1o4fp8ecoxowl` FOREIGN KEY (`medicalRecordDataId`) REFERENCES `medicalrecorddata` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propertyrecord`
--

LOCK TABLES `propertyrecord` WRITE;
/*!40000 ALTER TABLE `propertyrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `propertyrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regimen`
--

DROP TABLE IF EXISTS `regimen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regimen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `updateTime` datetime DEFAULT NULL,
  `illnessId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1gnpbcrr6uksq8qmejr4onf2d` (`illnessId`),
  CONSTRAINT `FK_1gnpbcrr6uksq8qmejr4onf2d` FOREIGN KEY (`illnessId`) REFERENCES `illness` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regimen`
--

LOCK TABLES `regimen` WRITE;
/*!40000 ALTER TABLE `regimen` DISABLE KEYS */;
INSERT INTO `regimen` VALUES (1,NULL,1),(2,NULL,2),(3,NULL,3);
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
  `description` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Doctor','Doctor'),(2,'Patient','Patient'),(3,'Nurse','Nurse'),(4,'Doctor Manager','Doctor Manager'),(5,'Staff','Staff'),(6,'Admin','Admin'),(7,'Nutrition','Nutrition');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adviseFood` varchar(255) DEFAULT NULL,
  `adviseMedicine` varchar(255) DEFAULT NULL,
  `advisePractice` varchar(255) DEFAULT NULL,
  `caloriesBurnEveryday` int(11) NOT NULL,
  `fromDate` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `toDate` datetime DEFAULT NULL,
  `appointmentId` int(11) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1k406kjs718c9035j7oggwiu4` (`appointmentId`),
  CONSTRAINT `FK_1k406kjs718c9035j7oggwiu4` FOREIGN KEY (`appointmentId`) REFERENCES `appointment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unitoffood`
--

DROP TABLE IF EXISTS `unitoffood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unitoffood` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caloriesEstimate` float NOT NULL,
  `unitName` varchar(255) DEFAULT NULL,
  `foodId` int(11) NOT NULL,
  `listElementNutritionName` varchar(255) DEFAULT NULL,
  `listElementNutritionValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75orce4pd9veck60shyaio5tw` (`foodId`),
  CONSTRAINT `FK_75orce4pd9veck60shyaio5tw` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unitoffood`
--

LOCK TABLES `unitoffood` WRITE;
/*!40000 ALTER TABLE `unitoffood` DISABLE KEYS */;
INSERT INTO `unitoffood` VALUES (1,200,'bát',1,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,0.6,44.2,4.6,0.230,0,0,0,0,0,0'),(2,158,'gam',2,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','1.801,0,13,6.32,0,23.6,0,0.74,66,0,0,1.6,5,0.62'),(3,250,'gam',3,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','5.9,0,1.8,15,0,26,0,2.6,7.2,0.46,0.176,82.4,5.378,6.31'),(4,219,'gam',4,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','3.5,0,0,12.56,0,24.68,0,1.16,67,0,0,0,0,0'),(5,61,'gam',5,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,121,0,4.7,3.5,0,0,0,0,0,0,0,0'),(6,479,'tô',6,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,16,65.3,18.4,3.3,0,0,0,0,0,0,0'),(7,200,'gam',7,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,13,8,0,29,0,1.4,0,0,0,29,0,0.9'),(8,25,'gam',8,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,40,0.1,3.2,1.28,2.5,0.47,18,0.061,0.04,36.6,0.234,0.18'),(9,59,'gam',9,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,204,0,11,4.8,0,3,0,0.1,0,239,0,0'),(10,527,'đĩa',10,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,13.3,81.6,20.7,0.44,0,0,0,0,0,0,0'),(11,451,'tô',11,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,13.7,67.3,14.7,3.96,0,0,0,0,0,0,0'),(14,146,'lon',14,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,0,36.2,0,0,0,0,0,0,0,0,0'),(15,431,'tô',15,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,11.7,59.3,17.9,2.28,0,0,0,0,0,0,0'),(16,361,'tô',16,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,12.5,47.8,14.4,1.23,0,0,0,0,0,0,0'),(17,30,'ổ',17,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,7,1,15,2.7,0.8,6,147,0,0,0,0,0'),(18,80,'quả',18,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,6.7,2.2,7.3,0,0,0,0,0,0,0,0'),(19,50,'quả',19,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,2,5,0.6,6,0,3,62,0,0,0,0,0'),(20,237,'ly',20,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,0,0,0.3,0,0,5,0,0,0,0,0'),(21,131,'ly',21,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,6,4.3,0,8,1.5,8,124,0,0,0,0,0'),(22,58,'quả',22,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,1,0.2,5,0.6,1.6,1,1,0,0,51,0,0'),(23,154,'ly',23,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,1,0,13,1.6,0,0,14,0,0,0,0,0'),(24,455,'gam',24,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,1,1.2,95,13,3.4,8,3,0,0,0,0,0'),(25,103,'ly',25,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,30,2.4,12,8,0,0,107,0,0,0,0,0'),(26,366,'gam',26,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,1,1.4,80,6,2.4,2,0,0,0,0,0,0'),(27,97,'gam',27,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,0,0.2,21,2,1,0,5,0,0,0,0,0'),(28,130,'gam',28,'animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc','0,0,1,0.3,28,2.7,0.4,1,1,0,0,0,0'),(29,33,'333',29,'animalFat','12');
/*!40000 ALTER TABLE `unitoffood` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-28 19:07:18
