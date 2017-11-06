-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2017 at 01:40 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `MFAproject`
--

DELIMITER $$
--
-- Procedures
--
CREATE PROCEDURE `verifyAndUpdate` (IN `userId` VARCHAR(30), IN `dayIn` VARCHAR(30), IN `timeIn` VARCHAR(30), IN `ipAddr` VARCHAR(30), IN `zipcodeIn` VARCHAR(15), IN `cityIn` VARCHAR(30), IN `stateIn` VARCHAR(30), IN `countryIn` VARCHAR(30), IN `macIn` VARCHAR(30))  BEGIN
	IF ( SELECT EXISTS (SELECT 1 FROM `location_ip` WHERE `user_id`=userId and `ip`=ipAddr) ) THEN 
        UPDATE `location_ip` SET `count`=`count`+1 WHERE `user_id`=userId and `ip`=ipAddr;
    ELSE
		INSERT `location_ip` (`ip`,`count`,`user_id`) VALUES (ipAddr, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `location_zipcode` WHERE `user_id`=userId and `zipcode`=zipcodeIn) ) THEN 
        UPDATE `location_zipcode` SET `count`=`count`+1 WHERE `user_id`=userId and `zipcode`=zipcodeIn;
    ELSE
		INSERT `location_zipcode` (`zipcode`,`count`,`user_id`) VALUES (zipcodeIn, 1, userId);
    END IF;
	
	IF ( EXISTS (SELECT 1 FROM `location_city` WHERE `user_id`=userId and `city`=cityIn) ) THEN 
        UPDATE `location_city` SET `count`=`count`+1 WHERE `user_id`=userId and `city`=cityIn;
    ELSE
		INSERT `location_city` (`city`,`count`,`user_id`) VALUES (cityIn, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `location_state` WHERE `user_id`=userId and `state`=stateIn) ) THEN 
        UPDATE `location_state` SET `count`=`count`+1 WHERE `user_id`=userId and `state`=stateIn;
    ELSE
		INSERT `location_state` (`state`,`count`,`user_id`) VALUES (stateIn, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `location_country` WHERE `user_id`=userId and `country`=countryIn) ) THEN 
        UPDATE `location_country` SET `count`=`count`+1 WHERE `user_id`=userId and `country`=countryIn;
    ELSE
		INSERT `location_country` (`country`,`count`,`user_id`) VALUES (countryIn, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `device` WHERE `user_id`=userId and `mac_addr`=macIn) ) THEN 
        UPDATE `device` SET `count`=`count`+1 WHERE `user_id`=userId and `mac_addr`=macIn;
    ELSE
		INSERT `device` (`mac_addr`,`count`,`user_id`) VALUES (macIn, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `time_frame` WHERE `user_id`=userId and `time_frame`=timeIn) ) THEN 
        UPDATE `time_frame` SET `count`=`count`+1 WHERE `user_id`=userId and `time_frame`=timeIn;
    ELSE
		INSERT `time_frame` (`time_frame`,`count`,`user_id`) VALUES (timeIn, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `day_login` WHERE `user_id`=userId and `weekday`=dayIn) ) THEN 
    	select concat ("output of if = ",(SELECT 1 FROM `day_login` WHERE `user_id`=userId and `weekday`=dayIn)) as ''; 
        UPDATE `day_login` SET `count`=`count`+1 WHERE `user_id`=userId and `weekday`=dayIn;
    ELSE
		select concat ("In else part ") as ''; 
        INSERT INTO `day_login` (`weekday`,`count`,`user_id`) VALUES (dayIn, 1, userId);
    END IF;
	
	IF ( SELECT EXISTS (SELECT 1 FROM `total` WHERE `user_id`=userId and `item`='total') ) THEN 
        UPDATE `total` SET `total`=`total`+1 WHERE `user_id`=userId and `item`='total';
    ELSE
		INSERT `total` (`item`,`total`,`user_id`) VALUES ('total', 1, userId);
    END IF; 
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `day_login`
--

CREATE TABLE `day_login` (
  `weekday` varchar(20) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `day_login`
--

INSERT INTO `day_login` (`weekday`, `count`, `user_id`) VALUES
('Wed', 2, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `mac_addr` varchar(60) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`mac_addr`, `count`, `user_id`) VALUES
('ff:ff:ff:ff:ff:ff', 2, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `location_city`
--

CREATE TABLE `location_city` (
  `city` varchar(40) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location_city`
--

INSERT INTO `location_city` (`city`, `count`, `user_id`) VALUES
('Palm Beach', 1, 'xie'),
('Saint Paul', 1, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `location_country`
--

CREATE TABLE `location_country` (
  `country` varchar(40) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location_country`
--

INSERT INTO `location_country` (`country`, `count`, `user_id`) VALUES
('US', 2, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `location_ip`
--

CREATE TABLE `location_ip` (
  `ip` varchar(16) NOT NULL,
  `count` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location_ip`
--

INSERT INTO `location_ip` (`ip`, `count`, `user_id`) VALUES
('199.17.15.181', 1, 'xie'),
('23.33.164.215', 1, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `location_state`
--

CREATE TABLE `location_state` (
  `state` varchar(40) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location_state`
--

INSERT INTO `location_state` (`state`, `count`, `user_id`) VALUES
('FL', 1, 'xie'),
('MN', 1, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `location_zipcode`
--

CREATE TABLE `location_zipcode` (
  `zipcode` varchar(15) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location_zipcode`
--

INSERT INTO `location_zipcode` (`zipcode`, `count`, `user_id`) VALUES
('33480', 1, 'xie'),
('55101', 1, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `time_frame`
--

CREATE TABLE `time_frame` (
  `time_frame` varchar(10) NOT NULL,
  `count` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `time_frame`
--

INSERT INTO `time_frame` (`time_frame`, `count`, `user_id`) VALUES
('3-6pm', 2, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `total`
--

CREATE TABLE `total` (
  `item` varchar(30) NOT NULL,
  `total` int(15) NOT NULL,
  `user_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `total`
--

INSERT INTO `total` (`item`, `total`, `user_id`) VALUES
('total', 2, 'xie');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` varchar(30) NOT NULL,
  `user_pass` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_pass`, `email`, `phone`) VALUES
('xie', 'zhao123', 'mndarren@gmail.com', '6127876367');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `day_login`
--
ALTER TABLE `day_login`
  ADD PRIMARY KEY (`weekday`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`mac_addr`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `location_city`
--
ALTER TABLE `location_city`
  ADD PRIMARY KEY (`city`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `location_country`
--
ALTER TABLE `location_country`
  ADD PRIMARY KEY (`country`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `location_ip`
--
ALTER TABLE `location_ip`
  ADD PRIMARY KEY (`ip`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `location_state`
--
ALTER TABLE `location_state`
  ADD PRIMARY KEY (`state`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `location_zipcode`
--
ALTER TABLE `location_zipcode`
  ADD PRIMARY KEY (`zipcode`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `time_frame`
--
ALTER TABLE `time_frame`
  ADD PRIMARY KEY (`time_frame`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `total`
--
ALTER TABLE `total`
  ADD PRIMARY KEY (`item`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `user_id_2` (`user_id`),
  ADD KEY `user_id_3` (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `day_login`
--
ALTER TABLE `day_login`
  ADD CONSTRAINT `day_login_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `device_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `location_city`
--
ALTER TABLE `location_city`
  ADD CONSTRAINT `location_city_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `location_country`
--
ALTER TABLE `location_country`
  ADD CONSTRAINT `location_country_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `location_ip`
--
ALTER TABLE `location_ip`
  ADD CONSTRAINT `location_ip_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `location_state`
--
ALTER TABLE `location_state`
  ADD CONSTRAINT `location_state_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `location_zipcode`
--
ALTER TABLE `location_zipcode`
  ADD CONSTRAINT `location_zipcode_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `time_frame`
--
ALTER TABLE `time_frame`
  ADD CONSTRAINT `time_frame_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `total`
--
ALTER TABLE `total`
  ADD CONSTRAINT `total_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
