package com.springboot.data.app.controllers;

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

import com.springboot.data.app.models.entity.Direccion;
import com.springboot.data.app.models.service.IDireccionService;
import com.springboot.data.app.util.RutasTemplates;
@RequestMapping("/direccion")
@SessionAttributes("")
@Controller
public class DireccionController {

	@Autowired
	@Qualifier("direccionService")
	private IDireccionService direccionService;
	
	@RequestMapping(value = "/listarDirecciones")
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de Direcciones");
		model.addAttribute("direcciones", direccionService.finAll());
		return  RutasTemplates.DIRECCION_LISTAR;
	}
	
	@GetMapping(value="/formDireccion")
	public String crear(Map<String, Object> model){
		Direccion direccion = new Direccion();
		model.put("direccion", direccion); 
		model.put("titulo", "Formulario de Direcciones");
		
		return  RutasTemplates.DIRECCION_FORM;
	}
	
	@RequestMapping(value="/formDireccion", method=RequestMethod.POST)
	public String guardar(@Valid Direccion direccion, BindingResult result,  SessionStatus status) {	
		if (result.hasErrors()) {
			return RutasTemplates.DIRECCION_FORM;
		}
		direccionService.save(direccion);
		status.setComplete();
		return "redirect:/" + RutasTemplates.DIRECCION_LISTAR;
	}
	
	@RequestMapping(value="/formDireccion/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model){
		
		Direccion direccion = null;
		if(id>0) {
			direccion = direccionService.findOne(id);
		}else {
			return "redirect:/" + RutasTemplates.DIRECCION_LISTAR;
		}
		model.put("direccion", direccion);
		model.put("titulo", "Editar Direccion");
		
		return RutasTemplates.DIRECCION_FORM;
	}
	
	@RequestMapping(value="/eliminarDireccion/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Map<String, Object> model){
		
		if(id>0) {
			direccionService.eliminar(id);
		}
		return "redirect:/" + RutasTemplates.DIRECCION_LISTAR;
	}
}
