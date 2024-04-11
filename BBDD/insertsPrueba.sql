-- Inserts para la tabla usuarios
INSERT INTO usuarios (nombre, contrasena, rol) VALUES
('Juan', 'contrasena123', 'usuario'),
('Maria', 'pass123', 'usuario'),
('Admin', 'adminpass', 'administrador');

-- Inserts para la tabla ejercicios
INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES
('Sentadillas', 'Ejercicio para piernas y glúteos', 'Barra con pesas'),
('Flexiones de brazos', 'Ejercicio para pectorales y tríceps', 'Ninguno'),
('Plancha', 'Ejercicio para fortalecer el core', 'Ninguno');

-- Inserts para la tabla musculos
INSERT INTO musculos (nombre) VALUES
('Piernas'),
('Glúteos'),
('Pectorales'),
('Tríceps'),
('Core');

-- Inserts para la tabla ejercicios_musculos
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(1, 1), -- Sentadillas - Piernas
(1, 2), -- Sentadillas - Glúteos
(2, 3), -- Flexiones de brazos - Pectorales
(2, 4), -- Flexiones de brazos - Tríceps
(3, 5); -- Plancha - Core

-- Inserts para la tabla rutinas
INSERT INTO rutinas (id_usuario, nombre, fecha, num_ejercicios) VALUES
(1, 'Rutina de piernas', '2024-04-10', 1),
(2, 'Rutina de torso', '2024-04-11', 2);

-- Inserts para la tabla rutinas_ejercicios
INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES
(1, 1), -- Rutina de piernas incluye Sentadillas
(2, 2), -- Rutina de torso incluye Flexiones de brazos
(2, 3); -- Rutina de torso incluye Plancha

-- Inserts para la tabla series
INSERT INTO series (id_ejercicio, tipo, num_repes) VALUES
(1, 'Calentamiento', 10), -- Sentadillas
(1, 'Serie principal', 3), -- Sentadillas
(2, 'Serie principal', 12), -- Flexiones de brazos
(3, 'Serie principal', NULL); -- Plancha

