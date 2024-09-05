
CREATE DATABASE IF NOT EXISTS crudjava;
USE crudjava;

-- Crear tabla persona
CREATE TABLE IF NOT EXISTS persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero ENUM('Masculino', 'Femenino') NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(20) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

-- Crear tabla cliente
CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    persona_id INT,
    FOREIGN KEY (persona_id) REFERENCES persona(id)
);

-- Crear tabla cuenta
CREATE TABLE IF NOT EXISTS cuenta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_cuenta ENUM('Ahorros', 'Corriente') NOT NULL,
    saldo_inicial DECIMAL(10, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Crear tabla movimiento
CREATE TABLE IF NOT EXISTS movimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento ENUM('Depósito', 'Retiro') NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    saldo_inicial DECIMAL(10, 2) NOT NULL,
    saldo_disponible DECIMAL(10, 2) NOT NULL,
    cuenta_id INT,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

-- Insertar datos en tabla persona
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES 
('Juan Perez', 'Masculino', 30, '12345678', 'Calle Falsa 123', '555-1234'),
('Maria Lopez', 'Femenino', 28, '87654321', 'Av. Siempre Viva 742', '555-5678'),
('Carlos García', 'Masculino', 35, '11223344', 'Calle 50 No. 100', '555-9876');

-- Insertar datos en tabla cliente
INSERT INTO cliente (contrasena, estado, persona_id)
VALUES 
('password123', TRUE, 1),
('password456', TRUE, 2),
('password789', TRUE, 3);

-- Insertar datos en tabla cuenta
INSERT INTO cuenta (tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES 
('Ahorros', 1000.00, TRUE, 1),
('Corriente', 2000.00, TRUE, 2),
('Ahorros', 500.00, TRUE, 3);

-- Insertar datos en tabla movimiento
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo_inicial, saldo_disponible, cuenta_id)
VALUES 
('2024-09-01', 'Depósito', 500.00, 1000.00, 1500.00, 1),
('2024-09-02', 'Retiro', -200.00, 1500.00, 1300.00, 1),
('2024-09-03', 'Depósito', 300.00, 1300.00, 1600.00, 1),

('2024-09-01', 'Retiro', -100.00, 2000.00, 1900.00, 2),
('2024-09-02', 'Depósito', 400.00, 1900.00, 2300.00, 2),

('2024-09-01', 'Depósito', 250.00, 500.00, 750.00, 3),
('2024-09-02', 'Retiro', -100.00, 750.00, 650.00, 3);
