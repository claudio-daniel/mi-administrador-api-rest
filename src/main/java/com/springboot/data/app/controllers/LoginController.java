package com.springboot.data.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com/" })
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,
			Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "ya has iniciado sesion");
			return "redirect:/";
		}

		if (error != null) {
			model.addAttribute("error", "Ha ocurrido un errror al validar el usuario. ");
		}
		
		if (logout != null) {
			model.addAttribute("success", "Has cerrado sesión con éxito");
		}
		
		return "login";
	}

}
