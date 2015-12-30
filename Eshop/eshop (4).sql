-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Út 29.Dec 2015, 16:41
-- Verzia serveru: 10.1.8-MariaDB
-- Verzia PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `eshop`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `login_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `order`
--

INSERT INTO `order` (`order_id`, `login_name`) VALUES
(1, 'black'),
(3, 'black'),
(5, 'gabi'),
(6, 'gabi'),
(4, 'green'),
(9, 'green'),
(10, 'green'),
(7, 'jones'),
(8, 'jones');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `order_product`
--

CREATE TABLE `order_product` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `order_product`
--

INSERT INTO `order_product` (`order_id`, `product_id`) VALUES
(1, 1),
(1, 5),
(1, 8),
(3, 8),
(3, 9),
(3, 10),
(4, 9),
(4, 10),
(4, 11),
(4, 12),
(5, 14),
(6, 12),
(6, 14),
(7, 4),
(7, 5),
(8, 1),
(8, 10),
(9, 8),
(10, 11);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `product`
--

INSERT INTO `product` (`product_id`, `name`, `description`, `price`) VALUES
(1, 'Adidas Boost 5', 'Trail Running Shoes ', 51.05),
(4, 'Saucony Triumph ISO ', 'Running Shoes', 106.43),
(5, 'Adidas Kanadia 7', 'Trail Running Shoes ', 51.05),
(8, 'salomon', 'a1', 11.55),
(9, 'salomon', 'sense', 11.55),
(10, 'Adidas Boost 2', 'Trail Running Shoes ', 51.99),
(11, 'ertyui', 'asd', 11.99),
(12, 'Adidas Boost 2', 'Trail Running Shoes ', 51.99),
(14, 'Hupla', 'new', 22.33),
(15, 'Puma', 'X1', 12.45),
(17, 'Mizuno', 'A123456', 22.55);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `user`
--

CREATE TABLE `user` (
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Sťahujem dáta pre tabuľku `user`
--

INSERT INTO `user` (`login`, `password`, `name`, `admin`) VALUES
('black', '456', 'Jane Black', 0),
('gabi', '123', 'Gabe First', 0),
('green', '789', 'Eva Green', 0),
('jones', '123', 'James Jones', 1),
('kenedy', '123', 'John Kenedy', 0),
('popeye', '111', 'Popeye', 0),
('red', '159', 'Joe Red', 0);

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `login_name` (`login_name`);

--
-- Indexy pre tabuľku `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexy pre tabuľku `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexy pre tabuľku `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`login`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- Obmedzenie pre exportované tabuľky
--

--
-- Obmedzenie pre tabuľku `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`login_name`) REFERENCES `user` (`login`);

--
-- Obmedzenie pre tabuľku `order_product`
--
ALTER TABLE `order_product`
  ADD CONSTRAINT `order_product_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  ADD CONSTRAINT `order_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
