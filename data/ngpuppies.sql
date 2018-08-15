-- MySQL Script generated by MySQL Workbench
-- Thu Aug 16 00:44:42 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ngpuppies
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ngpuppies
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ngpuppies` DEFAULT CHARACTER SET latin1 ;
USE `ngpuppies` ;

-- -----------------------------------------------------
-- Table `ngpuppies`.`currencies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`currencies` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`currencies` (
  `CurrencyID` INT(11) NOT NULL AUTO_INCREMENT,
  `Currency` VARCHAR(50) NOT NULL,
  `ExhangeRate` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`CurrencyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`services` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`services` (
  `ServiceID` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ServiceID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`roles` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`roles` (
  `RoleID` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`RoleID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`users` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`users` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(20) NOT NULL DEFAULT '0',
  `Password` VARCHAR(30) NOT NULL DEFAULT '0',
  `RoleID` INT(11) NOT NULL,
  `EIK` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  INDEX `FK_users_roles` (`RoleID` ASC),
  CONSTRAINT `FK_users_roles`
    FOREIGN KEY (`RoleID`)
    REFERENCES `ngpuppies`.`roles` (`RoleID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`addresses` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`addresses` (
  `AddressID` INT NOT NULL,
  `Country` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `ZipCode` VARCHAR(45) NOT NULL,
  `Street` VARCHAR(45) NULL,
  PRIMARY KEY (`AddressID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`subscribers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`subscribers` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`subscribers` (
  `PhoneNumber` VARCHAR(50) NOT NULL,
  `FirstName` VARCHAR(50) NOT NULL,
  `LastName` VARCHAR(50) NOT NULL,
  `EGN` VARCHAR(50) NOT NULL,
  `AddressID` INT NOT NULL,
  `BankID` INT(11) NOT NULL,
  PRIMARY KEY (`PhoneNumber`),
  INDEX `FK_subscribers_users` (`BankID` ASC),
  INDEX `FK_subscribers_addresses_idx` (`AddressID` ASC),
  CONSTRAINT `FK_subscribers_users`
    FOREIGN KEY (`BankID`)
    REFERENCES `ngpuppies`.`users` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_subscribers_addresses`
    FOREIGN KEY (`AddressID`)
    REFERENCES `ngpuppies`.`addresses` (`AddressID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`bills`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`bills` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`bills` (
  `BillID` INT(11) NOT NULL AUTO_INCREMENT,
  `ServiceID` INT(11) NOT NULL DEFAULT 0,
  `SubscriberID` VARCHAR(50) NOT NULL DEFAULT '0',
  `StartDate` DATE NOT NULL COMMENT 'Invoice date',
  `EndDate` DATE NOT NULL COMMENT 'Due date',
  `Amount` DOUBLE NOT NULL DEFAULT 0,
  `CurrencyID` INT(11) NOT NULL DEFAULT 0,
  `PaymentDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`BillID`),
  INDEX `FK_bills_services` (`ServiceID` ASC),
  INDEX `FK_bills_currencies` (`CurrencyID` ASC),
  INDEX `FK_bills_subscribers` (`SubscriberID` ASC),
  CONSTRAINT `FK_bills_currencies`
    FOREIGN KEY (`CurrencyID`)
    REFERENCES `ngpuppies`.`currencies` (`CurrencyID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_bills_services`
    FOREIGN KEY (`ServiceID`)
    REFERENCES `ngpuppies`.`services` (`ServiceID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_bills_subscribers`
    FOREIGN KEY (`SubscriberID`)
    REFERENCES `ngpuppies`.`subscribers` (`PhoneNumber`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
