package com.example.apiejercicios.Controller;

import com.example.apiejercicios.orm.EjercicioORM;
import com.example.apiejercicios.repositorio.RepositoryEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ControllerEjercicio {

	@Autowired
	private RepositoryEjercicio ejerciciosRepository;

	@GetMapping("/ejercicios")
	public ResponseEntity<Iterable<EjercicioORM>> getEjercicios() {
		return ResponseEntity.ok(ejerciciosRepository.findAll());
	}

	@GetMapping("/ejercicios/nombre")
	public ResponseEntity<Iterable<EjercicioORM>> getEjerciciosByNombre(@RequestParam String nombre) {
		return ResponseEntity.ok(ejerciciosRepository.findByNombreContaining(nombre));
	}

	@GetMapping("/ejercicios/musculo")
	public ResponseEntity<Iterable<EjercicioORM>> getEjerciciosByMusculo(@RequestParam String musculo) {
		return ResponseEntity.ok(ejerciciosRepository.findByMusculoContaining(musculo));
	}

	@GetMapping("/ejercicios/equipamiento")
	public ResponseEntity<Iterable<EjercicioORM>> getEjerciciosByEquipamieto(@RequestParam String equipamiento) {
		return ResponseEntity.ok(ejerciciosRepository.findByEquipamientoContaining(equipamiento));
	}

	@GetMapping("/ejercicios/{id}")
	public ResponseEntity<EjercicioORM> getEjercicio(@PathVariable(name = "id") Long id) {
		Optional<EjercicioORM> ejercicioOptional = ejerciciosRepository.findById(id);
		return ejercicioOptional.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/ejercicios")
	public ResponseEntity<EjercicioORM> createEjercicio(@RequestBody EjercicioORM producto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ejerciciosRepository.save(producto));
	}

	@PutMapping("/ejercicios/{id}")
	public ResponseEntity<EjercicioORM> updateEjercicio(@PathVariable(name = "id") Long id,
														@RequestBody EjercicioORM ejercicioORM) {
		return ResponseEntity.ok(ejerciciosRepository.save(ejercicioORM));
	}

	@DeleteMapping("/ejercicios/{id}")
	public ResponseEntity<Void> deleteEjercicio(@PathVariable(name = "id") Long id) {
		ejerciciosRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/*@PatchMapping("/ejercicios/{id}")
	public ResponseEntity<Ejercicio> patchEjercicio(@PathVariable(name = "id") Long id,
			@RequestBody Ejercicio ejercicioPatch) {
		// Encuentra el ejercicio por ID
		Ejercicio ejercicio = ejerciciosRepository.findById(id).orElse(null);

		if (ejercicio == null) {
			return ResponseEntity.notFound().build();
		}

		// Aplica los cambios parciales del ejercicio recibido al ejercicio existente
		if (ejercicioPatch.getNombre() != null) {
			ejercicio.setNombre(ejercicioPatch.getNombre());
		}
		if (ejercicioPatch.getDescripcion() != null) {
			ejercicio.setDescripcion(ejercicioPatch.getDescripcion());
		}

		// Guarda y retorna el ejercicio actualizado
		return ResponseEntity.ok(ejerciciosRepository.save(ejercicio));
	}*/
}
