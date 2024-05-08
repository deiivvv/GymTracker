
insert into usuarios(id, nombre, contrasena, rol) values (-1, 'usuario_rutinas', 'usuario_rutinas', 'bloqueado');

SET @id_usuario = -1;

insert into rutinas(id_usuario, nombre, fecha) values(@id_usuario, "Pecha y Trícepes", "2004-01-16");
SET @id_rutina = LAST_INSERT_ID();

insert into ejercicios(id, nombre) values
(2, "press banca"),
(5, "fondos en paralelas"),
(14, "extensiones de triceps en polea alta"),
(33, "flexiones diamante"),
(37, "press de banca con mancuernas"),
(43, "pulldown con polea alta");

insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_fondos_paralelas_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_fondos_paralelas_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_fondos_paralelas_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_extension_triceps_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_extension_triceps_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_extension_triceps_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_flexiones_diamante_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_flexiones_diamante_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_flexiones_diamante_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_mancuernas_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_mancuernas_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_press_banca_mancuernas_3 = LAST_INSERT_ID();

insert into series(peso, repes) values(0, 0);
SET @id_serie_pulldown_1 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_pulldown_2 = LAST_INSERT_ID();
insert into series(peso, repes) values(0, 0);
SET @id_serie_pulldown_3 = LAST_INSERT_ID();

insert into rutinas_ejercicios_series(id_rutina, id_ejercicio, id_serie) values
(@id_rutina, 2, @id_serie_press_banca_1),
(@id_rutina, 2, @id_serie_press_banca_2),
(@id_rutina, 2, @id_serie_press_banca_3),
(@id_rutina, 5, @id_serie_fondos_paralelas_1),
(@id_rutina, 5, @id_serie_fondos_paralelas_2),
(@id_rutina, 5, @id_serie_fondos_paralelas_3),
(@id_rutina, 14, @id_serie_extension_triceps_1),
(@id_rutina, 14, @id_serie_extension_triceps_2),
(@id_rutina, 14, @id_serie_extension_triceps_3),
(@id_rutina, 33, @id_serie_flexiones_diamante_1),
(@id_rutina, 33, @id_serie_flexiones_diamante_2),
(@id_rutina, 33, @id_serie_flexiones_diamante_3),
(@id_rutina, 37, @id_serie_press_banca_mancuernas_1),
(@id_rutina, 37, @id_serie_press_banca_mancuernas_2),
(@id_rutina, 37, @id_serie_press_banca_mancuernas_3),
(@id_rutina, 43, @id_serie_pulldown_1),
(@id_rutina, 43, @id_serie_pulldown_2),
(@id_rutina, 43, @id_serie_pulldown_3);
