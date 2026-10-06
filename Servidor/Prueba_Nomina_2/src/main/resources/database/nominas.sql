

CREATE DATABASE IF NOT EXISTS nomina
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE nomina;


CREATE TABLE IF NOT EXISTS empleados (
    dni VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sexo CHAR(1) NOT NULL,
    categoria INT NOT NULL,
    anyos INT NOT NULL
);



CREATE TABLE IF NOT EXISTS nominas (
    dni VARCHAR(15) PRIMARY KEY,
    sueldo INT NOT NULL,
    FOREIGN KEY (dni) REFERENCES empleados(dni)
);



INSERT INTO empleados (dni, nombre, sexo, categoria, anyos)
VALUES
('32000032G', 'James Cosling', 'M', 9, 7),
('32000031R', 'Ada Lovelace', 'F', 1, 1);



INSERT INTO nominas (dni, sueldo)
VALUES
('32000032G', 245000),
('32000031R', 55000);