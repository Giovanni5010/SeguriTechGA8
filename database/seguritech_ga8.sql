-- Base de datos para la evidencia GA8-220501096-AA1-EV01
-- Proyecto: Digitalización del Proceso de Chequeo de Cuentas en SeguriTech S.A.
-- Motor sugerido: MySQL 8.x

CREATE DATABASE IF NOT EXISTS seguritech_ga8
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE seguritech_ga8;

DROP TABLE IF EXISTS visita_tecnica;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS rol;

CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(80) NOT NULL,
    apellidos VARCHAR(80) NOT NULL,
    documento VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(120) NOT NULL UNIQUE,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,
    id_rol INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'Activo',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(120) NOT NULL,
    documento_nit VARCHAR(30) NOT NULL UNIQUE,
    direccion VARCHAR(160) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    correo VARCHAR(120),
    estado VARCHAR(20) NOT NULL DEFAULT 'Activo',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE visita_tecnica (
    id_visita INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_visita DATE NOT NULL,
    motivo VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    observaciones TEXT,
    estado VARCHAR(30) NOT NULL DEFAULT 'Registrada',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_visita_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT fk_visita_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

INSERT INTO rol (nombre_rol) VALUES ('Administrador'), ('Tecnico');

-- Usuarios de prueba. En un ambiente real la contraseña debe almacenarse con hash seguro.
INSERT INTO usuario (nombres, apellidos, documento, correo, usuario, contrasena, id_rol, estado)
VALUES
('Administrador', 'Sistema', '1000000001', 'admin@seguritech.com', 'admin', 'admin123', 1, 'Activo'),
('Tecnico', 'Pruebas', '1000000002', 'tecnico@seguritech.com', 'tecnico', 'tecnico123', 2, 'Activo');

INSERT INTO cliente (nombre_cliente, documento_nit, direccion, telefono, correo, estado)
VALUES
('Conjunto Residencial Las Palmas', '900123456-1', 'Cra 45 # 10-20 Medellín', '6044445566', 'admin@laspalmas.com', 'Activo'),
('Comercializadora Norte S.A.S.', '901987654-3', 'Cl 80 # 50-30 Medellín', '6045557788', 'contacto@comercialnorte.com', 'Activo');

INSERT INTO visita_tecnica (id_cliente, id_usuario, fecha_visita, motivo, descripcion, observaciones, estado)
VALUES
(1, 2, CURRENT_DATE(), 'Chequeo de cuenta y validación de servicio', 'Se verifica información de contacto, dirección y estado del servicio.', 'Sin novedades críticas.', 'Registrada'),
(2, 2, CURRENT_DATE(), 'Actualización de información técnica', 'Se actualizan observaciones del cliente y se deja registro para trazabilidad.', 'Pendiente seguimiento administrativo.', 'Registrada');
