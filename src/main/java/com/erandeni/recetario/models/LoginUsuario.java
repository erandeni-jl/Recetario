package com.erandeni.recetario.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginUsuario {


	@NotEmpty(message="El email es requerido")
	@Email( message= "Tiene que estar en el formato de email") 
	private String email;

	@NotEmpty(message="La contraseña es requerida")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
