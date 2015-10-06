-- MySQL Script generated by MySQL Workbench
-- 10/06/15 16:52:08
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hsts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hsts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hsts` DEFAULT CHARACTER SET utf8 ;
USE `hsts` ;

-- -----------------------------------------------------
-- Table `hsts`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `roleId` INT(11) NOT NULL,
  `username` VARCHAR(25) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `fullName` VARCHAR(45) NULL DEFAULT NULL,
  `status` TINYINT NULL COMMENT '1 In-active\n2 Active\n3 Block',
  `dateOfBirth` DATE NULL,
  `gender` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_role_idx` (`roleId` ASC),
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`roleId`)
    REFERENCES `hsts`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Doctor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `accountId` INT NOT NULL,
  `major` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_doctor_account1_idx` (`accountId` ASC),
  CONSTRAINT `fk_doctor_account1`
    FOREIGN KEY (`accountId`)
    REFERENCES `hsts`.`Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Food` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `foodName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Illness`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Illness` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`Regimen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Regimen` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `illnessId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Illness_Regimen_idx` (`illnessId` ASC),
  CONSTRAINT `FK_Illness_Regimen`
    FOREIGN KEY (`illnessId`)
    REFERENCES `hsts`.`Illness` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `accountId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_patient_account1_idx` (`accountId` ASC),
  CONSTRAINT `fk_patient_account1`
    FOREIGN KEY (`accountId`)
    REFERENCES `hsts`.`Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`MedicalRecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`MedicalRecord` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `illnessId` INT NULL,
  `doctorId` INT NOT NULL,
  `patientId` INT NOT NULL,
  `startTime` DATE NOT NULL,
  `endTime` DATE NULL,
  `status` TINYINT NULL COMMENT 'status = 1 cho kham\nstatus = 2 khong co benh\nstatus = 3 di kham co benh va dang dieu tri\nstatus = 4 benh da chua tri xong',
  `clinicalSymptoms` VARCHAR(200) NULL,
  `medicalHistory` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medical_record_Doctor1_idx` (`doctorId` ASC),
  INDEX `fk_medical_record_patient1_idx` (`patientId` ASC),
  INDEX `FK_Illness_MedicalRecord_idx` (`illnessId` ASC),
  CONSTRAINT `fk_medical_record_Doctor1`
    FOREIGN KEY (`doctorId`)
    REFERENCES `hsts`.`Doctor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_record_patient1`
    FOREIGN KEY (`patientId`)
    REFERENCES `hsts`.`Patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Illness_MedicalRecord`
    FOREIGN KEY (`illnessId`)
    REFERENCES `hsts`.`Illness` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Appointment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `medicalRecordId` INT NOT NULL,
  `appointmentDateTime` DATETIME NULL,
  `appointmentMessage` VARCHAR(200) NULL,
  `status` TINYINT NULL COMMENT 'status = 1 la cuoc hen chua duoc gap bac si\nstatus = 2 la cuoc hen da duoc gap bac si',
  `nextAppointment` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_appointment_medicalrecord_idx` (`medicalRecordId` ASC),
  INDEX `fk_appointment_nextappointment_idx` (`nextAppointment` ASC),
  CONSTRAINT `fk_appointment_medicalrecord`
    FOREIGN KEY (`medicalRecordId`)
    REFERENCES `hsts`.`MedicalRecord` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_nextappointment`
    FOREIGN KEY (`nextAppointment`)
    REFERENCES `hsts`.`Appointment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`MedicalRecordData`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`MedicalRecordData` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `appointmentId` INT NOT NULL,
  `height` INT(11) NULL DEFAULT NULL,
  `weight` INT(11) NULL DEFAULT NULL,
  `numberOfStep` INT(11) NULL DEFAULT NULL,
  `calories` INT(11) NULL DEFAULT NULL,
  `distance` FLOAT NULL,
  `dateCollectData` DATE NULL,
  `type` TINYINT NULL COMMENT 'type = 1 du lieu kham benh cua benh nhan\ntype = 2 du lieu thu thap duoc cua benh nhan chua duoc tinh toan\ntype = 3 du lieu thu thap duoc cua benh nhan da duoc tinh toan\n',
  PRIMARY KEY (`id`),
  INDEX `fk_data_appointment_idx` (`appointmentId` ASC),
  CONSTRAINT `fk_data_appointment`
    FOREIGN KEY (`appointmentId`)
    REFERENCES `hsts`.`Appointment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Medicine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Medicine` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `medicineName` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Treatment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Treatment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `appointientId` INT NOT NULL,
  `fromDate` DATE NOT NULL,
  `toDate` DATE NOT NULL,
  `adviseFood` VARCHAR(400) NULL,
  `adviseMedicine` VARCHAR(400) NULL,
  `advisePractice` VARCHAR(400) NULL,
  `status` TINYINT NULL COMMENT 'status = 1 treatment dang su dung\nstatus = 0 treatment ko con duoc su dung',
  PRIMARY KEY (`id`),
  INDEX `fk_treatement_appointment_idx` (`appointientId` ASC),
  CONSTRAINT `fk_treatement_appointment`
    FOREIGN KEY (`appointientId`)
    REFERENCES `hsts`.`Appointment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`MedicineTime`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`MedicineTime` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timeUse` TIME NULL,
  `treatmentId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medicine_time_treatment_idx` (`treatmentId` ASC),
  CONSTRAINT `fk_medicine_time_treatment`
    FOREIGN KEY (`treatmentId`)
    REFERENCES `hsts`.`Treatment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`MedicineTreatment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`MedicineTreatment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `medicineId` INT(11) NOT NULL,
  `numberOfMedicine` FLOAT NULL DEFAULT NULL,
  `medicineTimeId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medicine_treatment_medicine_idx` (`medicineId` ASC),
  INDEX `fk_medicine_treatment_medicineTime_idx` (`medicineTimeId` ASC),
  CONSTRAINT `fk_medicine_treatment_medicine`
    FOREIGN KEY (`medicineId`)
    REFERENCES `hsts`.`Medicine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_treatment_medicineTime`
    FOREIGN KEY (`medicineTimeId`)
    REFERENCES `hsts`.`MedicineTime` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Phase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Phase` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `regimenId` INT(11) NOT NULL,
  `fromdate` INT NULL DEFAULT NULL,
  `todate` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_phase_regimen1_idx` (`regimenId` ASC),
  CONSTRAINT `fk_phase_regimen1`
    FOREIGN KEY (`regimenId`)
    REFERENCES `hsts`.`Regimen` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Practice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Practice` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `practiceName` VARCHAR(45) NULL DEFAULT NULL,
  `intensity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hsts`.`Meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Meal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `treatmentId` INT NULL,
  `timeStart` TIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_treatment_idx` (`treatmentId` ASC),
  CONSTRAINT `fk_meal_treatment`
    FOREIGN KEY (`treatmentId`)
    REFERENCES `hsts`.`Treatment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`FoodTreatment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`FoodTreatment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `foodId` INT NOT NULL,
  `quantitative` VARCHAR(45) NULL,
  `mealId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_food_treatment_food_idx` (`foodId` ASC),
  INDEX `fk_food_treatment_meal_idx` (`mealId` ASC),
  CONSTRAINT `fk_food_treatment_food`
    FOREIGN KEY (`foodId`)
    REFERENCES `hsts`.`Food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_treatment_meal`
    FOREIGN KEY (`mealId`)
    REFERENCES `hsts`.`Meal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`PracticeTime`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`PracticeTime` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timeStart` TIME NULL,
  `treatmentId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_practice_time_treatment_idx` (`treatmentId` ASC),
  CONSTRAINT `fk_practice_time_treatment`
    FOREIGN KEY (`treatmentId`)
    REFERENCES `hsts`.`Treatment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`PracticeTreatment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`PracticeTreatment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `practiceId` INT NULL,
  `timeDuration` INT NULL,
  `practiceTimeId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_practice_treatment_practice_idx` (`practiceId` ASC),
  INDEX `fk_practice_treatment_practiceTime_idx` (`practiceTimeId` ASC),
  CONSTRAINT `fk_practice_treatment_practice`
    FOREIGN KEY (`practiceId`)
    REFERENCES `hsts`.`Practice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_practice_treatment_practiceTime`
    FOREIGN KEY (`practiceTimeId`)
    REFERENCES `hsts`.`PracticeTime` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`Device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Device` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `brandName` VARCHAR(45) NULL,
  `brandUuid` VARCHAR(45) NULL,
  `numberOfStepUuid` VARCHAR(45) NULL,
  `positionNumberOfStep` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`FoodPhase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`FoodPhase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phaseId` INT NOT NULL,
  `foodId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_phase_food_idx` (`phaseId` ASC),
  INDEX `fk_phase_food_food_idx` (`foodId` ASC),
  CONSTRAINT `fk_phase_food_phase`
    FOREIGN KEY (`phaseId`)
    REFERENCES `hsts`.`Phase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_phase_food_food`
    FOREIGN KEY (`foodId`)
    REFERENCES `hsts`.`Food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`MedicinePhase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`MedicinePhase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phaseId` INT NOT NULL,
  `medicineId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medicine_phase_phase_idx` (`phaseId` ASC),
  INDEX `fk_medicine_phase_medicine_idx` (`medicineId` ASC),
  CONSTRAINT `fk_medicine_phase_phase`
    FOREIGN KEY (`phaseId`)
    REFERENCES `hsts`.`Phase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_phase_medicine`
    FOREIGN KEY (`medicineId`)
    REFERENCES `hsts`.`Medicine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`PracticePhase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`PracticePhase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phaseId` INT NOT NULL,
  `practiceId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_practice_phase_phase_idx` (`phaseId` ASC),
  INDEX `fk_practice_phase_practice_idx` (`practiceId` ASC),
  CONSTRAINT `fk_practice_phase_phase`
    FOREIGN KEY (`phaseId`)
    REFERENCES `hsts`.`Phase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_practice_phase_practice`
    FOREIGN KEY (`practiceId`)
    REFERENCES `hsts`.`Practice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`Notify`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Notify` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `senderId` INT NOT NULL,
  `receiverId` INT NOT NULL,
  `type` TINYINT NULL COMMENT 'type = 1 notify cua benh nhan den bac si\ntype = 2 notify cua bac si den benh nhan - new prescription\ntype = 3 notify cua bac si den benh nhan - new appointment\ntype = 4 notify cua y ta den bac si',
  `status` BIT NULL COMMENT 'status = 0 chua nhan\nstatus = 1 da nhan',
  PRIMARY KEY (`id`),
  INDEX `fk_notify_sender_idx` (`senderId` ASC),
  INDEX `fk_notify_receiver_idx` (`receiverId` ASC),
  CONSTRAINT `fk_notify_sender`
    FOREIGN KEY (`senderId`)
    REFERENCES `hsts`.`Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notify_receiver`
    FOREIGN KEY (`receiverId`)
    REFERENCES `hsts`.`Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsts`.`Device_Patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hsts`.`Device_Patient` (
  `id` INT NOT NULL,
  `deviceId` INT NULL,
  `patientId` INT NULL,
  `wristbandAddress` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pd_patient_idx` (`patientId` ASC),
  INDEX `fk_pd_device_idx` (`deviceId` ASC),
  CONSTRAINT `fk_pd_patient`
    FOREIGN KEY (`patientId`)
    REFERENCES `hsts`.`Patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pd_device`
    FOREIGN KEY (`deviceId`)
    REFERENCES `hsts`.`Device` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
