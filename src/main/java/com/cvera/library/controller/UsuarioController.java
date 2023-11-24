package com.cvera.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cvera.library.model.entity.Usuario;
import com.cvera.library.model.service.IUsuarioService;
import com.cvera.library.utils.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/libreria")
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuariolistar")
	public String listarUsuarios(@RequestParam(value = "pag", defaultValue = "0") int pag, Model model) {

		Pageable pagina = PageRequest.of(pag, 5);
		Page<Usuario> usuarios = usuarioService.obtenerTodos(pagina);

		PageRender<Usuario> pageRender = new PageRender<Usuario>("/libreria/usuariolistar", usuarios);

		model.addAttribute("titulo", "Listado de usuarios activos");
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("pageRender", pageRender);
		return "usuario/listado_usuario";
	}

	@GetMapping("/usuarionuevo")
	public String usuarioFormNuevo(Model model) {
		model.addAttribute("titulo", "Nuevo usuario");
		model.addAttribute("accion", "Crear");
		model.addAttribute("usuario", new Usuario());
		return "usuario/nuevo_usuario";
	}

	@PostMapping("/usuarioagregar")
	public String usuarioAgregar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		String accion = (usuario.getId() == null) ? "Crear" : "Modificar";

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo usuario");
			model.addAttribute("accion", accion);
			model.addAttribute("info", "Corregir los errores del formulario !!");
			return "usuario/nuevo_usuario";
		}

		usuarioService.guardarUsuario(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", accion + " usuario, proceso exitoso !!");
		return "redirect:/libreria/usuariolistar";
	}
}
