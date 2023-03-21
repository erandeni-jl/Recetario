package com.erandeni.recetario.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.erandeni.recetario.models.Ingrediente;
import com.erandeni.recetario.models.LoginUsuario;
import com.erandeni.recetario.models.Receta;
import com.erandeni.recetario.models.Usuario;
import com.erandeni.recetario.services.IngredienteService;
import com.erandeni.recetario.services.RecetaService;
import com.erandeni.recetario.services.UsuarioService;



@Controller
public class HomeController {
	
	@Autowired
	private UsuarioService usuarioServ;
	@Autowired
	private RecetaService recetaServ;
	@Autowired
	private IngredienteService ingredienteServ;

	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("usuario",new Usuario());
		model.addAttribute("loginUsuario",new LoginUsuario());
		//model.addAttribute("usuarios",usuarioServ.getAll());
		return "index.jsp";
	}
	
	@PostMapping("/crear")
	public String crearUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,Model model, HttpSession session ) {
		
		Usuario newUsuario=usuarioServ.crearUsuario(usuario,result);
		if (newUsuario==null) {
			model.addAttribute("loginUsuario",new LoginUsuario());
			return "index.jsp"; 
		}
		else {
			session.setAttribute("usuarioId", newUsuario.getId());
			return "redirect:/recetas";
		}
	}
	
	@PostMapping("/login")
	public String loginUsuario(@Valid @ModelAttribute("loginUsuario") LoginUsuario logUsuario, BindingResult result,
			Model model, HttpSession session ) {
		
		// if usuario exists and passwords match redirect to another page
		Usuario usuario= usuarioServ.login(logUsuario, result);
		
		if(usuario != null) {
			session.setAttribute("usuarioId", usuario.getId());
			return "redirect:/recetas";
		}
		// else re-render form with errors
		else {		
			model.addAttribute("usuario", new Usuario());
			return "index.jsp";
		}
	}
	
	@GetMapping("/recetas")
	public String recetas(Model model, HttpSession session ) {
		
		if(session.getAttribute("usuarioId").equals(null)) {
			return "redirect:/";
		}		
		Long usuarioId= (Long) session.getAttribute("usuarioId");
		model.addAttribute("usuario",usuarioServ.findById(usuarioId));
		model.addAttribute("recetas",recetaServ.getAll());
		return "recetas.jsp";
	}


	
	@GetMapping("/nuevaReceta")
	public String nuevaReceta(@ModelAttribute("receta") Receta receta,Model model,HttpSession session ) {
		
		Usuario usuario = usuarioServ.findById((Long)session.getAttribute("usuarioId"));
		model.addAttribute("usuario",usuario);	
		return "nuevaReceta.jsp";
	}
	
	@PostMapping("/nuevaReceta")
	public String nuevaReceta(@Valid @ModelAttribute("receta") Receta receta, BindingResult result) {
		
		if(result.hasErrors()) {
			return "nuevaReceta.jsp";
		}	
			recetaServ.createReceta(receta);
			return  "redirect:/recetas";		
	}
	

	@GetMapping("/recetas/{id}")
	public String verReceta(@PathVariable(name="id") Long id,Model model,HttpSession session ) {
		if(session.getAttribute("usuarioId").equals(null)) {
			return "redirect:/";
		}
		Receta receta = recetaServ.getReceta(id);
		Long usuarioId= (Long) session.getAttribute("usuarioId");
		model.addAttribute("receta",receta);
		model.addAttribute("usuario", usuarioServ.findById((Long)session.getAttribute("usuarioId")));
		model.addAttribute("ingredientes",ingredienteServ.getAllByReceta(receta));
		Boolean esCreador = receta.getUsuario().getId().equals(usuarioId);
		model.addAttribute("esCreador",esCreador);
		return "verReceta.jsp";
	}
	
	@GetMapping("/recetas/{id}/ingredientes")
	public String agregarIngredientes(@PathVariable(name="id") Long id,@ModelAttribute("ingrediente") Ingrediente ingrediente,
			Model model,HttpSession session ) {
		if(session.getAttribute("usuarioId").equals(null)) {
			return "redirect:/";
		}
		Receta receta = recetaServ.getReceta(id);
		model.addAttribute("receta",receta);
		model.addAttribute("usuario", usuarioServ.findById((Long)session.getAttribute("usuarioId")));
		model.addAttribute("ingredientes",ingredienteServ.getAllByReceta(receta));
		return "agregarIngredientes.jsp";
	}
	
	@PostMapping("/recetas/ingrediente")
	public String nuevoIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,BindingResult result,Model model) {
		Receta receta =ingrediente.getReceta();
		if(result.hasErrors()) {
			model.addAttribute("receta",receta);
			model.addAttribute("ingredientes",ingredienteServ.getAllByReceta(receta));
			return "agregarIngredientes.jsp";
		}	
		ingredienteServ.createIngrediente(ingrediente);
		String url="redirect:/recetas/"+ingrediente.getReceta().getId()+"/ingredientes";
		return url;		
		//return "redirect:/recetas";
	}
	
	
	@GetMapping("/recetas/{id}/actualizar")
	public String actualizarReceta(@PathVariable(name="id") Long id, Model model,HttpSession session) {
		if(session.getAttribute("usuarioId").equals(null)) {
			return "redirect:/";
		}
		model.addAttribute("receta",recetaServ.getReceta(id));
		model.addAttribute("usuario", usuarioServ.findById((Long)session.getAttribute("usuarioId")));
		
		return "actualizarReceta.jsp";
	}
	
	@PostMapping("/recetas/{id}/actualizar")
	public String actualizarReceta(@Valid @ModelAttribute("receta") Receta receta, BindingResult result) {
		
		if(result.hasErrors()) {
			return "actualizarReceta.jsp"; 
		}
		
		recetaServ.updateReceta(receta);
		return "redirect:/recetas/"+receta.getId();
	}
	
	@GetMapping("/recetas/{id}/eliminar")
	public String deleteReceta(@PathVariable(name="id") Long id) {
		recetaServ.deleteReceta(id);
		return "redirect:/recetas";
	}
	
	@GetMapping("/salir")
	public String logout(HttpSession session ) {
		session.removeAttribute("usuarioId");
		return "redirect:/";
	}
}
