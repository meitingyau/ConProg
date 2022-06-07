-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2022 at 04:15 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ifarm`
--

-- --------------------------------------------------------

--
-- Table structure for table `activities`
--

CREATE TABLE `activities` (
  `_id` varchar(50) NOT NULL,
  `farmId` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `action` varchar(128) NOT NULL,
  `type` varchar(128) NOT NULL,
  `unit` varchar(128) NOT NULL,
  `quantity` double(50,3) NOT NULL,
  `field` int(50) NOT NULL,
  `row` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `farmfertilizers`
--

CREATE TABLE `farmfertilizers` (
  `farmId` varchar(50) NOT NULL,
  `fertilizerId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farmfertilizers`
--

INSERT INTO `farmfertilizers` (`farmId`, `fertilizerId`) VALUES
('1', '4'),
('1', '9'),
('10', '100'),
('2', '13'),
('2', '14'),
('2', '15'),
('3', '25'),
('3', '26'),
('3', '27'),
('3', '28'),
('3', '29'),
('4', '30'),
('4', '31'),
('4', '38'),
('5', '43'),
('5', '44'),
('5', '52'),
('6', '53'),
('6', '54'),
('7', '59'),
('7', '73'),
('7', '74'),
('7', '78'),
('8', '82'),
('9', '87');

-- --------------------------------------------------------

--
-- Table structure for table `farmpesticide`
--

CREATE TABLE `farmpesticide` (
  `farmId` varchar(50) NOT NULL,
  `pesticideId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farmpesticide`
--

INSERT INTO `farmpesticide` (`farmId`, `pesticideId`) VALUES
('1', '10'),
('1', '2'),
('1', '3'),
('10', '94'),
('2', '14'),
('2', '16'),
('3', '20'),
('3', '23'),
('3', '96'),
('4', '36'),
('5', '48'),
('5', '49'),
('6', '54'),
('6', '55'),
('6', '93'),
('7', '64'),
('7', '65'),
('7', '66'),
('7', '69'),
('7', '70'),
('7', '75'),
('8', '90'),
('9', '88');

-- --------------------------------------------------------

--
-- Table structure for table `farmplants`
--

CREATE TABLE `farmplants` (
  `farmId` varchar(50) NOT NULL,
  `plantId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farmplants`
--

INSERT INTO `farmplants` (`farmId`, `plantId`) VALUES
('1', '24'),
('1', '9'),
('10', '1'),
('10', '10'),
('10', '7'),
('10', '90'),
('10', '91'),
('10', '92'),
('10', '93'),
('10', '94'),
('2', '14'),
('2', '28'),
('2', '31'),
('3', '18'),
('3', '19'),
('3', '21'),
('3', '22'),
('3', '25'),
('3', '50'),
('4', '29'),
('4', '30'),
('4', '32'),
('4', '35'),
('5', '42'),
('5', '43'),
('5', '45'),
('5', '46'),
('5', '48'),
('5', '49'),
('5', '51'),
('5', '52'),
('6', '13'),
('6', '14'),
('6', '15'),
('6', '53'),
('6', '54'),
('6', '55'),
('6', '56'),
('6', '57'),
('6', '58'),
('7', '16'),
('7', '17'),
('7', '20'),
('7', '23'),
('7', '26'),
('7', '27'),
('7', '59'),
('7', '60'),
('7', '61'),
('7', '62'),
('7', '63'),
('7', '64'),
('7', '65'),
('7', '66'),
('7', '67'),
('7', '68'),
('7', '69'),
('7', '70'),
('7', '71'),
('7', '72'),
('7', '73'),
('7', '74'),
('7', '75'),
('7', '76'),
('7', '77'),
('7', '78'),
('7', '79'),
('8', '33'),
('8', '34'),
('8', '36'),
('8', '37'),
('8', '38'),
('8', '80'),
('8', '81'),
('8', '82'),
('8', '83'),
('9', '39'),
('9', '40'),
('9', '41'),
('9', '44'),
('9', '47'),
('9', '84'),
('9', '85'),
('9', '86'),
('9', '87'),
('9', '88'),
('9', '89');

-- --------------------------------------------------------

--
-- Table structure for table `farms`
--

CREATE TABLE `farms` (
  `_id` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farms`
--

INSERT INTO `farms` (`_id`, `name`, `address`) VALUES
('1', 'Urban Hijau', 'Jalan 3/71b Kampung Sungai Penchala, Taman Pinggir Tun Dr Ismail, 60000 Kuala Lumpur'),
('10', 'Gapam Eco Farm', 'PT 4078, Jalan Gapam, Hang Tuah Jaya, 77200 Jasin, Melaka'),
('2', 'Happy Green Farms', '96, 2, Jalan 8/62a, Bandar Menjalara, 52200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur'),
('3', 'Babylon Vertical Farms', '12, Jalan PJU 5/1 Encorp Strand Garden Offices, Kota Damansara, 47810 Petaling Jaya, Selangor'),
('4', 'Raju\'s Hill Strawberry Farm', '129 59, Jalan Tapah, Brinchang, 39000 Brinchang, Pahang'),
('5', 'Highlands Apiary Farm', 'Jalan BOH, Tanah Rata, 39000 Ringlet, Pahang'),
('6', 'Evergreen Farm', 'D2-0, Parklane, 16, Jalan 1/152, Taman OUG, 58200 Kuala Lumpur, Federal Territory of Kuala Lumpur'),
('7', 'E-FARM', '2, Jalan Damai Rasa 1, The Corner @ Alam Damai, 56000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur'),
('8', 'Agro Bright Organic Farm', 'hang tuah jaya, Lt4547, lorong zamrud, 5/2, Jalan Zamrud 1, Taman Kerjasama, 75450 Ayer Keroh, Malacca'),
('9', 'Happy Orchard Farm', '69-A, Lorong AS 5, Kampung Ayer Salak, 75250 Malacca');

-- --------------------------------------------------------

--
-- Table structure for table `farmusers`
--

CREATE TABLE `farmusers` (
  `farmId` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farmusers`
--

INSERT INTO `farmusers` (`farmId`, `userId`) VALUES
('1', '1'),
('1', '10'),
('1', '24'),
('1', '4'),
('1', '5'),
('1', '6'),
('1', '7'),
('1', '8'),
('1', '9'),
('10', '90'),
('10', '91'),
('10', '92'),
('10', '93'),
('10', '94'),
('2', '1'),
('2', '11'),
('2', '12'),
('2', '13'),
('2', '14'),
('2', '15'),
('2', '3'),
('3', '14'),
('3', '16'),
('3', '17'),
('3', '18'),
('3', '19'),
('3', '2'),
('3', '20'),
('3', '21'),
('3', '22'),
('3', '23'),
('3', '25'),
('3', '26'),
('3', '27'),
('3', '28'),
('3', '29'),
('3', '50'),
('4', '30'),
('4', '31'),
('4', '32'),
('4', '33'),
('4', '34'),
('4', '35'),
('4', '36'),
('4', '38'),
('4', '39'),
('4', '40'),
('5', '37'),
('5', '41'),
('5', '42'),
('5', '43'),
('5', '44'),
('5', '45'),
('5', '46'),
('5', '47'),
('5', '48'),
('5', '49'),
('5', '51'),
('5', '52'),
('6', '53'),
('6', '54'),
('6', '55'),
('6', '56'),
('6', '57'),
('6', '58'),
('7', '59'),
('7', '60'),
('7', '61'),
('7', '62'),
('7', '63'),
('7', '64'),
('7', '65'),
('7', '66'),
('7', '67'),
('7', '68'),
('7', '69'),
('7', '70'),
('7', '71'),
('7', '72'),
('7', '73'),
('7', '74'),
('7', '75'),
('7', '76'),
('7', '77'),
('7', '78'),
('7', '79'),
('8', '80'),
('8', '81'),
('8', '82'),
('8', '83'),
('9', '84'),
('9', '85'),
('9', '86'),
('9', '87'),
('9', '88'),
('9', '89');

-- --------------------------------------------------------

--
-- Table structure for table `fertilizers`
--

CREATE TABLE `fertilizers` (
  `_id` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `unitType` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fertilizers`
--

INSERT INTO `fertilizers` (`_id`, `name`, `unitType`) VALUES
('1', 'GROBEL NPK 4-3-3+1MgO', 'kg'),
('10', 'Serbajadi Fertilizer', 'kg'),
('100', 'XinLianXin urea', 'kg'),
('11', 'Superbmix', 'kg'),
('12', 'Baja Organik Ino Nature Fertilizer Premium Super Bio-C (Sbioc)', 'kg'),
('13', 'NovaTec Suprem 21-5-10(+3+TE)-Granular & Stabilized Granular Fertilizers\r\n\r\n', 'kg'),
('14', 'Serbajadi Plant Food Growing Inducer 45', 'kg'),
('15', 'Baja Pokok NPK 15 15 15', 'kg'),
('16', 'VestiGrow Slow Release Fertilizer 25kg 23-9-13 Green Controlled Release Fertilizer', 'kg'),
('17', 'Organic Fertilizer 11 IN 1 Organic Soil Vegetable Flower Tree Plan\r\n', 'kg'),
('18', 'Gardening Soil Fertilized Mixed\r\n', 'kg'),
('19', 'Baja Nrich Bio-Organik (Baja Bof)\r\n', 'kg'),
('2', 'Activit 4-3-2', 'kg'),
('20', 'Fertilizer Soya Fish 8-8-8-8+Te\r\n', 'kg'),
('21', 'Serbajadi Plant Food Fruiting Inducer 43 Fertilizers\r\n', 'kg'),
('22', 'Besgrow Supreme Fertilizer', 'kg'),
('23', 'Novatec Premium 15-3-20(+2+TE)', 'kg'),
('24', 'TIP TOP [Original] BABA Mr Ganick 100% Authentic Organic Fertilizer 426-Vegetable', 'kg'),
('25', 'TIP TOP [Original] BABA Mr Ganick 100% Authentic Organic Fertilizer 532-Leafy\r\n', 'kg'),
('26', 'TIP TOP [Original] BABA Mr Ganick 100% Authentic Organic Fertilizer 549-Flower\r\n', 'kg'),
('27', 'TIP TOP [Original] BABA Mr Ganick 100% Authentic Organic Fertilizer 258-Fruit\r\n\r\n', 'kg'),
('28', 'Besgrow Grass Fertilizer', 'kg'),
('29', 'Biogold Original Japanese Top Bonsai and All Purpose Organic Fertilizer Baja Bonsai\r\n', 'kg'),
('3', 'Fertiplus 4-2-10\r\n', 'kg'),
('30', 'Tasty Garden Professional Fertilizers\r\n', 'kg'),
('31', 'Miracle-gro\r\n', 'kg'),
('32', 'Eraser\r\n', 'kg'),
('33', 'General Hydroponics', 'kg'),
('34', 'Pennington', 'kg'),
('35', 'Jobe\'s', 'kg'),
('36', 'Fox Farm', 'kg'),
('37', 'Neptune\'s Harvest\r\n', 'kg'),
('38', 'Safer Brand\r\n', 'kg'),
('39', 'Scotts\r\n', 'kg'),
('4', 'Nutri Smart Active Eco-Fertilizer', 'kg'),
('40', 'Bayer Corporation\r\n', 'kg'),
('41', 'Hydroton\r\n', 'kg'),
('42', 'Whitney Farms\r\n', 'kg'),
('43', 'Clonex', 'kg'),
('44', 'Dr. Earth', 'kg'),
('45', 'Hoffman\r\n', 'kg'),
('46', 'Monteray\r\n', 'kg'),
('47', 'Dyna-Gro\r\n', 'kg'),
('48', 'Advanced Nutrients\r\n', 'kg'),
('49', 'Grow More\r\n', 'kg'),
('5', 'BG Soil', 'kg'),
('50', 'Seacom\r\n', 'kg'),
('51', 'Ferti-lome\r\n', 'kg'),
('52', 'Natural Industries\r\n', 'kg'),
('53', 'Bonide\r\n', 'kg'),
('54', 'Aero Garden\r\n', 'kg'),
('55', 'Honor Guard', 'kg'),
('56', 'Dip \'N Grow', 'kg'),
('57', 'Nature\'s Wisdom\r\n', 'kg'),
('58', 'Hydrofarm\r\n', 'kg'),
('59', 'Ionic', 'kg'),
('6', 'Grobel NPK 4-3-3 +1 MgO', 'kg'),
('60', 'Rtu', 'kg'),
('61', 'AlphaChemicals', 'kg'),
('62', 'Green Bamboo', 'kg'),
('63', 'Botanicare', 'kg'),
('64', 'Azomite', 'kg'),
('65', 'Pramitol\r\n', 'kg'),
('66', 'Delta\r\n', 'kg'),
('67', 'Espoma', 'kg'),
('68', 'Tylan', 'kg'),
('69', 'Celatom\r\n', 'kg'),
('7', 'Greenferti 4-3-3-1\r\n', 'kg'),
('70', 'Blue Lightening', 'kg'),
('71', 'TVC Herbicide', 'kg'),
('72', 'GreenSense', 'kg'),
('73', 'Martin\'s', 'kg'),
('74', 'GS Plant Foods', 'kg'),
('75', 'Bonsai Boy', 'kg'),
('76', 'EncapConc SysGarden Accessory', 'kg'),
('77', 'Hinterland Trading', 'kg'),
('78', 'Voluntary Purchasing Group', 'kg'),
('79', 'Dominion 21', 'kg'),
('8', 'MixO’Plus\r\n', 'kg'),
('80', 'Flora Bloom', 'kg'),
('81', 'Shamrock Organic Fertilizer', 'kg'),
('82', 'Jobe’s Organics All Purpose Fertilizer', 'kg'),
('83', 'Scotts Turf Builder UltraFeed\r\n', 'kg'),
('84', 'Miracle-Gro All Purpose Plant Food', 'kg'),
('85', 'BioAdvanced Tree and Shrub Feed and Protect\r\n', 'kg'),
('86', 'Jobe’s Organics Annuals and Perennials Granular Fertilizer', 'kg'),
('87', 'Hyponex All-Purpose Garden Fertilizer', 'kg'),
('88', 'Dr. Earth Home Grown Organic Tomato, Vegetable, & Herb Fertilize', 'kg'),
('89', 'Espoma Organic Plant Tone', 'kg'),
('9', 'Baja organik Champion', 'kg'),
('90', 'Osmocote Flower and Vegetable Smart Release Fertilizer', 'kg'),
('91', 'KunLun Urea\r\n', 'kg'),
('92', 'YiHua Urea', 'kg'),
('93', 'Kunlun seaweed Urea', 'kg'),
('94', 'HuaJin Urea', 'kg'),
('95', 'HuaShan Urea', 'kg'),
('96', 'CNOOC FuDao Urea', 'kg'),
('97', 'LuXi Urea', 'kg'),
('98', 'YunTianHua Urea', 'kg'),
('99', 'JianFeng Urea\r\n', 'kg');

-- --------------------------------------------------------

--
-- Table structure for table `pesticide`
--

CREATE TABLE `pesticide` (
  `_id` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `unitType` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesticide`
--

INSERT INTO `pesticide` (`_id`, `name`, `unitType`) VALUES
('1', 'Lasso', 'L'),
('10', 'Micro‐Tech', 'L'),
('100', 'Ferxone', 'L'),
('101', 'Lawn‐Keep', 'L'),
('102', 'Macondray', 'L'),
('103', 'Pennamine D', 'L'),
('104', 'Planotox', 'L'),
('105', 'Plantgard', 'L'),
('106', 'Tributon', 'L'),
('107', 'Weed‐B‐Gon', 'L'),
('108', 'Weedar', 'L'),
('109', 'Weedone', 'L'),
('11', 'Nudor Extra', 'L'),
('110', 'Weedmaster', 'L'),
('111', 'Weed & Feed', 'L'),
('112', 'Weedatul', 'L'),
('113', 'Chipco Turf Herbicide D', 'L'),
('114', 'DMA‐4', 'L'),
('115', 'Esterone 99', 'L'),
('116', 'Formula 40', 'L'),
('117', 'Spritz‐Hormit', 'L'),
('118', 'Weed‐Ag‐Bar', 'L'),
('119', 'Weedez Wonder Bar', 'L'),
('12', 'Bronco', 'L'),
('120', 'Basagran', 'L'),
('121', 'Acme Super Brush Killer 875', 'L'),
('122', 'U 46 DP', 'L'),
('123', 'Duplosan DP‐D', 'L'),
('124', 'Duplasan KV‐ Combi', 'L'),
('125', 'Chipco Turf Kleen', 'L'),
('126', '2 Plus 2', 'L'),
('127', 'Actril DS', 'L'),
('128', 'Mad', 'L'),
('129', 'Gordon’s Vegemec Vegetation Killer', 'L'),
('13', 'Alanex', 'L'),
('130', 'Lentemul', 'L'),
('131', 'Dalapon‐Na', 'L'),
('132', 'Ded‐Weed', 'L'),
('133', 'Devipon', 'L'),
('134', 'Gramevin', 'L'),
('135', 'Revenge', 'L'),
('136', 'Unipon', 'L'),
('137', 'Dowpon M', 'L'),
('138', 'Radapon', 'L'),
('139', 'Basfapon', 'L'),
('14', 'Bullet', 'L'),
('140', 'Basinex P and N', 'L'),
('141', 'Revenge', 'L'),
('142', 'Nemafume', 'L'),
('143', 'Nemanax', 'L'),
('144', 'Nemaset', 'L'),
('145', 'BBC 12', 'L'),
('146', 'Fumazone', 'L'),
('147', 'Nemagon', 'L'),
('148', 'Nematocide', 'L'),
('149', 'Oxy', 'L'),
('15', 'Stake', 'L'),
('150', 'DOA', 'L'),
('16', 'Shroud', 'L'),
('17', 'Temik', 'L'),
('18', 'UC21149', 'L'),
('19', 'Aatrex', 'L'),
('2', 'Pillarzo', 'L'),
('20', 'Aktikon', 'L'),
('21', 'Atrazinax', 'L'),
('22', 'Atratol', 'L'),
('23', 'Fenamin', 'L'),
('24', 'Aatrex', 'L'),
('25', 'Prozine', 'L'),
('26', 'Gesaprim', 'L'),
('27', 'Zeaphos', 'L'),
('28', 'Nudor Extra', 'L'),
('29', 'Atramet Combi', 'L'),
('3', 'Alatox‐480', 'L'),
('30', 'Crisazin‐Crisatrina', 'L'),
('31', 'Kombi', 'L'),
('32', 'Drexel', 'L'),
('33', 'Rhino', 'L'),
('34', 'Farmco Anizine', 'L'),
('35', 'Aaa Flowable', 'L'),
('36', 'Marksman', 'L'),
('37', 'Primextra', 'L'),
('38', 'Bicep', 'L'),
('39', 'Conquest', 'L'),
('4', 'Alazine', 'L'),
('40', 'Candex', 'L'),
('41', 'Extrazine', 'L'),
('42', 'Vestal', 'L'),
('43', 'Rapuzin', 'L'),
('44', 'Pramatol', 'L'),
('45', 'Surpass', 'L'),
('46', 'Bullet', 'L'),
('47', 'Buctril', 'L'),
('48', 'Laddock', 'L'),
('49', 'Bay 70143', 'L'),
('5', 'ALA', 'L'),
('50', 'Crisfura', 'L'),
('51', 'Curaterr', 'L'),
('52', 'Yaltox', 'L'),
('53', 'Furadan', 'L'),
('54', 'Carbodan', 'L'),
('55', 'Carbosip', 'L'),
('56', 'Chinufur', 'L'),
('57', 'Kenofuran', 'L'),
('58', 'Niagara', 'L'),
('59', 'Forchlor', 'L'),
('6', 'Lozo', 'L'),
('60', 'Kill‐Ko', 'L'),
('61', 'Sydane', 'L'),
('62', 'Belt', 'L'),
('63', 'Chlor Kil\r\n', 'L'),
('64', 'Chlorotox', 'L'),
('65', 'Corodane', 'L'),
('66', 'Gold Crest C‐100', 'L'),
('67', 'Kilex Lindane', 'L'),
('68', 'Kypchlo', 'L'),
('69', 'Octachlor', 'L'),
('7', 'Lariat', 'L'),
('70', 'Synklor', 'L'),
('71', 'Termided', 'L'),
('72', 'Topiclor 20', 'L'),
('73', 'Velsicol 1068', 'L'),
('74', 'Aspon‐chlordate', 'L'),
('75', 'Ortho‐Klor', 'L'),
('76', 'Niran', 'L'),
('77', 'Termide', 'L'),
('78', 'Chlorohepton', 'L'),
('79', '2,4‐Dichlorophenoxy acetic acid', 'L'),
('8', 'Marksman', 'L'),
('80', 'Acme Main 4', 'L'),
('81', 'Acme Butyl Ester 4', 'L'),
('82', 'Acme LV 4', 'L'),
('83', 'Acme LV 6', 'L'),
('84', 'Agrotect', 'L'),
('85', 'Amoxone', 'L'),
('86', 'Aquakleen', 'L'),
('87', 'Chlorozxone', 'L'),
('88', 'Croprider', 'L'),
('89', 'Crossbow', 'L'),
('9', 'Freedom', 'L'),
('90', 'D50', 'L'),
('91', 'Dinoxol', 'L'),
('92', 'DMA‐4', 'L'),
('93', 'Dormone', 'L'),
('94', 'Emulsamine BK', 'L'),
('95', 'Emulsamine E‐3', 'L'),
('96', 'Estone', 'L'),
('97', 'Fernesta', 'L'),
('98', 'Fernimine', 'L'),
('99', 'Fernoxone', 'L');

-- --------------------------------------------------------

--
-- Table structure for table `plants`
--

CREATE TABLE `plants` (
  `_id` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `unitType` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `plants`
--

INSERT INTO `plants` (`_id`, `name`, `unitType`) VALUES
('1', 'African sheepbush', 'kg'),
('10', 'Bamboo', 'kg'),
('100', 'Tomato', 'kg'),
('11', 'Banana', 'kg'),
('12', 'Baobab', 'kg'),
('13', 'Bearberry ', 'kg'),
('14', 'Bear corn', 'kg'),
('15', 'Beech', 'kg'),
('16', 'Bird of paradise', 'kg'),
('17', 'California bay', 'kg'),
('18', 'California buckeye', 'kg'),
('19', 'California sycamore', 'kg'),
('2', 'Almond', 'kg'),
('20', 'California walnut', 'kg'),
('21', 'Canada root', 'kg'),
('22', 'Cancer jalap', 'kg'),
('23', 'Carrot', 'kg'),
('24', 'Desert Rose', 'kg'),
('25', 'Devil\'s bite ', 'kg'),
('26', 'Devil\'s darning needle', 'kg'),
('27', 'Devil\'s nose', 'kg'),
('28', 'Devil\'s plague', 'kg'),
('29', 'Durian', 'kg'),
('3', 'Aloe vera ', 'kg'),
('30', 'Easter orchid', 'kg'),
('31', 'Earth gall', 'kg'),
('32', 'Elderberry', 'kg'),
('33', 'Elegant lupine', 'kg'),
('34', 'Elephant apple', 'kg'),
('35', 'English bull\'s eye', 'kg'),
('36', 'Eucalyptus', 'kg'),
('37', 'Evergreen huckleberry', 'kg'),
('38', 'Extinguisher moss', 'kg'),
('39', 'Eytelia', 'kg'),
('4', 'Amy root ', 'kg'),
('40', 'Fairymoss', 'kg'),
('41', 'Fellenwort', 'kg'),
('42', 'Felonwood', 'kg'),
('43', 'Felonwort', 'kg'),
('44', 'Fennel', 'kg'),
('45', 'Golden buttons', 'kg'),
('46', 'Golden chain', 'kg'),
('47', 'Goldenglow', 'kg'),
('48', 'Golden Jerusalem', 'kg'),
('49', 'Gordaldo', 'kg'),
('5', 'Angel trumpet', 'kg'),
('50', 'Goose tongue', 'kg'),
('51', 'Grapefruit', 'kg'),
('52', 'Grapevine', 'kg'),
('53', 'Hemp dogbane', 'kg'),
('54', 'Hen plant ', 'kg'),
('55', 'Herb barbara', 'kg'),
('56', 'Hogweed', 'kg'),
('57', 'Indian posy', 'kg'),
('58', 'Inkberry ', 'kg'),
('59', 'Ironwood', 'kg'),
('6', 'Apple', 'kg'),
('60', 'Isle of Man cabbage', 'kg'),
('61', 'Itchweed', 'kg'),
('62', 'Jasmine', 'kg'),
('63', 'Jewel orchid', 'kg'),
('64', 'Jointed rush', 'kg'),
('65', 'Kousa', 'kg'),
('66', 'Kudzu', 'kg'),
('67', 'Kumarahou', 'kg'),
('68', 'Laceflower', 'kg'),
('69', 'Lace fern', 'kg'),
('7', 'Apricot', 'kg'),
('70', 'Lady\'s mantle', 'kg'),
('71', 'Lady\'s smock', 'kg'),
('72', 'Lamb\'s foot', 'kg'),
('73', 'Morelle verte', 'kg'),
('74', 'Mosquito plant', 'kg'),
('75', 'Mother-of-the-evening', 'kg'),
('76', 'Mountain mahogany', 'kg'),
('77', 'Native fuchsia', 'kg'),
('78', 'Necklace fern', 'kg'),
('79', 'Neem', 'kg'),
('8', 'Arfaj ', 'kg'),
('80', 'Nettle', 'kg'),
('81', 'Orange-root', 'kg'),
('82', 'Osage', 'kg'),
('83', 'Parsley', 'kg'),
('84', 'Parsnip', 'kg'),
('85', 'Pea', 'kg'),
('86', 'Peach', 'kg'),
('87', 'Peanut', 'kg'),
('88', 'Pear', 'kg'),
('89', 'Queen Anne\'s lace', 'kg'),
('9', 'Arizona sycamore', 'kg'),
('90', 'Quercitron', 'kg'),
('91', 'Red ink plant', 'kg'),
('92', 'Redweed', 'kg'),
('93', 'Rheumatism root', 'kg'),
('94', 'Rhubarb', 'kg'),
('95', 'Skunkweed', 'kg'),
('96', 'Snakeberry', 'kg'),
('97', 'Snowdrop', 'kg'),
('98', 'Tickleweed', 'kg'),
('99', 'Tobacco plant', 'kg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `_id` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `phoneNumber` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`_id`, `name`, `email`, `password`, `phoneNumber`) VALUES
('1', 'hao', 'haohao@email.comt', '123456789', '012-3456789'),
('10', 'Caleb Tan', 'calebT398@gmail.com', 'caleb%$@#', '014-7520136'),
('100', 'Wade Cheah', 'wadeeee393@gmail.com', 'wade0393cheah', '012-8543120'),
('11', 'Daniel Yap', 'danielll38@gmail.com', 'danielYaP28', '017-6320148'),
('12', 'Dylan Lai', 'dylanLAiiiiii@gmail.com', 'dylan9876', '013-6320785'),
('13', 'Daxton Chua', 'daxtonC@gmail.com', 'daxtonhaha123', '013-6354167'),
('14', 'Ethan Goh', 'ethanGoh586@gmail.com', 'ethann583', '019-3210754'),
('15', 'Evan Chin', 'evanChinchin@gmail.com', 'evanCC651', '013-8621476'),
('16', 'Fiona Toh', 'fionaaattt@gmail.com', 'fiona1249', '013-9643017'),
('17', 'Finn Leong', 'finnleong@gmail.com', 'finfinl129', '013-7513654'),
('18', 'Grayson Hay', 'grasonnn@gmail.com', 'grason2839', '019-8630124'),
('19', 'Gavin Teoh', 'gavinteoh39@gmail.com', '123gavinteoh', '017-3012497'),
('2', 'Alexander Lim', 'alexanderL@gmail.com', 'alexander1982', '012-5621465'),
('20', 'George Lim', 'georgelim@gmail.com', 'georgee293', '018-9630146'),
('21', 'Gael Chong', 'gaelchong@gmail.com', 'gaelll032', '015-3012497'),
('22', 'Henry Lim', 'henrylim@gmail.com', 'henryyyy123', '013-3246750'),
('23', 'Hayden Hin', 'haydennnn123@gmail.com', 'hayhayhin987', '016-9730198'),
('24', 'Hudson Kim', 'hudsonnnn345@gmail.com', 'kimhudson234', '014-9630127'),
('25', 'Harvey Liw', 'harveyyliw@gmail.com', 'haharvey398', '013-9762103'),
('26', 'Harrison Yeoh', 'harrisonnyy@gmail.com', 'harris2938', '012-7530156'),
('27', 'Isabella Chin', 'issa3984@gmail.com', 'issabellla198', '013-9762451'),
('28', 'Iris Teo', 'iris98u7@gmail.com', 'irisss128', '013-9874287'),
('29', 'Itzel Ng', 'itzelng@gmail.com', 'itzelll087', '014-9752103'),
('3', 'Aiden Kim', 'aidenKSJ@gmail.com', 'aiden9837', '013-9647520'),
('30', 'Jackson Wang', 'jackson628@gmail.com', 'jackjack3984', '012-9863124'),
('31', 'Joseph Peh', 'josephpeh@gmail.com', 'josephhh988', '012-7654320'),
('32', 'Julian Lim', 'julianlim@gmail.com', 'jujulian293', '016-9834657'),
('33', 'Joshua Goh', 'joshuagoh@gmail.com', 'gogojosh2938', '014-9876132'),
('34', 'Jack Lee', 'jacklee98@gmail.com', 'jackjack980', '019-8630124'),
('35', 'John Wong', 'johnwong@gmail.com', 'johnw987', '015-9634120'),
('36', 'Jayden Lim', 'jaydenL@gmail.com', 'jayden65225', '017-9634572'),
('37', 'Josiah Chong', 'josiahhhh@gmail.com', 'josiahhahaha384', '013-9675210'),
('38', 'Jonathan Wong', 'johnathanwww@gmail.com', 'wongjohnathan2938i', '015-7530165'),
('39', 'Kevin Chew', 'kevinshew@gmail.com', 'kevinnn392', '014-7620132'),
('4', 'Anthony Ng', 'antNg@gmail.com', 'anthong920', '016-8520143'),
('40', 'Karter Yeoh', 'karteryeoh@gmail.com', 'karter938', '013-9420132'),
('41', 'Kaleb Goh', 'kalebgg@gmail.com', 'kalebbbg29', '017-8452013'),
('42', 'Lucas Lok', 'lucasLok@gmail.com', 'lucassssslock839', '012-6432015'),
('43', 'Levi Liau', 'liaulevi@gmail.com', 'lvliau3984', '019-8632457'),
('44', 'Luna Lu', 'lunalu@gmail.com', 'lulunana394', '014-7652013'),
('45', 'Luke Toh', 'luketoh@gmail.com', 'lukeke298', '017-9863201'),
('46', 'Leo Kim', 'leokim@gmail.com', 'kimkimleo293', '016-9875430'),
('47', 'Mia Woo', 'miawoo@gmail.com', 'miawmiaw384', '015-9863420'),
('48', 'Michael Jo', 'michaeljoo@gmail.com', 'michaeljojo398', '013-9765201'),
('49', 'Matthew Lim', 'matthewLim@gmail.com', 'matmathew293', '019-8634201'),
('5', 'Aaron Tan', 'aarontan999@gmail.com', 'aaron12349', '013-6540324'),
('50', 'Madison Hin', 'madisonH@gmail.com', 'madisonnnnnhin38', '014-8657420'),
('51', 'Nathan See', 'nathansee@gmail.com', 'seenathan394', '013-9765012'),
('52', 'Natalie Lim', 'natalieeeeLim@gmail.com', 'natalielim394', '019-7851342'),
('53', 'Nicholas Kan', 'nicholaskan@gmail.com', 'nicholassss099', '013-9765241'),
('54', 'Olivia Chen', 'oliviachen@gmail.com', 'chenolivia390', '014-7830125'),
('55', 'Owen Poh', 'owenpoh@gmail.com', 'pohowen390', '016-9765412'),
('56', 'Oakley Lee', 'oakleylee@gmail.com', 'oakleeeeee@%$', '013-9685475'),
('57', 'Oscar Chung', 'oscarchung@gmail.com', 'oscarchung4958', '013-9875410'),
('58', 'Omar Harman', 'omarharman@gmail.com', 'omaromar938', '015-9765201'),
('59', 'Orion Ong', 'orionong@gmail.com', 'orionongong098', '015-7854320'),
('6', 'Benjamin Chin', 'ben4785@gmail.com', 'benj@min123', '015-9861345'),
('60', 'Parker Jang', 'parkerjang@gmail.com', 'parker9498jang', '012-8975412'),
('61', 'Phoenix Peh', 'phoenixpeh@gmail.com', 'pehphoenix383', '014-8765412'),
('62', 'Piper Tee', 'pipertee@gmail.com', 'pipertee3093', '017-5413278'),
('63', 'Peyton Tan', 'peytontan@gmail.com', 'tantanpeyton298', '015-7654032'),
('64', 'Patrick Jim', 'jimpatrick@gmail.com', 'patrick930', '013-9874026'),
('65', 'Peter Lim', 'peterlim3948@gmail.com', 'limpeter938', '017-9762410'),
('66', 'Quincy Chew', 'quincychew@gmail.com', 'chewchewquin1982', '014-7854321'),
('67', 'Quintin Tan', 'quintin398@gmail.com', 'quintinnn39i84', '013-8631204'),
('68', 'Quill Chin', 'quillchin@gmail.com', 'quilll398', '016-7301246'),
('69', 'Quinton Cheng', 'quintonCheng@gmail.com', 'quinton3838', '016-8520132'),
('7', 'Bryson Teo', 'brysonnn2938@gmail.com', 'brysonnn!!@#', '016-9230123'),
('70', 'Quinlan Lim', 'limquinlan@gmail.com', 'limlimquin398', '014-7865012'),
('71', 'Robert Chen', 'robertchen@gmail.com', 'chenrobert408', '015-7620136'),
('72', 'Rowan Wong', 'rowanwong@gmail.com', 'rowanwwww209', '013-8742103'),
('73', 'Richard Lee', 'richardlee@gmail.com', 'leerichar3949', '013-9874620'),
('74', 'Ryder Lau', 'ryder394@gmail.com', 'ryder4084lau', '016-9730145'),
('75', 'Remington Tan ', 'remingtontan3048@gmail.com', 'remington483tantnn', '014-9720136'),
('76', 'Riley Ma', 'rileyma@gmail.com', 'rileyyy595', '016-7432015'),
('77', 'Sophie Soh', 'sophiesoh@gmail.com', 'sohsohsop@#', '016-8512013'),
('78', 'Sophia Koh', 'sophiakoh@gmail.com', 'kohsophia404', '016-3452103'),
('79', 'Scarlett Ku', 'scarlett3093@gmail.com', 'scarletttttku039', '012-6420132'),
('8', 'Camila Chan', 'camilaaa1890@gmail.com', 'camilaCCC2938', '012-9643275'),
('80', 'Samuel Lim', 'samLim3094@gmail.com', 'samuellim393', '016-4501265'),
('81', 'Sienna Yoo', 'yoosienna@gmail.com', 'siennaaaa304', '012-9872013'),
('82', 'Summer Leong', 'summer4i84@gmail.com', 'summerlll3oi4', '013-6021327'),
('83', 'Sofia Gan', 'sofia393@gmail.com', 'sofia098gan', '014-7520432'),
('84', 'Sebastian Low', 'sebastianlow@gmail.com', 'bastian4094', '013-8752410'),
('85', 'Thomas Lim', 'limlimthomas@gmail.com', 'thommm3093', '012-8574123'),
('86', 'Tyler Tee', 'teetyler@gmail.com', 'tetettyler40049', '013-9874125'),
('87', 'Taylor Lam', 'taylor9487@gmail.com', '0452taylor4093', '016-9713002'),
('88', 'Tritan Kim', 'kimtritan@gmail.com', 'tritannn404', '014-7854102'),
('89', 'Timothy Lim', 'timothyL@gmail.com', 'tim484Lim', '013-9764201'),
('9', 'Charles Chong', 'charleschong@gmail.com', 'charleS2938', '016-7520136'),
('90', 'Uriah Eng', 'uriahh84@gmail.com', 'uriahhheng494', '016-9754107'),
('91', 'Victoria Bong', 'victoriabong@gmail.com', 'vicvicbong309', '016-3412075'),
('92', 'Violet Teo', 'violetteo@gmail.com', 'viovio5893', '016-7854120'),
('93', 'Valentina Gan', 'valengan@gmail.com', 'valennn9494', '016-8520147'),
('94', 'Vincent Yeow', 'yeowvincent@gmail.com', 'vinccccyeow049', '016-3698741'),
('95', 'Vivian Goh', 'gohvivian@gmail.com', 'gohgohviv404', '013-5874123'),
('96', 'Victor Yang', 'yangvictor@gmail.com', 'yangyangvic304', '014-8574632'),
('97', 'Valerie Hin', 'valeriehin@gmail.com', 'valeriehihihih303', '013-6541230'),
('98', 'William Tan', 'williamLim@gmail.com', 'williammmllin40448', '013-6541207'),
('99', 'Walter Jung', 'walterjjj@gmail.com', 'wal39394', '013-8541072');


create procedure setNextAction (farm_id varchar(50),field_no int(50), row_no int(50))
BEGIN
declare activity varchar(50) default "";
declare next_activity varchar(50) default "sowing";
declare act_date date default CURRENT_DATE;
declare plant varchar(50) default "";
declare pl_unit varchar(50) default "";
select action into activity from activities where farmId=farm_id and field=field_no and row=row_no order by date desc limit 1;
select date into act_date from activities where farmId=farm_id and field=field_no and row=row_no order by date desc limit 1;
select type into plant from activities where farmId=farm_id and field=field_no and row=row_no and (action="sowing" or action="harvest") order by date desc limit 1;
select unit into pl_unit from activities where farmId=farm_id and field=field_no and row=row_no and (action="sowing" or action="harvest") order by date desc limit 1;
if activity = "sales" then set next_activity = "sowing";
elseif activity = "sowing" then set next_activity = "pesticide";
elseif activity = "pesticide" then set next_activity = "fertilizer";
elseif activity = "fertilizer" then set next_activity = "harvest";
elseif activity = "harvest" then set next_activity = "sales";
end if;
select next_activity, act_date, plant, pl_unit;
end

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`_id`),
  ADD KEY `farms_id_FK` (`farmId`),
  ADD KEY `users_id_FK` (`userId`);

--
-- Indexes for table `farmfertilizers`
--
ALTER TABLE `farmfertilizers`
  ADD PRIMARY KEY (`farmId`,`fertilizerId`),
  ADD KEY `fertilizers_farms_FK` (`fertilizerId`);

--
-- Indexes for table `farmpesticide`
--
ALTER TABLE `farmpesticide`
  ADD PRIMARY KEY (`farmId`,`pesticideId`),
  ADD KEY `pesticides_farms_FK` (`pesticideId`);

--
-- Indexes for table `farmplants`
--
ALTER TABLE `farmplants`
  ADD PRIMARY KEY (`farmId`,`plantId`),
  ADD KEY `plants_farms_FK` (`plantId`);

--
-- Indexes for table `farms`
--
ALTER TABLE `farms`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `farmusers`
--
ALTER TABLE `farmusers`
  ADD PRIMARY KEY (`farmId`,`userId`),
  ADD KEY `users_farms_FK` (`userId`);

--
-- Indexes for table `fertilizers`
--
ALTER TABLE `fertilizers`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `pesticide`
--
ALTER TABLE `pesticide`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `plants`
--
ALTER TABLE `plants`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activities`
--
ALTER TABLE `activities`
  ADD CONSTRAINT `farms_id_FK` FOREIGN KEY (`farmId`) REFERENCES `farms` (`_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `users_id_FK` FOREIGN KEY (`userId`) REFERENCES `users` (`_id`) ON UPDATE CASCADE;

--
-- Constraints for table `farmfertilizers`
--
ALTER TABLE `farmfertilizers`
  ADD CONSTRAINT `farms_fertilizers_FK` FOREIGN KEY (`farmId`) REFERENCES `farms` (`_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fertilizers_farms_FK` FOREIGN KEY (`fertilizerId`) REFERENCES `fertilizers` (`_id`) ON UPDATE CASCADE;

--
-- Constraints for table `farmpesticide`
--
ALTER TABLE `farmpesticide`
  ADD CONSTRAINT `farms_pesticides_FK` FOREIGN KEY (`farmId`) REFERENCES `farms` (`_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `pesticides_farms_FK` FOREIGN KEY (`pesticideId`) REFERENCES `pesticide` (`_id`) ON UPDATE CASCADE;

--
-- Constraints for table `farmplants`
--
ALTER TABLE `farmplants`
  ADD CONSTRAINT `farms_plants_FK` FOREIGN KEY (`farmId`) REFERENCES `farms` (`_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `plants_farms_FK` FOREIGN KEY (`plantId`) REFERENCES `plants` (`_id`) ON UPDATE CASCADE;

--
-- Constraints for table `farmusers`
--
ALTER TABLE `farmusers`
  ADD CONSTRAINT `farms_users_FK` FOREIGN KEY (`farmId`) REFERENCES `farms` (`_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `users_farms_FK` FOREIGN KEY (`userId`) REFERENCES `users` (`_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
