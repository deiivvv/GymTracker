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

CREATE TABLE perfil (
  id_usuario INT,
  genero VARCHAR(100),
  altura float,
  peso float),
  fecha_creacion VARCHAR(100)
  PRIMARY KEY (id)
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

------------------------------------------------------------------------------------
----------------------------INSERTS-------------------------------
------------------------------------------------------------------------------------

-----------------------------MUSCULOS-----------------------------
INSERT INTO musculos (nombre) VALUES ('Bíceps');
INSERT INTO musculos (nombre) VALUES ('Triceps');
INSERT INTO musculos (nombre) VALUES ('Hombro');
INSERT INTO musculos (nombre) VALUES ('Trapecio');
INSERT INTO musculos (nombre) VALUES ('Antebrazo');
INSERT INTO musculos (nombre) VALUES ('Abdominales');
INSERT INTO musculos (nombre) VALUES ('Dorsal');
INSERT INTO musculos (nombre) VALUES ('Lumbar');
INSERT INTO musculos (nombre) VALUES ('Pectoral');
INSERT INTO musculos (nombre) VALUES ('Cuadriceps');
INSERT INTO musculos (nombre) VALUES ('Isquiotibial');
INSERT INTO musculos (nombre) VALUES ('Abductor');
INSERT INTO musculos (nombre) VALUES ('Adductor');
INSERT INTO musculos (nombre) VALUES ('Gemelo');
INSERT INTO musculos (nombre) VALUES ('Tibial');
INSERT INTO musculos (nombre) VALUES ('Gluteo');



-----------------------------EJERCICIOS-----------------------------
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES ('Press de banca', 'El press de banca es un ejercicio clásico para trabajar los pectorales, los deltoides anteriores y los tríceps. Se realiza acostado sobre un banco, levantando y bajando una barra con pesas desde el pecho hasta la extensión de los brazos.', 'Banco plano, barra y pesas');
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES ('Dominadas', 'Las dominadas son un ejercicio excelente para trabajar los músculos de la espalda, en particular los dorsales anchos y los braquiales. Se realiza suspendiéndose de una barra fija y elevando el cuerpo hasta que la barbilla esté por encima de la barra.', 'Barra fija');
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES ('Curl de bíceps', 'El curl de bíceps es un ejercicio de aislamiento para trabajar los músculos bíceps braquial y braquial. Se realiza levantando una barra o mancuernas desde la posición de brazos extendidos hasta la contracción máxima de los bíceps.', 'Barra o mancuernas');
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES ('Fondos de tríceps', 'Los fondos de tríceps son un ejercicio efectivo para trabajar los músculos tríceps braquial y anconeo. Se realiza apoyando las manos en barras paralelas y bajando el cuerpo flexionando los codos hasta que los brazos estén casi paralelos al suelo.', 'Barras paralelas');
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES ('Press Militar', 'El press de hombros es un ejercicio compuesto para trabajar los músculos deltoides, especialmente los deltoides anteriores. Se realiza levantando una barra o mancuernas desde la posición de hombros hasta la extensión completa de los brazos por encima de la cabeza.', 'Barra o mancuernas');
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES ('Sentadillas', 'Las sentadillas son un ejercicio básico para trabajar los músculos de las piernas, incluyendo los cuádriceps, los glúteos y los músculos de los isquiotibiales. Se realiza bajando el cuerpo hacia abajo flexionando las rodillas y las caderas, manteniendo la espalda recta.', 'Nada o Barra y soporte para sentadillas');

-----------------------------USUARIOS-----------------------------
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('David', '1234', 'admin');
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('Alvaro', '1234', 'admin');
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('Daniel', '1234', 'admin');

-----------------------------RUTINAS-----------------------------
INSERT INTO rutinas (id_usuario, nombre, fecha,num_ejercicios) VALUES (1, 'Rutina para perder grasa.',CURDATE(), 5);
INSERT INTO rutinas (id_usuario, nombre, fecha,num_ejercicios) VALUES (2, 'Rutina para definir.',CURDATE(), 5);
INSERT INTO rutinas (id_usuario, nombre, fecha,num_ejercicios) VALUES (3, 'Rutina para ganar musculo.',CURDATE(), 5);

-----------------------------RUTINAS-EJERCICIOS-----------------------------
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (1, 1);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (1, 2);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (1, 3);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (1, 4);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (1, 5);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (2, 1);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (2, 2);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (2, 3);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (2, 4);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (2, 5);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (3, 1);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (3, 2);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (3, 3);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (3, 4);
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (3, 5);

-----------------------------EJERCICIOS-MUSCULOS-----------------------------
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (1, 2);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (1, 3);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (1, 9);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (2, 7);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (2, 9);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (2, 3);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (2, 1);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (2, 2);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (3, 1);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (4, 3);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (4, 9);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (5, 3);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (5, 4);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (6, 10);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (6, 11);
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES (6, 16);






SELECT * FROM musculos;
SELECT * FROM ejercicios;
SELECT * FROM usuarios;
SELECT * FROM rutinas;
SELECT * FROM rutinas_ejercicios order by id_rutina;
SELECT * FROM ejercicios_musculos order by id_ejercicio;







