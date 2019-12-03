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

import com.springboot.data.app.models.entity.Propietario;
import com.springboot.data.app.models.service.imp.PropietarioServiceImp;
import com.springboot.data.app.util.RutasTemplates;

@SessionAttributes("/propietario")
@RequestMapping("propietario")
@Controller
public class PropietarioController {
	
	@Autowired
	@Qualifier("propietarioService")
	private PropietarioServiceImp propietarioService;
	
	@RequestMapping(value = "/listarPropietarios")
	public String listar(Model model) {

		model.addAttribute("titulo", "Listado de Propietarios");
		model.addAttribute("propietarios", propietarioService.findAll());
		return  RutasTemplates.PROPIETARIO_LISTAR;
	}
	
	@RequestMapping(value = "/verPropietario/{id}")
	public String verPropietario(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Propietario propietario = null;
		if (id > 0) {
			propietario = propietarioService.findOne(id);
		} else {
			return "redirect:/" +  RutasTemplates.PROPIETARIO_LISTAR;
		}
		model.put("propietario", propietario);
		model.put("titulo", "Detalle de Propietario");

		return  RutasTemplates.PROPIETARIO_VER;
	}
	
	@GetMapping(value = "/formPropietario")
	public String crear(Map<String, Object> model) {
		Propietario propietario = new Propietario();
		model.put("propietario", propietario);
		model.put("titulo", "Formulario de Propietario");

		return  RutasTemplates.PROPIETARIO_FORM;
	}

	@RequestMapping(value = "/formPropietario", method = RequestMethod.POST)
	public String guardar(@Valid Propietario propietario, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return RutasTemplates.PROPIETARIO_FORM;
		}
		propietarioService.save(propietario);
		status.setComplete();
		return "redirect:/"+  RutasTemplates.PROPIETARIO_LISTAR;
	}

	@RequestMapping(value = "/formPropietario/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Propietario propietario = null;
		if (id > 0) {
			propietario = propietarioService.findOne(id);
		} else {
			return "redirect:/" +  RutasTemplates.PROPIETARIO_LISTAR;
		}
		model.put("propietario", propietario);
		model.put("titulo", "Editar Propietario");

		return  RutasTemplates.PROPIETARIO_FORM;
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		if (id > 0) {
			propietarioService.eliminar(id);
		}
		return "redirect:/" +  RutasTemplates.PROPIETARIO_LISTAR;
	}

}
