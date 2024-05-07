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
  id_ejercicio INT,
  peso FLOAT,
  repes INT,
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id)
    ON DELETE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE ejercicios_series (
  id_ejercicio INT,
  id_serie INT,
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id)
    ON DELETE CASCADE,
  FOREIGN KEY (id_serie) REFERENCES series(id)
    ON DELETE CASCADE,
    PRIMARY KEY (id_ejercicio, id_serie)
);

CREATE TABLE rutinas_ejercicios (
  id_rutina INT,
  id_ejercicio INT,
  FOREIGN KEY (id_rutina) REFERENCES rutinas(id)
    ON DELETE CASCADE,
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id)
    ON DELETE CASCADE,
  PRIMARY KEY (id_rutina, id_ejercicio)
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
INSERT INTO series (id_ejercicio, peso, repes) VALUES
(@id_press_banca, 50, 20),
(@id_press_banca, 100, 1);

-- Insertar series para el ejercicio 'press militar'
INSERT INTO series (id_ejercicio, peso, repes) VALUES
(@id_press_militar, 20, 20),
(@id_press_militar, 40, 1);

-- Insertar rutina 'pechito' asociada al usuario 'damago'
INSERT INTO rutinas (id_usuario, nombre, fecha) VALUES ((SELECT id FROM usuarios WHERE nombre = 'damago'), 'pechito', CURDATE());

-- Asociar ejercicios a la rutina 'pechito'
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES
((SELECT id FROM rutinas WHERE nombre = 'pechito'), @id_press_banca),
((SELECT id FROM rutinas WHERE nombre = 'pechito'), @id_press_militar);

-- Insertar relaciones entre ejercicios y series
-- Para el ejercicio 'press banca'
INSERT INTO ejercicios_series (id_ejercicio, id_serie) VALUES
(@id_press_banca, (SELECT id FROM series WHERE id_ejercicio = @id_press_banca AND peso = 50 AND repes = 20)),
(@id_press_banca, (SELECT id FROM series WHERE id_ejercicio = @id_press_banca AND peso = 100 AND repes = 1));

-- Para el ejercicio 'press militar'
INSERT INTO ejercicios_series (id_ejercicio, id_serie) VALUES
(@id_press_militar, (SELECT id FROM series WHERE id_ejercicio = @id_press_militar AND peso = 20 AND repes = 20)),
(@id_press_militar, (SELECT id FROM series WHERE id_ejercicio = @id_press_militar AND peso = 40 AND repes = 1));
