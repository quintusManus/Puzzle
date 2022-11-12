-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2022 at 02:09 AM
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
-- Table structure for table `gymroute`
--

CREATE TABLE `gymroute` (
  `id` bigint(20) NOT NULL,
  `name` varchar(256) NOT NULL,
  `difficulty` varchar(256) NOT NULL,
  `climbingStyle` varchar(256) NOT NULL,
  `locationAndEnvironment` varchar(256) NOT NULL,
  `notes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gymroute`
--

INSERT INTO `gymroute` (`id`, `name`, `difficulty`, `climbingStyle`, `locationAndEnvironment`, `notes`) VALUES
(1, 'Spongebob', '5.7', 'Top-Rope', 'High Point Climbing Gym/Indoor', 'Spongebob!!!'),
(2, 'Yellow', '5.7', 'Top-Rope', 'High Point Climbing Gym/Indoor', 'It\'s actually pink.');

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `id` bigint(20) NOT NULL,
  `name` varchar(256) NOT NULL,
  `difficulty` varchar(256) NOT NULL,
  `climbingStyle` varchar(256) NOT NULL,
  `locationAndEnvironment` varchar(256) NOT NULL,
  `notes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`id`, `name`, `difficulty`, `climbingStyle`, `locationAndEnvironment`, `notes`) VALUES
(3, 'Kelp Forest', '5.8', 'Top-Rope', 'Greensboro Climbing Gym/Indoor', 'Good luck!'),
(4, 'The Bridge of Khazad Dum', '5.9', 'Top-Rope', 'The Greensboro Climbing Gym/Indoor', 'Crack is on.'),
(5, 'Percy Jackson', '5.9', 'Top-Rope', 'Greensboro Climbing Center/Indoor', 'Arete is on.');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(64) NOT NULL,
  `type` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `type`) VALUES
(1, 'smuska@uncg.edu', 'caffeineaddict', 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gymroute`
--
ALTER TABLE `gymroute`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gymroute`
--
ALTER TABLE `gymroute`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `route`
--
ALTER TABLE `route`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
