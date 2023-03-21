package com.erandeni.recetario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erandeni.recetario.models.Ingrediente;
import com.erandeni.recetario.models.Receta;
import com.erandeni.recetario.repositories.IngredienteRepository;

 
@Service
public class IngredienteService  {

	@Autowired
	private IngredienteRepository ingredienteRepo;
	
	public IngredienteService(IngredienteRepository rep) {
        this.ingredienteRepo = rep;
    }
	
	//creates ingrediente
	public Ingrediente createIngrediente(Ingrediente i) {
		return ingredienteRepo.save(i);
	}
	
	
	// find ingrediente by id
	public Ingrediente getIngrediente(Long id) {
		Optional<Ingrediente> ingrediente = ingredienteRepo.findIngredienteById(id);
		if (ingrediente.isPresent()) {
			return ingrediente.get();
		}
		else
		{
			return null;
		}
	}
	

	// finds all Ingredientes by Receta
	public List<Ingrediente> getAllByReceta(Receta r){
		return ingredienteRepo.findAllByReceta(r);
	}

	// finds all Ingredientes 
	public List<Ingrediente> getAll(){
		return ingredienteRepo.findAll();
	}
	
	
	
	public Ingrediente updateIngrediente(Ingrediente n) {
		return ingredienteRepo.save(n);
	}
	
	
	public void deleteIngrediente(Long id) {
		ingredienteRepo.deleteById(id);
		
	}
	
	
	
}