-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2024 a las 17:22:44
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

CREATE DATABASE raftrush;
USE raftrush;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `raftrush`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `dificultad` varchar(50) DEFAULT NULL,
  `precio` decimal(5,2) DEFAULT NULL,
  `idTipo` int(11) NOT NULL,
  `idCentro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`id`, `nombre`, `descripcion`, `dificultad`, `precio`, `idTipo`, `idCentro`) VALUES
(1, 'Rafting en el Río', 'Descenso en balsa por el río', 'Intermedio', 50.00, 8, 1),
(2, 'Escalada en Roca', 'Escalada en paredes de roca natural', 'Avanzado', 75.00, 1, 2),
(3, 'Tiro con Arco', 'Práctica de tiro con arco', 'Fácil', 30.00, 2, 3),
(4, 'Circuito de Tirolinas', 'Recorrido de tirolinas entre árboles', 'Intermedio', 45.00, 4, 4),
(5, 'Canoa en el Lago', 'Paseo en canoa por el lago', 'Fácil', 25.00, 7, 5),
(6, 'Paintball en el Bosque', 'Juego de estrategia y combate con paintball', 'Intermedio', 60.00, 9, 6),
(7, 'Orientación con Brújula', 'Competencia de orientación utilizando brújula', 'Intermedio', 20.00, 10, 7),
(8, 'Senderismo Nocturno', 'Caminata nocturna guiada por el parque', 'Fácil', 40.00, 3, 8),
(9, 'Carrera de Aventura', 'Competencia de resistencia y habilidades en el parque', 'Avanzado', 100.00, 5, 9),
(10, 'Aventura en Familia', 'Diversas actividades pensadas para toda la familia', 'Fácil', 50.00, 10, 10),
(11, 'Kayak en Rápidos', 'Descenso en kayak por rápidos', 'Avanzado', 80.00, 8, 1),
(12, 'Puente Colgante', 'Atravesar un puente colgante a gran altura', 'Intermedio', 60.00, 5, 2),
(13, 'Barranquismo', 'Descenso por barrancos y cañones', 'Avanzado', 90.00, 4, 3),
(14, 'Vía Ferrata', 'Recorrido de escalada con equipamiento fijo', 'Intermedio', 70.00, 6, 4),
(15, 'Paseo a Caballo', 'Ruta guiada a caballo por el parque', 'Fácil', 50.00, 3, 5),
(16, 'Escalada en Hielo', 'Escalada en paredes de hielo', 'Avanzado', 100.00, 5, 6),
(17, 'Tiro con Ballesta', 'Práctica de tiro con ballesta', 'Intermedio', 40.00, 2, 7),
(18, 'Caminata Fotográfica', 'Senderismo con enfoque en fotografía', 'Fácil', 30.00, 3, 8),
(19, 'Supervivencia en la Selva', 'Curso de técnicas de supervivencia', 'Avanzado', 120.00, 10, 9),
(20, 'Senderismo Nocturno', 'Senderismo nocturno con linternas', 'Intermedio', 45.00, 3, 10),
(21, 'Pesca Deportiva', 'Pesca guiada en el lago', 'Fácil', 35.00, 10, 1),
(22, 'Parapente', 'Vuelo en parapente desde la montaña', 'Avanzado', 150.00, 4, 2),
(23, 'Ciclismo en Cuevas', 'Exploración de cuevas submarinas', 'Avanzado', 200.00, 6, 3),
(24, 'Slackline', 'Equilibrio sobre una cuerda floja', 'Intermedio', 25.00, 4, 4),
(25, 'Rappel en Cascada', 'Descenso con cuerda por una cascada', 'Avanzado', 85.00, 5, 5),
(26, 'Paseo en Globo', 'Vuelo en globo aerostático', 'Fácil', 200.00, 3, 6),
(27, 'Ciclismo de Montaña', 'Ruta en bicicleta por caminos de montaña', 'Intermedio', 50.00, 6, 7),
(28, 'Escalada en Muro', 'Escalada en muro artificial', 'Fácil', 20.00, 1, 8),
(29, 'Rafting Familiar', 'Descenso en balsa por el río para familias', 'Fácil', 40.00, 8, 9),
(30, 'Orientación en la Nieve', 'Competencia de orientación en la nieve', 'Intermedio', 60.00, 10, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro`
--

CREATE TABLE `centro` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  `presupuesto` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `centro`
--

INSERT INTO `centro` (`id`, `nombre`, `localidad`, `presupuesto`) VALUES
(1, 'Parque Multiaventura Sierra Norte', 'Madrid', 500000.00),
(2, 'Parque de Aventura Montaña Mágica', 'Barcelona', 750000.00),
(3, 'Parque Multiaventura Costa Blanca', 'Valencia', 600000.00),
(4, 'Parque Aventura y Naturaleza', 'Sevilla', 450000.00),
(5, 'Parque Multiaventura Euzkadi', 'Bilbao', 800000.00),
(6, 'Parque de Aventura del Ebro', 'Zaragoza', 550000.00),
(7, 'Parque Multiaventura del Segura', 'Murcia', 300000.00),
(8, 'Parque Aventura Campo Grande', 'Valladolid', 400000.00),
(9, 'Parque Multiaventura Atlántico', 'La Coruña', 650000.00),
(10, 'Parque Aventura Sierra Nevada', 'Granada', 700000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `nif` varchar(9) NOT NULL,
  `contrasenya` varchar(25) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`nif`, `contrasenya`, `telefono`, `nombre`, `edad`) VALUES
('02414134P', 'abc123456', '098765432', 'Marina Sánchez', 37),
('97687928M', 'abc123456', '109876543', 'Luisa Pérez', 39),
('14916841F', 'abc123456', '123456789', 'María García', 35),
('21930062E', 'abc123456', '210987654', 'Pablo Martín', 38),
('50853687C', 'abc123456', '210987654', 'Antonio Gómez', 41),
('86212533T', 'abc123456', '234567890', 'Juan López', 28),
('15264188P', 'abc123456', '321098765', 'Carmen López', 42),
('59936311J', 'abc123456', '345678901', 'Laura Martínez', 40),
('80670835Z', 'abc123456', '432109876', 'Javier García', 36),
('96340970L', 'abc123456', '456789012', 'Carlos Rodríguez', 25),
('57079988Z', 'abc123456', '543210987', 'Sofía González', 29),
('38947676M', 'abc123456', '567890123', 'Ana Sánchez', 30),
('06574310J', 'abc123456', '654321098', 'Lucía Torres', 32),
('49097274W', 'abc123456', '678901234', 'David Pérez', 33),
('45562161N', 'abc123456', '765432109', 'Pedro Díaz', 31),
('15822136E', 'abc123456', '789012345', 'Sara Gómez', 45),
('52063074K', 'abc123456', '876543210', 'Isabel Ruiz', 36),
('40488245B', 'abc123456', '890123456', 'Miguel Ruiz', 50),
('66920006B', 'abc123456', '901234567', 'Elena Fernández', 27),
('06826215E', 'abc123456', '987654321', 'Sergio Martínez', 44);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entregaproveedormaterial`
--

CREATE TABLE `entregaproveedormaterial` (
  `fechaEntrega` date NOT NULL,
  `idProv` int(11) NOT NULL,
  `codMaterial` int(11) NOT NULL,
  `idCentro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entregaproveedormaterial`
--

INSERT INTO `entregaproveedormaterial` (`fechaEntrega`, `idProv`, `codMaterial`, `idCentro`) VALUES
('2024-05-01', 1, 1, 3),
('2024-05-02', 2, 2, 5),
('2024-05-03', 3, 3, 2),
('2024-05-04', 4, 4, 1),
('2024-05-05', 5, 5, 6),
('2024-05-06', 6, 6, 7),
('2024-05-07', 7, 7, 8),
('2024-05-08', 8, 8, 2),
('2024-05-09', 9, 9, 4),
('2024-05-10', 10, 10, 10),
('2024-05-11', 9, 11, 3),
('2024-05-12', 8, 12, 1),
('2024-05-13', 7, 13, 9),
('2024-05-14', 6, 14, 5),
('2024-05-15', 5, 15, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material`
--

CREATE TABLE `material` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` decimal(5,2) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `idCentro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `material`
--

INSERT INTO `material` (`codigo`, `nombre`, `precio`, `cantidad`, `idCentro`) VALUES
(1, 'Arco Compuesto', 150.00, 5, 3),
(2, 'Canoa Inflable', 200.00, 12, 5),
(3, 'Casco de Escalada', 50.00, 8, 2),
(4, 'Chaleco Salvavidas', 40.00, 5, 1),
(5, 'Marcadora de Paintball', 120.00, 5, 6),
(6, 'Brújula Profesional', 30.00, 2, 7),
(7, 'Linterna de Cabeza', 25.00, 3, 8),
(8, 'Cuerda de Escalada', 80.00, 3, 2),
(9, 'Botas de Montaña', 100.00, 12, 4),
(10, 'Ropa de Aventura', 75.00, 10, 10),
(11, 'Gafas de Sol Deportivas', 45.00, 18, 3),
(12, 'Guantes de Escalada', 35.00, 25, 1),
(13, 'Tienda de Campaña', 300.00, 5, 9),
(14, 'Kit de Supervivencia', 60.00, 20, 5),
(15, 'Raquetas de Nieve', 110.00, 7, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `nombre`, `telefono`, `email`) VALUES
(1, 'Aventuras Extremas S.A.', '123-456-7890', 'info@aventurasextremas.com'),
(2, 'Equipamiento Outdoor Ltda.', '234-567-8901', 'ventas@equipamientooutdoor.com'),
(3, 'Suministros de Aventura y Más', '345-678-9012', 'info@aventurasyMas.com'),
(4, 'Adventure Gear Corp.', '456-789-0123', 'sales@adventuregearcorp.com'),
(5, 'Equipos de Exploración y Deportes', '567-890-1234', 'info@exploraydeportes.com'),
(6, 'Suministros de Aventura Extrema', '678-901-2345', 'ventas@aventuraextrema.com'),
(7, 'Proveedores de Equipamiento Deportivo', '789-012-3456', 'contacto@equipamientodeportivo.com'),
(8, 'Aventura Total S.R.L.', '890-123-4567', 'info@aventuratotal.com'),
(9, 'Equipos y Servicios de Aventura', '901-234-5678', 'ventas@equiposyaventura.com'),
(10, 'Proveedores de Equipamiento de Aventura', '012-345-6789', 'info@equipamientodeaventura.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservaclienteactividad`
--

CREATE TABLE `reservaclienteactividad` (
  `fechaDeReserva` date NOT NULL,
  `nifCli` varchar(9) NOT NULL,
  `idActividad` int(11) NOT NULL,
  `idActividadCentro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservaclienteactividad`
--

INSERT INTO `reservaclienteactividad` (`fechaDeReserva`, `nifCli`, `idActividad`, `idActividadCentro`) VALUES
('2024-05-01', '02414134P', 1, 1),
('2024-05-02', '97687928M', 2, 2),
('2024-05-03', '14916841F', 3, 3),
('2024-05-04', '21930062E', 4, 4),
('2024-05-05', '50853687C', 5, 5),
('2024-05-06', '86212533T', 6, 6),
('2024-05-07', '15264188P', 7, 7),
('2024-05-08', '59936311J', 8, 8),
('2024-05-09', '80670835Z', 9, 9),
('2024-05-10', '96340970L', 10, 10),
('2024-05-11', '57079988Z', 11, 1),
('2024-05-12', '38947676M', 12, 2),
('2024-05-13', '06574310J', 13, 3),
('2024-05-14', '49097274W', 14, 4),
('2024-05-15', '45562161N', 15, 5),
('2024-05-16', '15822136E', 16, 6),
('2024-05-17', '52063074K', 17, 7),
('2024-05-18', '40488245B', 18, 8),
('2024-05-19', '66920006B', 19, 9),
('2024-05-20', '06826215E', 20, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE `tipo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`id`, `nombre`) VALUES
(1, 'Escalada'),
(2, 'Tiro con Arco'),
(3, 'Senderismo'),
(4, 'Tirolina'),
(5, 'Rappel'),
(6, 'Ciclismo'),
(7, 'Canoa'),
(8, 'Rafting'),
(9, 'Paintball'),
(10, 'Orientación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `nif` varchar(9) NOT NULL,
  `contrasenya` varchar(25) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `salario` decimal(8,2) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `idCentro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`nif`, `contrasenya`, `nombre`, `apellido`, `salario`, `edad`, `idCentro`) VALUES
('49909900S', 'abc123456', 'Sara', 'Díaz', 2800.00, 30, 1),
('34713246J', 'abc123456', 'Roberto', 'Herrero', 3200.00, 30, 2),
('01150545Q', 'abc123456', 'Alberto', 'Vidal', 3000.00, 38, 10),
('21521574Z', 'abc123456', 'Carlos', 'Muñoz', 2900.00, 35, 2),
('20069994X', 'abc123456', 'Ana', 'Herrero', 3200.00, 30, 3),
('30306733R', 'abc123456', 'Celia', 'Gutiérrez', 3000.00, 38, 3),
('69545302H', 'abc123456', 'Rosa', 'Torres', 2800.00, 33, 3),
('35648503K', 'abc123456', 'Juan', 'García', 2500.00, 35, 1),
('74169544H', 'abc123456', 'Luis', 'Gutiérrez', 3000.00, 38, 4),
('28576408N', 'abc123456', 'Pablo', 'Ramos', 2800.00, 33, 4),
('60871725V', 'abc123456', 'Sergio', 'Ruiz', 2900.00, 34, 4),
('88293258F', 'abc123456', 'Daniel', 'García', 2700.00, 29, 4),
('84380321Z', 'abc123456', 'María', 'López', 2800.00, 28, 2),
('49305919Z', 'abc123456', 'Sandra', 'Navarro', 2700.00, 29, 5),
('76472759J', 'abc123456', 'Luisa', 'González', 2800.00, 27, 5),
('94702995N', 'abc123456', 'Eva', 'Fernández', 2900.00, 36, 5),
('17822351L', 'abc123456', 'Pedro', 'Martínez', 3000.00, 40, 3),
('10241857A', 'abc123456', 'Manuel', 'López', 2900.00, 36, 6),
('63899574P', 'abc123456', 'Javier', 'Martínez', 3000.00, 39, 6),
('73373894F', 'abc123456', 'Diego', 'Martín', 2800.00, 31, 6),
('69194102M', 'abc123456', 'Ana', 'Fernández', 2600.00, 33, 1),
('15956212P', 'abc123456', 'Elena', 'Martínez', 2800.00, 31, 7),
('05882500C', 'abc123456', 'Carmen', 'Sanz', 2600.00, 32, 7),
('48617263R', 'abc123456', 'Cristina', 'Alonso', 3000.00, 40, 7),
('03175972V', 'abc123456', 'David', 'Sánchez', 2700.00, 29, 2),
('16036461X', 'abc123456', 'Alejandro', 'Sánchez', 3000.00, 40, 8),
('99659810M', 'abc123456', 'Pablo', 'Jiménez', 2700.00, 28, 8),
('83309516C', 'abc123456', 'Héctor', 'Gómez', 2600.00, 34, 8),
('18015601T', 'abc123456', 'Elena', 'Gómez', 2900.00, 37, 3),
('14775617A', 'abc123456', 'Sara', 'Gómez', 2600.00, 34, 9),
('84780481C', 'abc123456', 'Sonia', 'Ortega', 3100.00, 41, 9),
('40527015A', 'abc123456', 'Isabel', 'Díaz', 2700.00, 29, 9),
('89796346E', 'abc123456', 'Miguel', 'Rodríguez', 3200.00, 42, 1),
('27826051F', 'abc123456', 'Juan', 'Díaz', 2700.00, 29, 10),
('00542815S', 'abc123456', 'Jorge', 'López', 2900.00, 35, 10),
('35700851K', 'abc123456', 'Álvaro', 'Prieto', 3100.00, 41, 10),
('99590136K', 'abc123456', 'Laura', 'Pérez', 2700.00, 31, 2),
('75707041B', 'abc123456', 'Carlos', 'Hernández', 3100.00, 36, 3),
('07608515T', 'abc123456', 'Carlos', 'Hernández', 3100.00, 36, 3),
('70779638J', 'abc123456', 'María', 'Prieto', 3100.00, 41, 1),
('67998175D', 'abc123456', 'Laura', 'Muñoz', 2900.00, 35, 1),
('66463379A', 'abc123456', 'Marina', 'Ramírez', 3200.00, 30, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`id`,`idCentro`),
  ADD KEY `idCentro` (`idCentro`),
  ADD KEY `idTipo` (`idTipo`);

--
-- Indices de la tabla `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`nif`);

--
-- Indices de la tabla `entregaproveedormaterial`
--
ALTER TABLE `entregaproveedormaterial`
  ADD PRIMARY KEY (`fechaEntrega`,`idProv`,`codMaterial`,`idCentro`),
  ADD KEY `idProv` (`idProv`),
  ADD KEY `codMaterial` (`codMaterial`,`idCentro`);

--
-- Indices de la tabla `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`codigo`,`idCentro`),
  ADD KEY `idCentro` (`idCentro`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reservaclienteactividad`
--
ALTER TABLE `reservaclienteactividad`
  ADD PRIMARY KEY (`fechaDeReserva`,`nifCli`,`idActividad`,`idActividadCentro`),
  ADD KEY `nifCli` (`nifCli`),
  ADD KEY `idActividad` (`idActividad`,`idActividadCentro`);

--
-- Indices de la tabla `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`nif`),
  ADD KEY `idCentro` (`idCentro`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE `actividad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `centro`
--
ALTER TABLE `centro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `material`
--
ALTER TABLE `material`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tipo`
--
ALTER TABLE `tipo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`idCentro`) REFERENCES `centro` (`id`),
  ADD CONSTRAINT `actividad_ibfk_2` FOREIGN KEY (`idTipo`) REFERENCES `tipo` (`id`);

--
-- Filtros para la tabla `entregaproveedormaterial`
--
ALTER TABLE `entregaproveedormaterial`
  ADD CONSTRAINT `entregaproveedormaterial_ibfk_1` FOREIGN KEY (`idProv`) REFERENCES `proveedor` (`id`),
  ADD CONSTRAINT `entregaproveedormaterial_ibfk_2` FOREIGN KEY (`codMaterial`,`idCentro`) REFERENCES `material` (`codigo`, `idCentro`);

--
-- Filtros para la tabla `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `material_ibfk_1` FOREIGN KEY (`idCentro`) REFERENCES `centro` (`id`);

--
-- Filtros para la tabla `reservaclienteactividad`
--
ALTER TABLE `reservaclienteactividad`
  ADD CONSTRAINT `reservaclienteactividad_ibfk_1` FOREIGN KEY (`nifCli`) REFERENCES `cliente` (`nif`),
  ADD CONSTRAINT `reservaclienteactividad_ibfk_2` FOREIGN KEY (`idActividad`,`idActividadCentro`) REFERENCES `actividad` (`id`, `idCentro`);

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`idCentro`) REFERENCES `centro` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
