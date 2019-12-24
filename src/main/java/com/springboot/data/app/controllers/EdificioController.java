package com.springboot.data.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.data.app.models.data.entity.Edificio;
import com.springboot.data.app.models.service.imp.EdificioService;
import com.springboot.data.app.util.RutasTemplates;

//@Secured("ROLE_ADMIN")
@RequestMapping("/edificio")
@SessionAttributes("edificio")
@Controller
public class EdificioController {
	
	@Autowired
	@Qualifier("edificioService")
	private EdificioService edificioService;
	
	@RequestMapping(value = "/listarEdificios")
	public String listar(Model model) {
		List<Edificio> listEdificios = edificioService.findAll() != null ? edificioService.findAll() : new ArrayList<Edificio>(); 	
		
		model.addAttribute("titulo", "Listado de Edificios");
		model.addAttribute("edificios", listEdificios);
		return RutasTemplates.EDIFICIO_LISTAR;
	}
	
	@RequestMapping(value = "/verEdificio/{id}")
	public String verEdificio(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Edificio edificio = null;
		if (id > 0) {
			edificio = edificioService.findOne(id);
		} else {
			return "redirect:/"+ RutasTemplates.EDIFICIO_LISTAR;
		}
		model.put("edificio", edificio);
		model.put("titulo", "Detalle de Edificio");

		return RutasTemplates.EDIFICIO_VER;
	}
	
	@GetMapping(value = "/formEdificio")
	public String crear(Map<String, Object> model) {
		Edificio edificio = new Edificio();
		model.put("edificio", edificio);
		model.put("titulo", "Formulario de Edificio");

		return RutasTemplates.EDIFICIO_FORM;
	}

	@RequestMapping(value = "/formEdificio", method = RequestMethod.POST)
	public String guardar(@Valid Edificio edificio, BindingResult result, RedirectAttributes flash, SessionStatus status, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de edificio");
			return RutasTemplates.EDIFICIO_FORM;
		}
		String mensajeFlash = edificio.getId() != null ? "Edificio editado con éxito" :"Edificio registrado con éxito" ;
		
		edificioService.save(edificio);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/" + RutasTemplates.EDIFICIO_LISTAR;
	}

	@RequestMapping(value = "/formEdificio/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Edificio edificio = null;
		if (id > 0) {
			edificio = edificioService.findOne(id);
			if (edificio == null){
				flash.addFlashAttribute("error", "El edificio no se encuentra registrado");
				return "redirect:/" +  RutasTemplates.EDIFICIO_LISTAR;
			}
		} else {
			flash.addFlashAttribute("error", "El ID no puede ser menor a cero");
			return "redirect:/" + RutasTemplates.EDIFICIO_LISTAR;
		}
		model.put("edificio", edificio);
		model.put("titulo", "Editar Edificio");

		return  RutasTemplates.EDIFICIO_FORM;
	}

	@RequestMapping(value = "/eliminarEdificio/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		if (id > 0) {
			edificioService.eliminar(id);
		}
		flash.addFlashAttribute("success", "Edificio eliminado con éxito");
		return "redirect:/" +  RutasTemplates.EDIFICIO_LISTAR;
	}
}

