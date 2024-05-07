DROP DATABASE IF EXISTS gymtracker;
CREATE DATABASE gymtracker;

USE gymtracker;

CREATE TABLE usuarios (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(100) UNIQUE,
  contrasena VARCHAR(100),
  rol VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE perfil (
  id_usuario INT AUTO_INCREMENT,
  genero VARCHAR(100),
  edad INT,
  altura FLOAT,
  peso FLOAT,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    ON DELETE CASCADE
);

CREATE TABLE rutinas (
  id INT AUTO_INCREMENT,
  id_usuario INT,
  nombre VARCHAR(100),
  fecha DATE,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    ON DELETE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE ejercicios (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE series (
  id INT AUTO_INCREMENT,
  peso FLOAT,
  repes INT,
  PRIMARY KEY (id)
);

CREATE TABLE rutinas_ejercicios_series (
  id_rutina INT,
  id_ejercicio INT,
  id_serie INT,
  FOREIGN KEY (id_rutina) REFERENCES rutinas(id)
    ON DELETE CASCADE,
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id)
    ON DELETE CASCADE,
  FOREIGN KEY (id_serie) REFERENCES series(id)
    ON DELETE CASCADE,
  PRIMARY KEY (id_rutina, id_ejercicio, id_serie)
);

-- Insertar usuario ficticio 'damago' y su perfil
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('damago', 'contrasena123', 'usuario');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

-- Insertar ejercicio 'press banca'
INSERT INTO ejercicios (nombre) VALUES ('press banca');
-- Obtener el ID del ejercicio 'press banca' insertado
SET @id_press_banca = LAST_INSERT_ID();

-- Insertar ejercicio 'press militar'
INSERT INTO ejercicios (nombre) VALUES ('press militar');
-- Obtener el ID del ejercicio 'press militar' insertado
SET @id_press_militar = LAST_INSERT_ID();

-- Insertar series para el ejercicio 'press banca'
INSERT INTO series (peso, repes) VALUES
(50, 20);
-- Obtener el ID de la serie insertada para 'press banca'
SET @id_serie_press_banca_1 = LAST_INSERT_ID();

INSERT INTO series (peso, repes) VALUES
(100, 1);
-- Obtener el ID de la serie insertada para 'press banca'
SET @id_serie_press_banca_2 = LAST_INSERT_ID();

-- Insertar series para el ejercicio 'press militar'
INSERT INTO series (peso, repes) VALUES
(20, 20);
-- Obtener el ID de la serie insertada para 'press militar'
SET @id_serie_press_militar_1 = LAST_INSERT_ID();

INSERT INTO series (peso, repes) VALUES
(40, 1);
-- Obtener el ID de la serie insertada para 'press militar'
SET @id_serie_press_militar_2 = LAST_INSERT_ID();

-- Insertar rutina 'pechito' asociada al usuario 'damago'
INSERT INTO rutinas (id_usuario, nombre, fecha) VALUES ((SELECT id FROM usuarios WHERE nombre = 'damago'), 'pechito', CURDATE());
-- Obtener el ID de la rutina 'pechito' insertada
SET @id_rutina_pechito = LAST_INSERT_ID();

-- Insertar relaciones entre ejercicios y series en la tabla rutinas_ejercicios_series

-- Para el ejercicio 'press banca'
INSERT INTO rutinas_ejercicios_series (id_rutina, id_ejercicio, id_serie) VALUES
(@id_rutina_pechito, @id_press_banca, @id_serie_press_banca_1),
(@id_rutina_pechito, @id_press_banca, @id_serie_press_banca_2);

-- Para el ejercicio 'press militar'
INSERT INTO rutinas_ejercicios_series (id_rutina, id_ejercicio, id_serie) VALUES
(@id_rutina_pechito, @id_press_militar, @id_serie_press_militar_1),
(@id_rutina_pechito, @id_press_militar, @id_serie_press_militar_2);
