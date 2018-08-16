-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ngpuppies
CREATE DATABASE IF NOT EXISTS `ngpuppies` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ngpuppies`;

-- Dumping structure for table ngpuppies.bills
CREATE TABLE IF NOT EXISTS `bills` (
  `BillID` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceID` int(11) NOT NULL DEFAULT 0,
  `SubscriberID` varchar(50) NOT NULL DEFAULT '0',
  `StartDate` date NOT NULL COMMENT 'Invoice date',
  `EndDate` date NOT NULL COMMENT 'Due date',
  `Amount` double NOT NULL DEFAULT 0,
  `CurrencyID` int(11) NOT NULL DEFAULT 0,
  `PaymentDate` date DEFAULT NULL,
  PRIMARY KEY (`BillID`),
  KEY `FK_bills_services` (`ServiceID`),
  KEY `FK_bills_currencies` (`CurrencyID`),
  KEY `FK_bills_subscribers` (`SubscriberID`),
  CONSTRAINT `FK_bills_currencies` FOREIGN KEY (`CurrencyID`) REFERENCES `currencies` (`CurrencyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_bills_services` FOREIGN KEY (`ServiceID`) REFERENCES `services` (`ServiceID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_bills_subscribers` FOREIGN KEY (`SubscriberID`) REFERENCES `subscribers` (`PhoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.bills: ~0 rows (approximately)
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.currencies
CREATE TABLE IF NOT EXISTS `currencies` (
  `CurrencyID` int(11) NOT NULL AUTO_INCREMENT,
  `Currency` varchar(50) NOT NULL,
  `ExhangeRate` double DEFAULT NULL,
  PRIMARY KEY (`CurrencyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.currencies: ~4 rows (approximately)
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
REPLACE INTO `currencies` (`CurrencyID`, `Currency`, `ExhangeRate`) VALUES
	(1, 'BGN', 1),
	(2, 'EUR', 1.96),
	(3, 'USD', 1.73),
	(4, 'GBP', 2.19);
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
REPLACE INTO `roles` (`RoleID`, `Name`) VALUES
	(1, 'Client'),
	(2, 'Admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.services
CREATE TABLE IF NOT EXISTS `services` (
  `ServiceID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ServiceID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.services: ~3 rows (approximately)
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
REPLACE INTO `services` (`ServiceID`, `Name`) VALUES
	(1, 'Telephone'),
	(2, 'Television'),
	(3, 'Internet');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.subscribers
CREATE TABLE IF NOT EXISTS `subscribers` (
  `PhoneNumber` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `EGN` varchar(50) NOT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `BankID` int(11) NOT NULL,
  PRIMARY KEY (`PhoneNumber`),
  KEY `FK_subscribers_users` (`BankID`),
  CONSTRAINT `FK_subscribers_users` FOREIGN KEY (`BankID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.subscribers: ~0 rows (approximately)
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;

-- Dumping structure for table ngpuppies.users
CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) NOT NULL DEFAULT '0',
  `Password` varchar(30) NOT NULL DEFAULT '0',
  `RoleID` int(11) NOT NULL,
  `EIK` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `FK_users_roles` (`RoleID`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ngpuppies.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;