DROP DATABASE IF EXISTS COMIDA_RAPIDA;
CREATE DATABASE COMIDA_RAPIDA;
USE COMIDA_RAPIDA;

CREATE TABLE Clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    password VARCHAR(25)
);

CREATE TABLE Patatas (
    idPatatasFritas INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('Normal', 'Deluxe') NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 1000
);

CREATE TABLE Hamburguesa (
    idHamburguesa INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(25),
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 1000
);

CREATE TABLE Refresco (
    idRefresco INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('Refresco', 'Agua') NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 1000
);



CREATE TABLE Pedidos (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    cantidadPatatasFritas INT,
    idPatatasFritas INT,
    cantidadHamburguesa INT,
    idHamburguesa INT,
    cantidadRefresco INT,
    idRefresco INT,
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
    FOREIGN KEY (idPatatasFritas) REFERENCES Patatas(idPatatasFritas),
    FOREIGN KEY (idHamburguesa) REFERENCES Hamburguesa(idHamburguesa),
    FOREIGN KEY (idRefresco) REFERENCES Refresco(idRefresco)
);

       
       
INSERT INTO Clientes (nombre, email, password)
VALUES 
('Juan Pérez', 'juan.perez@example.com', 'password123'),
('María García', 'maria.garcia@example.com', 'password456'),
('Carlos López', 'carlos.lopez@example.com', 'password789'),
('Ana Torres', 'ana.torres@example.com', 'password321'),
('Luis Martínez', 'luis.martinez@example.com', 'password654');

INSERT INTO Patatas (tipo, precio, stock) VALUES
('Normal', 3.50, 50),
('Deluxe', 5.00, 30);

-- Inserts para la tabla Hamburguesa
INSERT INTO Hamburguesa (tipo, precio, stock) VALUES
('Sin queso', 6.99, 40),
('Con queso', 8.99, 35);

-- Inserts para la tabla Refresco
INSERT INTO Refresco (tipo, precio, stock) VALUES
('Refresco', 2.50, 60),
('Agua', 1.50, 70);


SELECT * FROM Clientes;




