-- MySQL Script generated by MySQL Workbench
-- Mon Aug 20 02:58:17 2018
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
  `ExchangeRate` DECIMAL NOT NULL,
  PRIMARY KEY (`CurrencyID`),
  UNIQUE INDEX `Currency_UNIQUE` (`Currency` ASC))
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
  PRIMARY KEY (`ServiceID`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
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
  `Street` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AddressID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`roles` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`roles` (
  `RoleID` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` ENUM('ADMIN', 'USER') NOT NULL,
  PRIMARY KEY (`RoleID`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ngpuppies`.`clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ngpuppies`.`clients` ;

CREATE TABLE IF NOT EXISTS `ngpuppies`.`clients` (
  `ClientID` INT NOT NULL,
  `Username` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  `RoleID` INT NOT NULL,
  `EIK` VARCHAR(20) NOT NULL,
  `EmailAddress` VARCHAR(30) NULL,
  PRIMARY KEY (`ClientID`),
  UNIQUE INDEX `EIK_UNIQUE` (`EIK` ASC),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC),
  INDEX `FK_clients_roles_idx` (`RoleID` ASC),
  CONSTRAINT `FK_clients_roles`
    FOREIGN KEY (`RoleID`)
    REFERENCES `ngpuppies`.`roles` (`RoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
  INDEX `FK_subscribers_addresses_idx` (`AddressID` ASC),
  UNIQUE INDEX `EGN_UNIQUE` (`EGN` ASC),
  INDEX `FK_subscribers_clients_idx` (`BankID` ASC),
  CONSTRAINT `FK_subscribers_addresses`
    FOREIGN KEY (`AddressID`)
    REFERENCES `ngpuppies`.`addresses` (`AddressID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_subscribers_clients`
    FOREIGN KEY (`BankID`)
    REFERENCES `ngpuppies`.`clients` (`ClientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `Amount` DECIMAL NOT NULL DEFAULT 0,
  `CurrencyID` INT(11) NOT NULL DEFAULT 0,
  `PaymentDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`BillID`),
  INDEX `FK_bills_services` (`ServiceID` ASC),
  INDEX `FK_bills_subscribers` (`SubscriberID` ASC),
  INDEX `FK_bills_currencies_idx` (`CurrencyID` ASC),
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

-- -----------------------------------------------------
-- Data for table `ngpuppies`.`currencies`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`currencies` (`CurrencyID`, `Currency`, `ExchangeRate`) VALUES (1, 'BGN', 1);
INSERT INTO `ngpuppies`.`currencies` (`CurrencyID`, `Currency`, `ExchangeRate`) VALUES (2, 'EUR', 1.95583);
INSERT INTO `ngpuppies`.`currencies` (`CurrencyID`, `Currency`, `ExchangeRate`) VALUES (3, 'USD', 1.5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ngpuppies`.`services`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`services` (`ServiceID`, `Name`) VALUES (1, 'Telephone');
INSERT INTO `ngpuppies`.`services` (`ServiceID`, `Name`) VALUES (2, 'Internet');
INSERT INTO `ngpuppies`.`services` (`ServiceID`, `Name`) VALUES (3, 'Television');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ngpuppies`.`addresses`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`addresses` (`AddressID`, `Country`, `City`, `ZipCode`, `Street`) VALUES (1, 'Bulgaria', 'Sofia', '1680', 'Topli Dol 3');
INSERT INTO `ngpuppies`.`addresses` (`AddressID`, `Country`, `City`, `ZipCode`, `Street`) VALUES (2, 'USA', 'Tampa', '3000', 'xxxxxxxxxxxx');
INSERT INTO `ngpuppies`.`addresses` (`AddressID`, `Country`, `City`, `ZipCode`, `Street`) VALUES (3, 'Germany', 'Berlin', '3434', 'yyyyyyyyyyy');
INSERT INTO `ngpuppies`.`addresses` (`AddressID`, `Country`, `City`, `ZipCode`, `Street`) VALUES (4, 'Bulgaria', 'Plovdiv', '2000', 'sdsadfafasdasfas');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ngpuppies`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`roles` (`RoleID`, `Name`) VALUES (1, 'ADMIN');
INSERT INTO `ngpuppies`.`roles` (`RoleID`, `Name`) VALUES (2, 'USER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ngpuppies`.`clients`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`clients` (`ClientID`, `Username`, `Password`, `RoleID`, `EIK`, `EmailAddress`) VALUES (1, 'unicredit_bulbank', 'unicredit_bulbank', 2, '9999999999', '');
INSERT INTO `ngpuppies`.`clients` (`ClientID`, `Username`, `Password`, `RoleID`, `EIK`, `EmailAddress`) VALUES (2, 'dsk_bank', 'dsk_bank', 2, '0000000000', '');
INSERT INTO `ngpuppies`.`clients` (`ClientID`, `Username`, `Password`, `RoleID`, `EIK`, `EmailAddress`) VALUES (3, 'donchominkov', 'donchominkov', 1, '1111111111', 'doncho@telerikacademy.bg');
INSERT INTO `ngpuppies`.`clients` (`ClientID`, `Username`, `Password`, `RoleID`, `EIK`, `EmailAddress`) VALUES (4, 'petarraykov', 'petarraykov', 1, '2222222222', 'petar@telerikacademy.bg');
INSERT INTO `ngpuppies`.`clients` (`ClientID`, `Username`, `Password`, `RoleID`, `EIK`, `EmailAddress`) VALUES (5, 'united_bulgarian_bank', 'united_bulgarian_bank', 2, '3333333333', '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ngpuppies`.`subscribers`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`subscribers` (`PhoneNumber`, `FirstName`, `LastName`, `EGN`, `AddressID`, `BankID`) VALUES ('0888355101', 'Vladimir', 'Georgiev', '1111111111', 1, 1);
INSERT INTO `ngpuppies`.`subscribers` (`PhoneNumber`, `FirstName`, `LastName`, `EGN`, `AddressID`, `BankID`) VALUES ('7777777777', 'Nataliya', 'Georgieva', '2222222222', 1, 2);
INSERT INTO `ngpuppies`.`subscribers` (`PhoneNumber`, `FirstName`, `LastName`, `EGN`, `AddressID`, `BankID`) VALUES ('1111111111', 'Doncho', 'Minkov', '3333333333', 2, 1);
INSERT INTO `ngpuppies`.`subscribers` (`PhoneNumber`, `FirstName`, `LastName`, `EGN`, `AddressID`, `BankID`) VALUES ('1111111122', 'Petar', 'Raykov', '4444444444', 4, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ngpuppies`.`bills`
-- -----------------------------------------------------
START TRANSACTION;
USE `ngpuppies`;
INSERT INTO `ngpuppies`.`bills` (`BillID`, `ServiceID`, `SubscriberID`, `StartDate`, `EndDate`, `Amount`, `CurrencyID`, `PaymentDate`) VALUES (1, 1, '0888355101', '15.07.2018', '15.08.2018', 30, 1, '19.08.2018');
INSERT INTO `ngpuppies`.`bills` (`BillID`, `ServiceID`, `SubscriberID`, `StartDate`, `EndDate`, `Amount`, `CurrencyID`, `PaymentDate`) VALUES (2, 2, '0888355101', '15.07.2018', '15.08.2018', 40.54, 2, NULL);
INSERT INTO `ngpuppies`.`bills` (`BillID`, `ServiceID`, `SubscriberID`, `StartDate`, `EndDate`, `Amount`, `CurrencyID`, `PaymentDate`) VALUES (3, 1, '7777777777', '20.06.2018', '20.07.2018', 15.93, 1, '01.07.2018');

COMMIT;

