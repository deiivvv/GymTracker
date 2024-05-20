USE gymtracker;

-- Insertar usuarios ficticios y sus perfiles
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('damago', 'damago', 'usuario');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('alvaro', 'alvaro', 'administrador');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('david', 'david', 'bloqueado');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

-- Insertar ejercicio 'sentadillas'
INSERT INTO ejercicios (nombre) VALUES ('sentadillas');
-- Obtener el ID del ejercicio 'sentadillas' insertado
SET @id_sentadillas = LAST_INSERT_ID();

-- Insertar ejercicio 'press banca'
INSERT INTO ejercicios (nombre) VALUES ('press de banca');
-- Obtener el ID del ejercicio 'press banca' insertado
SET @id_press_banca = LAST_INSERT_ID();

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
