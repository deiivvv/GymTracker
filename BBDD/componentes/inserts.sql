
USE gymtracker;

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
INSERT INTO series (id_ejercicio, peso, num_repes) VALUES
(@id_press_banca, 50, 20),
(@id_press_banca, 100, 1);

-- Insertar series para el ejercicio 'press militar'
INSERT INTO series (id_ejercicio, peso, num_repes) VALUES
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
(@id_press_banca, (SELECT id FROM series WHERE id_ejercicio = @id_press_banca AND peso = 50 AND num_repes = 20)),
(@id_press_banca, (SELECT id FROM series WHERE id_ejercicio = @id_press_banca AND peso = 100 AND num_repes = 1));

-- Para el ejercicio 'press militar'
INSERT INTO ejercicios_series (id_ejercicio, id_serie) VALUES
(@id_press_militar, (SELECT id FROM series WHERE id_ejercicio = @id_press_militar AND peso = 20 AND num_repes = 20)),
(@id_press_militar, (SELECT id FROM series WHERE id_ejercicio = @id_press_militar AND peso = 40 AND num_repes = 1));
