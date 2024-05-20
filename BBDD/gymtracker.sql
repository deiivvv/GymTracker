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

USE gymtracker;

insert into usuarios(id, nombre, contrasena, rol) values (-1, 'usuario_rutinas', 'usuario_rutinas', 'bloqueado');

SET @id_usuario = -1;

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, "Pecha y Trícepes", "2004-01-16");
SET @id_rutina = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(2, "press banca"),
(5, "fondos en paralelas"),
(14, "extensiones de triceps en polea alta"),
(33, "flexiones diamante"),
(37, "press de banca con mancuernas"),
(43, "pulldown con polea alta");

insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_fondos_paralelas_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_fondos_paralelas_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_fondos_paralelas_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_extension_triceps_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_extension_triceps_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_extension_triceps_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_flexiones_diamante_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_flexiones_diamante_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_flexiones_diamante_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_mancuernas_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_mancuernas_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_mancuernas_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_pulldown_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_pulldown_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_pulldown_3 = LAST_INSERT_ID();

insert into rutinas_ejercicios_series(id_rutina, id_ejercicio, id_serie) values
(@id_rutina, 2, @id_serie_press_banca_1),
(@id_rutina, 2, @id_serie_press_banca_2),
(@id_rutina, 2, @id_serie_press_banca_3),
(@id_rutina, 5, @id_serie_fondos_paralelas_1),
(@id_rutina, 5, @id_serie_fondos_paralelas_2),
(@id_rutina, 5, @id_serie_fondos_paralelas_3),
(@id_rutina, 14, @id_serie_extension_triceps_1),
(@id_rutina, 14, @id_serie_extension_triceps_2),
(@id_rutina, 14, @id_serie_extension_triceps_3),
(@id_rutina, 33, @id_serie_flexiones_diamante_1),
(@id_rutina, 33, @id_serie_flexiones_diamante_2),
(@id_rutina, 33, @id_serie_flexiones_diamante_3),
(@id_rutina, 37, @id_serie_press_banca_mancuernas_1),
(@id_rutina, 37, @id_serie_press_banca_mancuernas_2),
(@id_rutina, 37, @id_serie_press_banca_mancuernas_3),
(@id_rutina, 43, @id_serie_pulldown_1),
(@id_rutina, 43, @id_serie_pulldown_2),
(@id_rutina, 43, @id_serie_pulldown_3);

-- Insertar usuario ficticio 'damago' y su perfil
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('damago', 'damago', 'usuario');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('alvaro', 'alvaro', 'administrador');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('david', 'david', 'bloqueado');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);


-- Obtener el ID del ejercicio 'sentadillas' insertado
SET @id_sentadillas = 1;

-- Obtener el ID del ejercicio 'press banca' insertado
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
