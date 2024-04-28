INSERT INTO usuarios (nombre, contrasena, rol) VALUES
('Juan', 'contrasena123', 'usuario'),
('Maria', 'pass123', 'usuario'),
('Admin', 'adminpass', 'administrador');

INSERT INTO perfil (id_usuario, genero, edad, altura, peso) VALUES
(1, 'Masculino', 30, 175.5, 70.2),
(2, 'Femenino', 25, 160.0, 55.8),
(3, 'Masculino', 20, 180.0, 80.0);

INSERT INTO ejercicios (nombre, descripcion, equipamiento) VALUES
('Sentadillas', 'Ejercicio para piernas y glúteos', 'Barra con pesas'),
('Flexiones de brazos', 'Ejercicio para pectorales y tríceps', 'Ninguno'),
('Plancha', 'Ejercicio para fortalecer el core', 'Ninguno');

INSERT INTO musculos (nombre) VALUES
('Piernas'),
('Glúteos'),
('Pectorales'),
('Tríceps'),
('Core');

INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5);

INSERT INTO rutinas (id_usuario, nombre, fecha, num_ejercicios) VALUES
(1, 'Rutina de piernas', '2024-04-10', 1),
(2, 'Rutina de torso', '2024-04-11', 2);

INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES
(1, 1),
(2, 2),
(2, 3);

INSERT INTO series (id_ejercicio, tipo, num_repes) VALUES
(1, 'Calentamiento', 10),
(1, 'Serie principal', 3),
(2, 'Serie principal', 12),
(3, 'Serie principal', NULL);

