-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2022 at 09:11 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aps`
--

-- --------------------------------------------------------

--
-- Table structure for table `tela_login`
--

CREATE TABLE `tela_login` (
  `idUsuario` int(11) NOT NULL,
  `loginUsuario` varchar(15) NOT NULL,
  `senhaUsuario` varchar(15) NOT NULL,
  `adminUsuario` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tela_login`
--

INSERT INTO `tela_login` (`idUsuario`, `loginUsuario`, `senhaUsuario`, `adminUsuario`) VALUES
(1, 'leandro', 'gostoso', 'S'),
(2, 'walker', 'gabriella', 'S'),
(3, 'enzo', 'volvo', 'S'),
(4, 'elisa', 'harry', 'S'),
(5, 'alice', 'otaku', 'S');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tela_login`
--
ALTER TABLE `tela_login`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tela_login`
--
ALTER TABLE `tela_login`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
