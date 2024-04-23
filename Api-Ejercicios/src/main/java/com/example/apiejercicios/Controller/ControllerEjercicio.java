package com.example.apiejercicios.Controller;

import com.example.apiejercicios.orm.EjercicioORM;
import com.example.apiejercicios.repositorio.RepositoryEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControllerEjercicio {

	@Autowired
	private RepositoryEjercicio ejerciciosRepository;

	@GetMapping("/ejercicios/buscar")
	public ResponseEntity<Iterable<EjercicioORM>> buscarEjercicios(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String musculo, @RequestParam(required = false) String equipamiento) {

		if (nombre != null) {
			if (musculo != null && equipamiento != null) {
				return findByNombreAndEquipamientoAndMusculo(nombre, equipamiento, musculo);
			} else if (musculo != null) {
				return findByNombreAndMusculo(nombre, musculo);
			} else if (equipamiento != null) {
				return findByNombreAndEquipamiento(nombre, equipamiento);
			} else {
				return findByNombre(nombre);
			}
		} else if (musculo != null) {
			if (equipamiento != null) {
				return findByMusculoAndEquipamiento(musculo, equipamiento);
			} else {
				return findByMusculo(musculo);
			}
		} else if (equipamiento != null) {
			return findByEquipamiento(equipamiento);
		} else {
			return null;
		}
	}

	@GetMapping("/ejercicios/nombre")
	public ResponseEntity<Iterable<EjercicioORM>> findByNombre(@RequestParam(required = false) String nombre) {
		return ResponseEntity.ok(ejerciciosRepository.findByNombreContaining(nombre));
	}

	@GetMapping("/ejercicios/buscarPorMusculo")
	public ResponseEntity<Iterable<EjercicioORM>> findByMusculo(@RequestParam(required = false) String musculo) {
		return ResponseEntity.ok(ejerciciosRepository.findByMusculoContaining(musculo));

	}

	@GetMapping("/ejercicios/buscarPorEquipamiento")
	public ResponseEntity<Iterable<EjercicioORM>> findByEquipamiento(
			@RequestParam(required = false) String equipamiento) {
		return ResponseEntity.ok(ejerciciosRepository.findByEquipamientoContaining(equipamiento));
	}

	@GetMapping("/ejercicios/buscarPorMusculoYEquipamiento")
	public ResponseEntity<Iterable<EjercicioORM>> findByMusculoAndEquipamiento(
			@RequestParam(required = false) String musculo, @RequestParam(required = false) String equipamiento) {
		return ResponseEntity
				.ok(ejerciciosRepository.findByMusculoContainingAndEquipamientoContaining(musculo, equipamiento));
	}

	@GetMapping("/ejercicios/buscarPorNombreYMusculo")
	public ResponseEntity<Iterable<EjercicioORM>> findByNombreAndMusculo(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String musculo) {
		return ResponseEntity.ok(ejerciciosRepository.findByNombreContainingAndMusculoContaining(nombre, musculo));

	}

	@GetMapping("/ejercicios/buscarPorNombreYEquipamiento")
	public ResponseEntity<Iterable<EjercicioORM>> findByNombreAndEquipamiento(
			@RequestParam(required = false) String nombre, @RequestParam(required = false) String equipamiento) {
		return ResponseEntity
				.ok(ejerciciosRepository.findByNombreContainingAndEquipamientoContaining(nombre, equipamiento));

	}

	@GetMapping("/ejercicios/buscarPorNombreYEquipamientoYMusculo")
	public ResponseEntity<Iterable<EjercicioORM>> findByNombreAndEquipamientoAndMusculo(
			@RequestParam(required = false) String nombre, @RequestParam(required = false) String equipamiento,
			@RequestParam(required = false) String musculo) {
		return ResponseEntity.ok(ejerciciosRepository
				.findByNombreContainingAndEquipamientoContainingAndMusculoContaining(nombre, equipamiento, musculo));

	}
}
