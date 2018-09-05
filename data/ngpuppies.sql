-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.31-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.5.0.5249
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ngpuppies
DROP DATABASE IF EXISTS `ngpuppies`;
CREATE DATABASE IF NOT EXISTS `ngpuppies` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ngpuppies`;

-- Dumping structure for table ngpuppies.addresses
DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `AddressID` int(11) NOT NULL AUTO_INCREMENT,
  `Country` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `ZipCode` varchar(45) NOT NULL,
  `Street` varchar(45) NOT NULL,
  PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.addresses: ~4 rows (approximately)
DELETE FROM `addresses`;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` (`AddressID`, `Country`, `City`, `ZipCode`, `Street`) VALUES
	(1, 'Bulgaria', 'Sofia', '1680', 'Topli Dol 3'),
	(2, 'USA', 'Tampa', '3000', 'xxxxxxxxxxxx'),
	(3, 'Germany', 'Berlin', '3434', 'yyyyyyyyyyy'),
	(4, 'Bulgaria', 'Plovdiv', '2000', 'sdsadfafasdasfas'),
	(18, 'Bulgaria2', 'Plovdiv2', '1010', 'xxxxxxxxxxxxx1'),
	(19, 'Bulgaria', 'Plovdiv', '1001', 'xxxxxxxxxxxxx'),
	(20, 'United States1', 'Tampa1', '2222', 'North Tamiami Trail'),
	(21, 'UNITED STATES', 'Cityto', '1111', 'dsadfafdsafa'),
	(22, 'Germany', 'Berlin', '1000', 'Bern Strasse');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.bills
DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `BillID` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceID` int(11) NOT NULL DEFAULT '0',
  `SubscriberID` varchar(50) NOT NULL DEFAULT '0',
  `StartDate` date NOT NULL COMMENT 'Invoice date',
  `EndDate` date NOT NULL COMMENT 'Due date',
  `Amount` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `CurrencyID` int(11) NOT NULL DEFAULT '0',
  `PaymentDate` date DEFAULT NULL,
  PRIMARY KEY (`BillID`),
  KEY `FK_bills_services` (`ServiceID`),
  KEY `FK_bills_subscribers` (`SubscriberID`),
  KEY `FK_bills_currencies_idx` (`CurrencyID`),
  CONSTRAINT `FK_bills_currencies` FOREIGN KEY (`CurrencyID`) REFERENCES `currencies` (`CurrencyID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_bills_services` FOREIGN KEY (`ServiceID`) REFERENCES `services` (`ServiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_bills_subscribers` FOREIGN KEY (`SubscriberID`) REFERENCES `subscribers` (`PhoneNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.bills: ~4 rows (approximately)
DELETE FROM `bills`;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` (`BillID`, `ServiceID`, `SubscriberID`, `StartDate`, `EndDate`, `Amount`, `CurrencyID`, `PaymentDate`) VALUES
	(1, 1, '0888355101', '2018-07-15', '2018-08-15', 30.00000, 1, NULL),
	(2, 2, '0888355101', '2018-08-20', '2018-09-20', 40.54000, 2, '2018-09-03'),
	(3, 1, '7777777777', '2018-06-10', '2018-07-10', 15.93000, 1, '2018-08-30'),
	(6, 3, '0888355101', '2018-09-02', '2018-09-30', 99.00000, 2, NULL),
	(7, 3, '0888355101', '2018-09-13', '2018-09-22', 100.00000, 1, NULL),
	(8, 1, '7777777777', '2018-09-13', '2018-09-21', 91.00000, 1, NULL),
	(9, 7, '7777777777', '2018-09-12', '2018-09-21', 99999.00000, 1, NULL);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.currencies
DROP TABLE IF EXISTS `currencies`;
CREATE TABLE IF NOT EXISTS `currencies` (
  `CurrencyID` int(11) NOT NULL AUTO_INCREMENT,
  `Currency` varchar(50) NOT NULL,
  `ExchangeRate` decimal(10,5) NOT NULL,
  PRIMARY KEY (`CurrencyID`),
  UNIQUE KEY `Currency_UNIQUE` (`Currency`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.currencies: ~3 rows (approximately)
DELETE FROM `currencies`;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` (`CurrencyID`, `Currency`, `ExchangeRate`) VALUES
	(1, 'BGN', 1.00000),
	(2, 'EUR', 1.95583),
	(3, 'USD', 1.71264);
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` enum('ROLE_ADMIN','ROLE_USER') NOT NULL,
  PRIMARY KEY (`RoleID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.roles: ~2 rows (approximately)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`RoleID`, `Name`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.services
DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `ServiceID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ServiceID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.services: ~3 rows (approximately)
DELETE FROM `services`;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` (`ServiceID`, `Name`) VALUES
	(2, 'Internet'),
	(3, 'Tele'),
	(1, 'Telephone'),
	(7, 'WiFi');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.subscribers
DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE IF NOT EXISTS `subscribers` (
  `PhoneNumber` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `EGN` varchar(50) NOT NULL,
  `AddressID` int(11) NOT NULL,
  `BankID` int(11) NOT NULL,
  PRIMARY KEY (`PhoneNumber`),
  UNIQUE KEY `EGN_UNIQUE` (`EGN`),
  KEY `FK_subscribers_users` (`BankID`),
  KEY `FK_subscribers_addresses` (`AddressID`),
  CONSTRAINT `FK_subscribers_addresses` FOREIGN KEY (`AddressID`) REFERENCES `addresses` (`AddressID`),
  CONSTRAINT `FK_subscribers_users` FOREIGN KEY (`BankID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.subscribers: ~2 rows (approximately)
DELETE FROM `subscribers`;
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
INSERT INTO `subscribers` (`PhoneNumber`, `FirstName`, `LastName`, `EGN`, `AddressID`, `BankID`) VALUES
	('0888355101', 'Vladimir', 'Georgiev', '1111111111', 1, 1),
	('0888355102', 'Vladimir14', 'Georgiev1', '1111111113', 18, 1),
	('0888355103', 'Vladimir20', 'Georgiev1', '9999999111', 20, 1),
	('7777777777', 'Nataliya', 'Georgieva', '2222222222', 1, 2);
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `RoleID` int(11) NOT NULL,
  `EIK` varchar(20) DEFAULT NULL,
  `EmailAddress` varchar(30) DEFAULT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `isFirstLogin` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `EIK_UNIQUE` (`EIK`),
  KEY `FK_users_roles_idx` (`RoleID`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.users: ~4 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`UserID`, `Username`, `Password`, `RoleID`, `EIK`, `EmailAddress`, `enabled`, `isFirstLogin`) VALUES
	(1, 'unicredit', '$2a$10$Q2JA3Y9Ra1gcq1a4ANrKG.0xJCxhhcuvasRHURvVjPIvOwAHXtl9K', 2, '9999999999', '', 1, 0),
	(2, 'dsk_bank', '$2a$10$s0QfBO8lBOPXO7bpdF5kyO3JC4FTOW.hOZfFmCyutSQZAwRX/gV7W', 2, '0000000011', '', 1, 0),
	(3, 'donchominkov', '$2a$04$6f3gE29U5Lz.5nzlMtIQIuXfuyQ7bYjsWZKb9WfV8zbm7ljk.yGHa', 1, '1111111111', 'doncho@telerikacademy.bg', 1, 1),
	(4, 'petarraykov', '$2a$10$MJIjcUwbZZ/KTo1/qlZGVuywy0qpM0MsxWkE5nGfFevL44E80VXDu', 1, '2222222222', 'petar@telerikacademy.bg', 1, 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
