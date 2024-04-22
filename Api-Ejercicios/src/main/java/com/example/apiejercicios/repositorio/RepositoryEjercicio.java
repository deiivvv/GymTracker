package com.example.apiejercicios.repositorio;

import java.util.List;

import com.example.apiejercicios.orm.EjercicioORM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RepositoryEjercicio extends CrudRepository<EjercicioORM,Long> {

  List<EjercicioORM> findByNombreContaining(@Param("nombre") String nombre);
  List<EjercicioORM> findByMusculoContaining(@Param("musculo") String musculo);
  List<EjercicioORM> findByEquipamientoContaining(@Param("equipamiento") String equipamiento);




}