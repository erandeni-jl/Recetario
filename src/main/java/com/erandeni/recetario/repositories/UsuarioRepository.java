package com.erandeni.recetario.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erandeni.recetario.models.Usuario;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario,Long>  {

	List<Usuario> findAll();	
	Optional<Usuario> findOneById(Long id);
	Optional<Usuario> findOneByEmail(String email);
	
}

 