package com.erandeni.recetario.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erandeni.recetario.models.Receta;
import com.erandeni.recetario.models.Usuario;

 
@Repository
public interface RecetaRepository  extends CrudRepository<Receta,Long> {
	
	List<Receta> findAll();	
	Optional<Receta> findRecetaById(Long id);
	List<Receta> findAllByUsuario(Usuario usuario);
	
}
