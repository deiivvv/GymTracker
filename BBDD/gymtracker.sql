drop database if exists gymtracker;
create database gymtracker;

use gymtracker;

CREATE TABLE usuarios (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(100),
  contrasena VARCHAR(100),
  rol VARCHAR(50),
  PRIMARY KEY (id)
);
create table perfil(
 id_usuario int,
 genero varchar(100),
 altura float,
 peso float,
 edad int,
 FOREIGN KEY (id_usuario)  REFERENCES usuarios(id)
);
CREATE TABLE rutinas (
  id INT AUTO_INCREMENT,
  id_usuario INT,
  nombre VARCHAR(100),
  fecha DATE,
  num_ejercicios INT,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
  PRIMARY KEY (id)
);

CREATE TABLE ejercicios (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(100),
  descripcion TEXT,
  equipamiento VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE musculos (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE ejercicios_musculos (
  id_ejercicio INT,
  id_musculo INT,
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id),
  FOREIGN KEY (id_musculo) REFERENCES musculos(id),
  PRIMARY KEY (id_ejercicio, id_musculo)
);

CREATE TABLE rutinas_ejercicios (
  id_rutina INT,
  id_ejercicio INT,
  FOREIGN KEY (id_rutina) REFERENCES rutinas(id),
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id),
  PRIMARY KEY (id_rutina, id_ejercicio)
);

CREATE TABLE series (
  id INT AUTO_INCREMENT,
  id_ejercicio INT,
  tipo VARCHAR(50),
  num_repes INT,
  FOREIGN KEY (id_ejercicio) REFERENCES ejercicios(id),
  PRIMARY KEY (id)
);

