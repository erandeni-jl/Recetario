package com.erandeni.recetario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erandeni.recetario.models.Receta;
import com.erandeni.recetario.models.Usuario;
import com.erandeni.recetario.repositories.RecetaRepository;


@Service
public class RecetaService  {

	@Autowired
	private RecetaRepository recetaRepo;
	
	public RecetaService(RecetaRepository rep) {
        this.recetaRepo = rep;
    }
	
	//creates receta
	public Receta createReceta(Receta b) {
		return recetaRepo.save(b);
	}
	
	
	// find receta by id
	public Receta getReceta(Long id) {
		Optional<Receta> receta = recetaRepo.findRecetaById(id);
		if (receta.isPresent()) {
			return receta.get();
		}
		else
		{
			return null;
		}
	}
	

	// finds all Recetas by User
	public List<Receta> getAllByUsuario(Usuario u){
		return recetaRepo.findAllByUsuario(u);
	}

	// finds all Recetas 
	public List<Receta> getAll(){
		return recetaRepo.findAll();
	}
	
	
	public Receta updateReceta(Receta n) {
		return recetaRepo.save(n);
	}
	
	
	public void deleteReceta(Long id) {
		recetaRepo.deleteById(id);
		
	}
	
	
	
}