-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2024 a las 17:22:44
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

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
('09876543R', '123456789', '098765432', 'Marina Sánchez', 37),
('10987654S', '123456789', '109876543', 'Luisa Pérez', 39),
('12345678A', '123456789', '123456789', 'María García', 35),
('21098765J', '123456789', '210987654', 'Pablo Martín', 38),
('21098765T', '123456789', '210987654', 'Antonio Gómez', 41),
('23456789B', '123456789', '234567890', 'Juan López', 28),
('32109876K', '123456789', '321098765', 'Carmen López', 42),
('34567890C', '123456789', '345678901', 'Laura Martínez', 40),
('43210987L', '123456789', '432109876', 'Javier García', 36),
('45678901D', '123456789', '456789012', 'Carlos Rodríguez', 25),
('54321098M', '123456789', '543210987', 'Sofía González', 29),
('56789012E', '123456789', '567890123', 'Ana Sánchez', 30),
('65432109N', '123456789', '654321098', 'Lucía Torres', 32),
('67890123F', '123456789', '678901234', 'David Pérez', 33),
('76543210O', '123456789', '765432109', 'Pedro Díaz', 31),
('78901234G', '123456789', '789012345', 'Sara Gómez', 45),
('87654321P', '123456789', '876543210', 'Isabel Ruiz', 36),
('89012345H', '123456789', '890123456', 'Miguel Ruiz', 50),
('90123456I', '123456789', '901234567', 'Elena Fernández', 27),
('98765432Q', '123456789', '987654321', 'Sergio Martínez', 44);

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
('2024-05-01', '12345678A', 1, 1),
('2024-05-02', '23456789B', 2, 2),
('2024-05-03', '34567890C', 3, 3),
('2024-05-04', '45678901D', 4, 4),
('2024-05-05', '56789012E', 5, 5),
('2024-05-06', '67890123F', 6, 6),
('2024-05-07', '78901234G', 7, 7),
('2024-05-08', '89012345H', 8, 8),
('2024-05-09', '90123456I', 9, 9),
('2024-05-10', '21098765J', 10, 10),
('2024-05-11', '32109876K', 11, 1),
('2024-05-12', '43210987L', 12, 2),
('2024-05-13', '54321098M', 13, 3),
('2024-05-14', '65432109N', 14, 4),
('2024-05-15', '76543210O', 15, 5),
('2024-05-16', '87654321P', 16, 6),
('2024-05-17', '98765432Q', 17, 7),
('2024-05-18', '09876543R', 18, 8),
('2024-05-19', '10987654S', 19, 9),
('2024-05-20', '21098765T', 20, 10);

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
('01234567J', '123456789', 'Sara', 'Díaz', 2800.00, 30, 1),
('09876543A', '102345678', 'Roberto', 'Herrero', 3200.00, 30, 2),
('09876543R', '1234567819', 'Alberto', 'Vidal', 3000.00, 38, 10),
('09876548A', '202345678', 'Carlos', 'Muñoz', 2900.00, 35, 2),
('10887654A', '212345678', 'Ana', 'Herrero', 3200.00, 30, 3),
('10987654A', '112345678', 'Celia', 'Gutiérrez', 3000.00, 38, 3),
('10987654S', '1234567820', 'Rosa', 'Torres', 2800.00, 33, 3),
('12345678A', '12345678', 'Juan', 'García', 2500.00, 35, 1),
('21088765A', '212345678', 'Luis', 'Gutiérrez', 3000.00, 38, 4),
('21098765A', '122345678', 'Pablo', 'Ramos', 2800.00, 33, 4),
('21098765J', '1234567811', 'Sergio', 'Ruiz', 2900.00, 34, 4),
('21098765T', '22345678', 'Daniel', 'García', 2700.00, 29, 4),
('23456789B', '123456881', 'María', 'López', 2800.00, 28, 2),
('32109876A', '132345678', 'Sandra', 'Navarro', 2700.00, 29, 5),
('32109876K', '1234567812', 'Luisa', 'González', 2800.00, 27, 5),
('32109876U', '32345678', 'Eva', 'Fernández', 2900.00, 36, 5),
('34567890C', '123456782', 'Pedro', 'Martínez', 3000.00, 40, 3),
('43210987A', '142345678', 'Manuel', 'López', 2900.00, 36, 6),
('43210987L', '1234567813', 'Javier', 'Martínez', 3000.00, 39, 6),
('43210987V', '42345678', 'Diego', 'Martín', 2800.00, 31, 6),
('45678901D', '123456783', 'Ana', 'Fernández', 2600.00, 33, 1),
('54321098A', '152345678', 'Elena', 'Martínez', 2800.00, 31, 7),
('54321098M', '1234567814', 'Carmen', 'Sanz', 2600.00, 32, 7),
('54321098W', '52345678', 'Cristina', 'Alonso', 3000.00, 40, 7),
('56789012E', '123456784', 'David', 'Sánchez', 2700.00, 29, 2),
('65432109A', '162345678', 'Alejandro', 'Sánchez', 3000.00, 40, 8),
('65432109N', '1234567815', 'Pablo', 'Jiménez', 2700.00, 28, 8),
('65432109X', '62345678', 'Héctor', 'Gómez', 2600.00, 34, 8),
('67890123F', '123456785', 'Elena', 'Gómez', 2900.00, 37, 3),
('76543210A', '172345678', 'Sara', 'Gómez', 2600.00, 34, 9),
('76543210O', '1234567816', 'Sonia', 'Ortega', 3100.00, 41, 9),
('76543210Y', '72345678', 'Isabel', 'Díaz', 2700.00, 29, 9),
('78901234G', '123456786', 'Miguel', 'Rodríguez', 3200.00, 42, 1),
('87654321A', '182345678', 'Juan', 'Díaz', 2700.00, 29, 10),
('87654321P', '1234567818', 'Jorge', 'López', 2900.00, 35, 10),
('87654321Z', '82345678', 'Álvaro', 'Prieto', 3100.00, 41, 10),
('89012345H', '123456787', 'Laura', 'Pérez', 2700.00, 31, 2),
('90123456I', '123456788', 'Carlos', 'Hernández', 3100.00, 36, 3),
('90123456Z', '1234567810', 'Carlos', 'Hernández', 3100.00, 36, 3),
('98765412A', '192345678', 'María', 'Prieto', 3100.00, 41, 1),
('98765432A', '92345678', 'Laura', 'Muñoz', 2900.00, 35, 1),
('98765432Q', '1234567818', 'Marina', 'Ramírez', 3200.00, 30, 1);

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
