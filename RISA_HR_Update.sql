SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema RISA_HR
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RISA_HR` DEFAULT CHARACTER SET utf8 ;
USE `RISA_HR` ;

-- -----------------------------------------------------
-- Table `RISA_HR`.`Organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`Organization` (
  `ID` INT(11) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`SecurityQuestion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`SecurityQuestion` (
  `ID` INT(11) NOT NULL,
  `SecurityQuestion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`UserPassword`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`UserPassword` (
  `ID` INT(11) NOT NULL,
  `UserPassword` VARCHAR(45) NOT NULL,
  `SecurityAnswer` VARCHAR(45) NOT NULL,
  `SecurityQuestion_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_StudentPassword_SecurityQuestion1_idx` (`SecurityQuestion_ID` ASC),
  CONSTRAINT `fk_StudentPassword_SecurityQuestion1`
    FOREIGN KEY (`SecurityQuestion_ID`)
    REFERENCES `RISA_HR`.`SecurityQuestion` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`AccountStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`AccountStatus` (
  `ID` INT NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISA_HR`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`Admin` (
  `ID` INT(11) NOT NULL,
  `RISACode` INT(11) NULL DEFAULT NULL,
  `LegalFirstName` VARCHAR(45) NOT NULL,
  `LegalLastName` VARCHAR(45) NOT NULL,
  `Organization_ID` INT(11) NOT NULL,
  `UserPassword_ID` INT(11) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `AccountStatus_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Admin_Organization1_idx` (`Organization_ID` ASC),
  INDEX `fk_Admin_StudentPassword1_idx` (`UserPassword_ID` ASC),
  INDEX `fk_Admin_AccountStatus1_idx` (`AccountStatus_ID` ASC),
  CONSTRAINT `fk_Admin_Organization1`
    FOREIGN KEY (`Organization_ID`)
    REFERENCES `RISA_HR`.`Organization` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Admin_StudentPassword1`
    FOREIGN KEY (`UserPassword_ID`)
    REFERENCES `RISA_HR`.`UserPassword` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Admin_AccountStatus1`
    FOREIGN KEY (`AccountStatus_ID`)
    REFERENCES `RISA_HR`.`AccountStatus` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`Certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`Certification` (
  `ID` INT(11) NOT NULL,
  `CertificationTitle` VARCHAR(45) NOT NULL,
  `CertificationURL` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`College`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`College` (
  `ID` INT(11) NOT NULL,
  `CollegeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`Major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`Major` (
  `ID` INT(11) NOT NULL,
  `Major` VARCHAR(45) NOT NULL,
  `College_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Major_College_idx` (`College_ID` ASC),
  CONSTRAINT `fk_Major_College`
    FOREIGN KEY (`College_ID`)
    REFERENCES `RISA_HR`.`College` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`Concentration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`Concentration` (
  `ID` INT(11) NOT NULL,
  `Concentration` VARCHAR(45) NOT NULL,
  `Major_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Concentration_Major1_idx` (`Major_ID` ASC),
  CONSTRAINT `fk_Concentration_Major1`
    FOREIGN KEY (`Major_ID`)
    REFERENCES `RISA_HR`.`Major` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`DegreeLevel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`DegreeLevel` (
  `ID` INT(11) NOT NULL,
  `DegreeLevel` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`Student` (
  `ID` INT(11) NOT NULL,
  `RISACode` INT(11) NOT NULL,
  `LegalFirstName` VARCHAR(45) NOT NULL,
  `LegalLastName` VARCHAR(45) NOT NULL,
  `PreferredName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `UserPassword_ID` INT(11) NOT NULL,
  `FieldofInterest` VARCHAR(45) NOT NULL,
  `AccountStatus_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Student_StudentPassword1_idx` (`UserPassword_ID` ASC),
  INDEX `fk_Student_AccountStatus1_idx` (`AccountStatus_ID` ASC),
  CONSTRAINT `fk_Student_StudentPassword1`
    FOREIGN KEY (`UserPassword_ID`)
    REFERENCES `RISA_HR`.`UserPassword` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_AccountStatus1`
    FOREIGN KEY (`AccountStatus_ID`)
    REFERENCES `RISA_HR`.`AccountStatus` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`RISAPosition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`RISAPosition` (
  `ID` INT(11) NOT NULL,
  `Position` VARCHAR(45) NOT NULL,
  `Student_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RISAPosition_Student1_idx` (`Student_ID` ASC),
  CONSTRAINT `fk_RISAPosition_Student1`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `RISA_HR`.`Student` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`StudentCertification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`StudentCertification` (
  `ID` INT(11) NOT NULL,
  `ExpirationMonth` INT(11) NULL DEFAULT NULL,
  `ExpirationYear` YEAR NULL DEFAULT NULL,
  `Student_ID` INT(11) NOT NULL,
  `Certification_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_StudentCertification_Student1_idx` (`Student_ID` ASC),
  INDEX `fk_StudentCertification_Certification1_idx` (`Certification_ID` ASC),
  CONSTRAINT `fk_StudentCertification_Certification1`
    FOREIGN KEY (`Certification_ID`)
    REFERENCES `RISA_HR`.`Certification` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_StudentCertification_Student1`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `RISA_HR`.`Student` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `RISA_HR`.`StudentCollege`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISA_HR`.`StudentCollege` (
  `ID` INT(11) NOT NULL,
  `Student_ID` INT(11) NOT NULL,
  `College_ID` INT(11) NOT NULL,
  `Major_ID` INT(11) NOT NULL,
  `DegreeLevel_ID` INT(11) NOT NULL,
  `Concentration_ID` INT(11) NOT NULL,
  `GradMonth` VARCHAR(45) NOT NULL,
  `GradYear` YEAR NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_StudentCollege_Student1_idx` (`Student_ID` ASC),
  INDEX `fk_StudentCollege_College1_idx` (`College_ID` ASC),
  INDEX `fk_StudentCollege_Major1_idx` (`Major_ID` ASC),
  INDEX `fk_StudentCollege_DegreeLevel1_idx` (`DegreeLevel_ID` ASC),
  INDEX `fk_StudentCollege_Concentration1_idx` (`Concentration_ID` ASC),
  CONSTRAINT `fk_StudentCollege_College1`
    FOREIGN KEY (`College_ID`)
    REFERENCES `RISA_HR`.`College` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_StudentCollege_Concentration1`
    FOREIGN KEY (`Concentration_ID`)
    REFERENCES `RISA_HR`.`Concentration` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_StudentCollege_DegreeLevel1`
    FOREIGN KEY (`DegreeLevel_ID`)
    REFERENCES `RISA_HR`.`DegreeLevel` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_StudentCollege_Major1`
    FOREIGN KEY (`Major_ID`)
    REFERENCES `RISA_HR`.`Major` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_StudentCollege_Student1`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `RISA_HR`.`Student` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
