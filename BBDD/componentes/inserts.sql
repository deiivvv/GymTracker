
USE gymtracker;


-- Insertar usuario ficticio 'damago' y su perfil
INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('damago', 'damago', 'usuario');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('alvaro', 'alvaro', 'administrador');
INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES (LAST_INSERT_ID(), 'Masculino', 25, 180, 75);

INSERT INTO usuarios (nombre, contrasena, rol) VALUES ('david', 'david', 'bloqueado');
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
