-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2023 a las 06:36:02
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmaceutica_eunice`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL,
  `categoria` varchar(70) NOT NULL,
  `subcategoria` varchar(70) NOT NULL,
  `descripcion` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `categoria`, `subcategoria`, `descripcion`) VALUES
(1, 'Medicamentos', 'Dolor', 'Analgésicos'),
(2, 'Medicamentos', 'Dolor', 'Analgésicos Infantil'),
(3, 'Medicamentos', 'Estomacal', 'Antiácidos'),
(4, 'Medicamentos', 'Dermatológicos', 'Cicatrizantes'),
(5, 'Dermocosmeticos', 'Capilar', 'Anticaída'),
(6, 'Bebés', 'Accesorios', 'Chupones'),
(7, 'Dermocosmeticos', 'Capilar', 'Anticaspa'),
(8, 'Cuidado Personal', 'Cuidado de la piel', 'Corporal'),
(9, 'Medicamentos', 'Estomacal', 'Antidiarreicos'),
(10, 'Medicamentos', 'Dolor', 'Colicos'),
(12, 'Bebés', 'Alimentos', 'Cereales'),
(13, 'Bebés', 'Alimentos', 'Jugos'),
(15, 'Bebés', 'Alimentos', 'Papillas'),
(20, 'Alimentos y Bebidas', 'Botanas', 'Palomitas'),
(22, 'Dermocosmeticos', 'Capilar', 'Antícaspa'),
(24, 'Medicamentos', 'Estomacal', 'Digestivos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuento`
--

CREATE TABLE `descuento` (
  `idDescuento` int(11) NOT NULL,
  `nombreProducto` varchar(255) NOT NULL,
  `precio` double NOT NULL,
  `descuento` int(11) DEFAULT NULL,
  `precioDescuento` double DEFAULT NULL,
  `fechaInicio` varchar(50) DEFAULT NULL,
  `fechaFin` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `descuento`
--

INSERT INTO `descuento` (`idDescuento`, `nombreProducto`, `precio`, `descuento`, `precioDescuento`, `fechaInicio`, `fechaFin`) VALUES
(1, 'Paracetamol 500 MG', 18, 5, 17.1, '2023-11-08', '2023-11-15'),
(2, 'Ibuprofeno 400 MG', 41.5, NULL, NULL, NULL, NULL),
(3, 'Melox Plus cereza', 90, 5, 85.5, '2023-11-22', '2023-11-29'),
(4, 'Febrax Analgésicos', 201, 15, 170.85, '2023-11-08', '2023-11-15'),
(5, 'La Roche Posay Kerium Shampoo para Caspa Seca Cuidado Capilar', 655, 15, 556.75, '2023-11-09', '2023-11-16'),
(6, 'Buscapina Fem', 162, 10, 145.8, '2023-11-20', '2023-11-27'),
(7, 'X Ray Gel Alivia Inflamación y Dolor', 122, 5, 115.9, '2023-11-22', '2023-11-29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `stock`, `descripcion`, `idCategoria`) VALUES
(1, 'paracetamol', 35.00, 20, 'Para el dolor', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion`
--

CREATE TABLE `promocion` (
  `idPromocion` int(11) NOT NULL,
  `subcategoria` varchar(50) NOT NULL,
  `tipo` varchar(80) NOT NULL,
  `promocion` varchar(20) NOT NULL,
  `cantidadMinima` int(11) NOT NULL,
  `fechaInicio` varchar(50) NOT NULL,
  `fechaFin` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `promocion`
--

INSERT INTO `promocion` (`idPromocion`, `subcategoria`, `tipo`, `promocion`, `cantidadMinima`, `fechaInicio`, `fechaFin`) VALUES
(1, 'Dolor', 'Antiacidos', '2x1', 2, '2023-11-21', '2023-12-05'),
(2, 'Dolor', 'analgésicos', '2x1', 2, '2023-11-22', '2023-11-29'),
(3, 'Bebidas', 'Refrescos', '2x1', 2, '2023-11-22', '2023-11-25'),
(4, 'Accesorios para bebé', 'Chupones', '3x2', 3, '2023-11-29', '2023-12-07'),
(5, 'Aparatos de medición', 'Termometros', '2x1', 2, '2023-11-22', '2023-11-24'),
(6, 'Vitamínicos', 'Antioxidantes', '3x2', 3, '2023-12-06', '2023-12-09'),
(7, 'Cosméticos', 'Labiales', '4x2', 4, '2023-11-21', '2023-11-29'),
(8, 'Capilar', 'Anticaspa', '2x1', 2, '2023-11-22', '2023-11-27'),
(9, 'Bienestar Sexual', 'Preservativos', '2x1', 2, '2023-11-22', '2023-11-28'),
(10, 'Cereales y Galletas', 'Galletas', '2x1', 2, '2023-11-22', '2023-11-29');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `descuento`
--
ALTER TABLE `descuento`
  ADD PRIMARY KEY (`idDescuento`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCategoria` (`idCategoria`);

--
-- Indices de la tabla `promocion`
--
ALTER TABLE `promocion`
  ADD PRIMARY KEY (`idPromocion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `descuento`
--
ALTER TABLE `descuento`
  MODIFY `idDescuento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `promocion`
--
ALTER TABLE `promocion`
  MODIFY `idPromocion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
