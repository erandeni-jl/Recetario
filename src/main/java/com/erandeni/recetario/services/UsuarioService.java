package com.erandeni.recetario.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.erandeni.recetario.models.LoginUsuario;
import com.erandeni.recetario.models.Usuario;
import com.erandeni.recetario.repositories.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public Usuario crearUsuario(Usuario u, BindingResult r) {
//		return usuarioRepo.save(u);
		
		if(!u.getPassword().equals(u.getConfirmPassword())) {
			r.rejectValue("password","Matches","Las contraseñas deben coincidir");
		}
		
		Optional <Usuario> usuario = usuarioRepo.findOneByEmail(u.getEmail());
		if (usuario.isPresent()) {
			r.rejectValue("email","Taken","Ese email ya está siendo usado por otro usuario!");
		}
		
		if(r.hasErrors()) {
			return null;
		}
		else {
			String hashPass = BCrypt.hashpw(u.getPassword(),BCrypt.gensalt());
			u.setPassword(hashPass);
			return usuarioRepo.save(u);
		}
	}
	
	public List<Usuario> getAll(){
		return usuarioRepo.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> usuario=usuarioRepo.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
			return null;
	}
	
	public Usuario updateUsuario(Usuario u) {
		return usuarioRepo.save(u);
	}
	
	public void deleteUsuario(Long id) {
		usuarioRepo.deleteById(id);
		
	}
	
	public Usuario login(LoginUsuario l, BindingResult result) {
	 Optional<Usuario> optUsuario =	usuarioRepo.findOneByEmail(l.getEmail());
	 
	 if(! result.hasErrors()) {
		 if(optUsuario.isPresent()) {
			 if(BCrypt.checkpw(l.getPassword(),optUsuario.get().getPassword())){
				 return optUsuario.get();
			 }
		 }
			 result.rejectValue("password", "Matches","Email/Contraseña inválida");
	 }
		 return null; 
	 }
	
	
}