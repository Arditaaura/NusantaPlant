-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `np`
--

-- --------------------------------------------------------

--
-- Table structure for table `game_session`
--

CREATE TABLE `game_session` (
  `id` char(100) NOT NULL,
  `uid` char(100) NOT NULL,
  `waktu_keluar` datetime NOT NULL,
  `waktu_haus` datetime NOT NULL,
  `waktu_lapar` datetime NOT NULL,
  `waktu_kesehatan` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `inventaris`
--

CREATE TABLE `inventaris` (
  `inventaris_id` int(100) NOT NULL,
  `user_id` char(100) NOT NULL,
  `tanaman_id` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventaris`
--

INSERT INTO `inventaris` (`inventaris_id`, `user_id`, `tanaman_id`) VALUES
(154, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(155, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(156, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(157, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(158, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(159, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(160, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(161, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL),
(162, '90583f91-461f-4538-9d67-6ec5ae59c201', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` char(100) NOT NULL,
  `item_nama` char(100) NOT NULL,
  `harga` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(100) NOT NULL,
  `user_id` char(100) NOT NULL,
  `umur_sekarang` int(100) NOT NULL,
  `kesehatan_sekarang` int(100) NOT NULL DEFAULT 2,
  `lapar_sekarang` int(100) NOT NULL DEFAULT 2,
  `haus_sekarang` int(100) NOT NULL DEFAULT 2,
  `tanaman_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `user_id`, `umur_sekarang`, `kesehatan_sekarang`, `lapar_sekarang`, `haus_sekarang`, `tanaman_id`) VALUES
(32, '2605182c-5957-4f20-b898-38bfafe14e9e', 3, 5, 5, 5, 1),
(33, '45a26695-5195-4051-96ad-23042bf49ccb', 3, 5, 5, 5, 2),
(34, '2762c902-88ec-4f82-89f0-0e6b0e5d9340', 0, 0, 0, 0, 2),
(35, '021b5b0f-9d43-4670-8356-f6f78eaef630', 3, 5, 5, 5, 1),
(36, '90583f91-461f-4538-9d67-6ec5ae59c201', 3, 5, 5, 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tanaman`
--

CREATE TABLE `tanaman` (
  `tanaman_id` char(100) NOT NULL,
  `nama` char(100) NOT NULL,
  `umur` int(100) NOT NULL,
  `kesehatan` int(100) NOT NULL,
  `lapar` int(100) NOT NULL,
  `haus` int(100) NOT NULL,
  `harga_beli` int(100) NOT NULL,
  `harga_jual` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tanaman`
--

INSERT INTO `tanaman` (`tanaman_id`, `nama`, `umur`, `kesehatan`, `lapar`, `haus`, `harga_beli`, `harga_jual`) VALUES
('1', 'anggrek', 0, 5, 5, 5, 50, 100),
('2', 'sedapMalam', 0, 5, 5, 5, 50, 100);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_bibit`
--

CREATE TABLE `transaksi_bibit` (
  `id_transaksi` int(11) NOT NULL,
  `user_id` char(100) NOT NULL,
  `tanaman_id` int(100) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_bibit`
--

INSERT INTO `transaksi_bibit` (`id_transaksi`, `user_id`, `tanaman_id`, `date`) VALUES
(35, '2605182c-5957-4f20-b898-38bfafe14e9e', 1, '2025-01-06 20:48:41'),
(36, '45a26695-5195-4051-96ad-23042bf49ccb', 2, '2025-01-06 20:51:26'),
(37, '2762c902-88ec-4f82-89f0-0e6b0e5d9340', 2, '2025-01-06 21:04:27'),
(38, '021b5b0f-9d43-4670-8356-f6f78eaef630', 1, '2025-01-06 21:46:55'),
(39, '90583f91-461f-4538-9d67-6ec5ae59c201', 2, '2025-01-06 22:18:29');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_item`
--

CREATE TABLE `transaksi_item` (
  `id_transaksi` int(11) NOT NULL,
  `user_id` char(100) NOT NULL,
  `kuantitas` int(11) NOT NULL,
  `item_id` char(100) NOT NULL,
  `tipe_transaksi` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_jual_tanaman`
--

CREATE TABLE `transaksi_jual_tanaman` (
  `id_transaksi` int(11) NOT NULL,
  `user_id` char(100) NOT NULL,
  `tanaman_id` int(100) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_tanaman_ke_inventori`
--

CREATE TABLE `transaksi_tanaman_ke_inventori` (
  `id_transaksi` int(11) NOT NULL,
  `user_id` char(100) NOT NULL,
  `tanaman_id` int(100) NOT NULL,
  `item_id` char(100) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` char(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `point` int(100) DEFAULT 100,
  `tanaman_id` int(100) DEFAULT NULL,
  `statusTanamanPertama` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `point`, `tanaman_id`, `statusTanamanPertama`) VALUES
('021b5b0f-9d43-4670-8356-f6f78eaef630', 'sepatu', 'super', 100, 1, 1),
('2605182c-5957-4f20-b898-38bfafe14e9e', 'kentang', 'goreng', 100, 1, 1),
('2762c902-88ec-4f82-89f0-0e6b0e5d9340', 'iqbal', 'ganteng', 100, 2, 1),
('45a26695-5195-4051-96ad-23042bf49ccb', 'mobil', 'balap', 100, 2, 1),
('90583f91-461f-4538-9d67-6ec5ae59c201', 'asep', '3333', 100, 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventaris`
--
ALTER TABLE `inventaris`
  ADD PRIMARY KEY (`inventaris_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `tanaman`
--
ALTER TABLE `tanaman`
  ADD PRIMARY KEY (`tanaman_id`);

--
-- Indexes for table `transaksi_bibit`
--
ALTER TABLE `transaksi_bibit`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transaksi_item`
--
ALTER TABLE `transaksi_item`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `transaksi_jual_tanaman`
--
ALTER TABLE `transaksi_jual_tanaman`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transaksi_tanaman_ke_inventori`
--
ALTER TABLE `transaksi_tanaman_ke_inventori`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventaris`
--
ALTER TABLE `inventaris`
  MODIFY `inventaris_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=163;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `transaksi_bibit`
--
ALTER TABLE `transaksi_bibit`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `transaksi_item`
--
ALTER TABLE `transaksi_item`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaksi_jual_tanaman`
--
ALTER TABLE `transaksi_jual_tanaman`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaksi_tanaman_ke_inventori`
--
ALTER TABLE `transaksi_tanaman_ke_inventori`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi_tanaman_ke_inventori`
--
ALTER TABLE `transaksi_tanaman_ke_inventori`
  ADD CONSTRAINT `transaksi_tanaman_ke_inventori_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
