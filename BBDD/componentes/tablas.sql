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

