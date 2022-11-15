-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2022 at 03:30 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `route`
--

-- --------------------------------------------------------

--
-- Table structure for table `attempt`
--

CREATE TABLE `attempt` (
  `routeID` bigint(20) NOT NULL,
  `attemptID` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `numOfFalls` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attempt`
--

INSERT INTO `attempt` (`routeID`, `attemptID`, `date`, `numOfFalls`) VALUES
(5, 19, '2022-11-12', 0);

-- --------------------------------------------------------

--
-- Table structure for table `gymevent`
--

CREATE TABLE `gymevent` (
  `userID` bigint(20) NOT NULL,
  `eventID` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gymevent`
--

INSERT INTO `gymevent` (`userID`, `eventID`, `title`, `description`) VALUES
(2, 1, 'Halloween Party', 'Come to our gym for a Halloween Party.');

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `userID` bigint(20) NOT NULL,
  `routeID` bigint(20) NOT NULL,
  `climbingStyle` varchar(255) DEFAULT NULL,
  `difficulty` varchar(255) DEFAULT NULL,
  `locationAndEnvironment` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`userID`, `routeID`, `climbingStyle`, `difficulty`, `locationAndEnvironment`, `name`, `notes`) VALUES
(1, 4, 'Top-Rope', '5.13', 'Greensboro Climbing Center/Indoor', 'The Pink Dread', 'The most powerful dragon in Westeros.'),
(2, 5, 'Top-Rope', '5.9', 'High Point Climbing Gym/Indoor', 'Percy Jackson', 'Natural is on.'),
(1, 11, 'Top-Rope', '5.7', 'Greensboro Climbing Center/Indoor', 'Percy Jackson', 'Natural is on.');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `type`, `password`) VALUES
(1, 'benewoods02@gmail.com', 'Ben', 'admin', 'puzzledb'),
(2, 'climber@climber.com', 'climber', 'climber', 'climber'),
(3, 'gym@gym.com', 'gym', 'gym', 'gymgym'),
(4, 'gym2@gym.com', 'gym2', 'gym', 'gymgym');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attempt`
--
ALTER TABLE `attempt`
  ADD PRIMARY KEY (`attemptID`);

--
-- Indexes for table `gymevent`
--
ALTER TABLE `gymevent`
  ADD PRIMARY KEY (`eventID`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`routeID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attempt`
--
ALTER TABLE `attempt`
  MODIFY `attemptID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `gymevent`
--
ALTER TABLE `gymevent`
  MODIFY `eventID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `route`
--
ALTER TABLE `route`
  MODIFY `routeID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
