package com.example.apiejercicios.repositorio;

import java.util.List;

import com.example.apiejercicios.orm.EjercicioORM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RepositoryEjercicio extends CrudRepository<EjercicioORM,Long> {

  List<EjercicioORM> findByNombreContaining(
		  @Param("nombre") String nombre);
  
  List<EjercicioORM> findByMusculoContaining(
		  @Param("musculo") String musculo);
  
  List<EjercicioORM> findByEquipamientoContaining(
		  @Param("equipamiento") String equipamiento);

  List<EjercicioORM> findByMusculoContainingAndEquipamientoContaining(
          @Param("musculo") String musculo,
          @Param("equipamiento") String equipamiento);
  
  List<EjercicioORM> findByNombreContainingAndMusculoContaining(
		  @Param("nombre") String nombre, 
		  @Param("musculo") String musculo);
  
  List<EjercicioORM> findByNombreContainingAndEquipamientoContaining(
		  @Param("nombre") String nombre, 
		  @Param("equipamiento") String equipamiento);
  
  List<EjercicioORM> findByNombreContainingAndEquipamientoContainingAndMusculoContaining(
		  @Param("nombre") String nombre, 
		  @Param("equipamiento") String equipamiento, 
		  @Param("musculo") String musculo);	



}