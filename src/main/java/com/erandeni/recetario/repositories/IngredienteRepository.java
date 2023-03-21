package com.erandeni.recetario.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erandeni.recetario.models.Ingrediente;
import com.erandeni.recetario.models.Receta;
 

@Repository
public interface IngredienteRepository  extends CrudRepository<Ingrediente,Long> {
	
	List<Ingrediente> findAll();	
	Optional<Ingrediente> findIngredienteById(Long id);
	List<Ingrediente> findAllByReceta(Receta receta);
	
}