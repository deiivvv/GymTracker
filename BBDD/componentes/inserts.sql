USE gymtracker;

-- Usuario para rutinas pretederminadas

insert into usuarios(id, nombre, contrasena, rol) values (-1, 'usuario_rutinas', 'usuario_rutinas', 'bloqueado');
SET @id_usuario = -1;

-- Rutina1:Pecho y Tríceps

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, "Pecho y Trícepes", "2004-01-16");
SET @id_rutina_1 = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(2, "press banca"),
(5, "fondos en paralelas"),
(14, "extensiones de triceps en polea alta"),
(33, "flexiones diamante"),
(37, "press de banca con mancuernas"),
(43, "pulldown con polea alta");

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

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, "Espalda y Bíceps", "1995-11-28");
SET @id_rutina_2 = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(3, "dominadas"),
(6, "remo con barra"),
(8, "curl de biceps con barra"),
(24, "pajaros"),
(38, "face pull"),
(44, "remo con mancuerna");

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

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, "Piernas y Glúteos", "2004-11-2");
SET @id_rutina_3 = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(1, "sentadillas"),
(4, "peso muerto"),
(10, "zancadas"),
(16, "hip thrust"),
(49, "elevaciones de gemelos sentado"),
(13, "prensa de piernas");

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
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('damago', 'damago', 'usuario');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('alvaro', 'alvaro', 'administrador');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('david', 'david', 'bloqueado');
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
