-- Asegurarse de usar UTF-8
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

DROP DATABASE IF EXISTS gymtracker;
CREATE DATABASE gymtracker CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE gymtracker;

DROP USER IF EXISTS 'dad'@'localhost';
CREATE USER 'dad'@'localhost' IDENTIFIED BY 'padre';
GRANT ALL PRIVILEGES ON gymtracker.* TO 'dad'@'localhost';

-- Asegurarse de usar UTF-8 para la conexión
USE gymtracker;
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

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

USE gymtracker;

-- Usuario para rutinas pretederminadas

-- -u usuario_rutinas -p dad
insert into usuarios(id, nombre, contrasena, rol) values (-1, 'usuario_rutinas', '$2a$10$5zV7bNha4hEX5P5d7MM8W.Da1BIri3C1j9O/1rbFRj37AoCbxzzyS', 'bloqueado');
SET @id_usuario = -1;

-- Rutina1:Pecho y Tríceps

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, 'Pecho y Tríceps', '2004-01-16');
SET @id_rutina_1 = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(2, 'press banca'),
(5, 'fondos en paralelas'),
(14, 'extensiones de triceps en polea alta'),
(33, 'flexiones diamante'),
(37, 'press de banca con mancuernas'),
(43, 'pulldown con polea alta');

insert into series(peso, repes) values(0, 0);
SET @id_serie_1 = LAST_INSERT_ID();

insert into rutinas_ejercicios_series(id_rutina, id_ejercicio, id_serie) values
(@id_rutina_1, 2, @id_serie_1),
(@id_rutina_1, 5, @id_serie_1),
(@id_rutina_1, 14, @id_serie_1),
(@id_rutina_1, 33, @id_serie_1),
(@id_rutina_1, 37, @id_serie_1),
(@id_rutina_1, 43, @id_serie_1);

-- Rutina2:Espalda y Bíceps

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, 'Espalda y Bíceps', '1995-11-28');
SET @id_rutina_2 = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(3, 'dominadas'),
(6, 'remo con barra'),
(8, 'curl de biceps con barra'),
(24, 'pajaros'),
(38, 'face pull'),
(44, 'remo con mancuerna');

insert into series(peso, repes) values(0, 0);
SET @id_serie_2 = LAST_INSERT_ID();

insert into rutinas_ejercicios_series(id_rutina, id_ejercicio, id_serie) values
(@id_rutina_2, 3, @id_serie_2),
(@id_rutina_2, 6, @id_serie_2),
(@id_rutina_2, 8, @id_serie_2),
(@id_rutina_2, 24, @id_serie_2),
(@id_rutina_2, 38, @id_serie_2),
(@id_rutina_2, 44, @id_serie_2);

-- Rutina3:Piernas y Glúteos:

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, 'Piernas y Glúteos', '2004-11-2');
SET @id_rutina_3 = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(1, 'sentadillas'),
(4, 'peso muerto'),
(10, 'zancadas'),
(16, 'hip thrust'),
(49, 'elevaciones de gemelos sentado'),
(13, 'prensa de piernas');

insert into series(peso, repes) values(0, 0);
SET @id_serie_3 = LAST_INSERT_ID();

insert into rutinas_ejercicios_series(id_rutina, id_ejercicio, id_serie) values
(@id_rutina_3, 1, @id_serie_3),
(@id_rutina_3, 4, @id_serie_3),
(@id_rutina_3, 10, @id_serie_3),
(@id_rutina_3, 16, @id_serie_3),
(@id_rutina_3, 49, @id_serie_3),
(@id_rutina_3, 13, @id_serie_3);

-- Insertar usuarios ficticios y sus perfiles

-- -u damago -p damago
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('damago', '$2a$10$.xAAp7NQisTV9EzB.cKdP.Ha5Q.v1OP2TpgupH8r9rSSAhG2N1l9O', 'usuario');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);
-- -u alvaro -p alvaro
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('alvaro', '$2a$10$1n/jJ/6C7J4QwEW24ywaxOWVhkbS.YabwmNbLq49zmNiwhwVG/UWu', 'administrador');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);
-- -u david -p david
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('david', '$2a$10$Vw0oGAx6fBQ6wpmIKPMN/.Y43tgNT/WUoM2a33XqdX7l7R1EE2DKS', 'bloqueado');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);


-- Obtener el ID del ejercicio 'sentadillas' 
SET @id_sentadillas = 1;

-- Obtener el ID del ejercicio 'press banca' 
SET @id_press_banca = 2;

-- Insertar series para el ejercicio 'sentadillas'
INSERT INTO series (peso, repes) VALUES
(20, 20);
-- Obtener el ID de la serie insertada para 'sentadillas'
SET @id_serie_sentadillas_1 = LAST_INSERT_ID();

INSERT INTO series (peso, repes) VALUES
(40, 1);
-- Obtener el ID de la serie insertada para 'sentadillas'
SET @id_serie_sentadillas_2 = LAST_INSERT_ID();

-- Insertar series para el ejercicio 'press banca'
INSERT INTO series (peso, repes) VALUES
(50, 20);
-- Obtener el ID de la serie insertada para 'press banca'
SET @id_serie_press_banca_1 = LAST_INSERT_ID();

INSERT INTO series (peso, repes) VALUES
(100, 1);
-- Obtener el ID de la serie insertada para 'press banca'
SET @id_serie_press_banca_2 = LAST_INSERT_ID();

-- Insertar rutina 'rutina1' asociada al usuario 'damago'
INSERT INTO rutinas (id_usuario, nombre, fecha) VALUES ((SELECT id FROM usuarios WHERE nombre = 'damago'), 'rutina1', CURDATE());
-- Obtener el ID de la rutina 'rutina1' insertada
SET @id_rutina_rutina1 = LAST_INSERT_ID();


-- Insertar relaciones entre ejercicios y series en la tabla rutinas_ejercicios_series

-- Para el ejercicio 'sentadillas'
INSERT INTO rutinas_ejercicios_series (id_rutina, id_ejercicio, id_serie) VALUES
(@id_rutina_rutina1, @id_sentadillas, @id_serie_sentadillas_1),
(@id_rutina_rutina1, @id_sentadillas, @id_serie_sentadillas_2);

-- Para el ejercicio 'press banca'
INSERT INTO rutinas_ejercicios_series (id_rutina, id_ejercicio, id_serie) VALUES
(@id_rutina_rutina1, @id_press_banca, @id_serie_press_banca_1),
(@id_rutina_rutina1, @id_press_banca, @id_serie_press_banca_2);
