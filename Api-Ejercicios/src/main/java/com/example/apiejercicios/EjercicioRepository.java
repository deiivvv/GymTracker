package com.example.apiejercicios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface EjercicioRepository extends CrudRepository<Ejercicio,Long> {

  List<Ejercicio> findByNombreContaining(@Param("nombre") String nombre);
  List<Ejercicio> findByMusculoContaining(@Param("musculo") String musculo);
  List<Ejercicio> findByEquipamientoContaining(@Param("equipamiento") String equipamiento);




}