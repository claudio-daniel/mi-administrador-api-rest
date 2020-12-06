package com.springboot.data.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.data.app.models.data.entity.Edificio;
import com.springboot.data.app.models.data.entity.Mantenimiento;
import com.springboot.data.app.models.service.IEdificioService;
import com.springboot.data.app.models.service.imp.MantenimientoServiceImp;
import com.springboot.data.app.util.RutasTemplates;

@Controller
@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com/" })
@RequestMapping("/mantenimiento")
@SessionAttributes("mantenimiento")
public class MantenimientoController {

	@Autowired
	@Qualifier("mantenimientoService")
	private MantenimientoServiceImp mantenimientoService;
	
	@Autowired
	@Qualifier("edificioService")
	IEdificioService edificioService;

	@RequestMapping(value = "/listarMantenimientos/{idEdificio}")
	public String listarMantenimiento(@PathVariable(value = "idEdificio") Long idEdificio,
			Map<String, Object> model) {
		List<Mantenimiento> mantenimientos;

		if (idEdificio > 0) {
			mantenimientos = mantenimientoService.findByEdificioId(idEdificio);
		} else {
			return "redirect:/listarMantenimientos";
		}
		model.put("mantenimientos", mantenimientos);
		model.put("idEdificio", idEdificio);
		model.put("titulo", "Mantenimientos Registrados");

		return  RutasTemplates.MANTENIMIENTO_LISTAR;
	}

	@RequestMapping(value = "/verMantenimiento/{id}")
	public String verMantenimiento(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Mantenimiento mantenimiento= null;
		if (id > 0) {
			mantenimiento = mantenimientoService.findById(id);
		} else {
			return "redirect:/" +  RutasTemplates.MANTENIMIENTO_LISTAR;
		}
		model.put("mantenimiento", mantenimiento);
		model.put("titulo", "Detalle de Mantenimiento");

		return  RutasTemplates.MANTENIMIENTO_VER;
	}

	@GetMapping("/formMantenimiento/{edificioId}")
	public String crear(@PathVariable(value = "edificioId") Long edificioId, Map<String, Object> model,
			RedirectAttributes flash) {

		Edificio edificio = edificioService.findOne(edificioId);
		if (edificio == null) {
			flash.addFlashAttribute("El edificio no se encuentra registrado");
			return "redirect:/" +  RutasTemplates.EDIFICIO_LISTAR;
		}
		Mantenimiento mantenimiento = new Mantenimiento();
		mantenimiento.setEdificio(edificio);

		model.put("mantenimiento", mantenimiento);
		model.put("titulo", "Registrar Mantenimiento");

		return  RutasTemplates.MANTENIMIENTO_FORM;
	}

	@RequestMapping(value = "/formMantenimiento", method = RequestMethod.POST)
	public String guardar(@Valid Mantenimiento mantenimiento, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return  RutasTemplates.MANTENIMIENTO_FORM;
		}
		mantenimientoService.save(mantenimiento);
		status.setComplete();
		return "redirect:/" + RutasTemplates.MANTENIMIENTO_LISTAR +"/"+ mantenimiento.getEdificio().getId().toString();
	}

	@RequestMapping(value = "/formMantenimiento/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Mantenimiento mantenimiento = null;
		Edificio edificio = null;
		if (id > 0) {
			mantenimiento = mantenimientoService.findById(id);
			edificio = edificioService.findOne(mantenimiento.getEdificio().getId());
		} else {
			return "redirect:/" +  RutasTemplates.EDIFICIO_LISTAR;//mantenimiento/listarMantenimientos";
		}
		model.put("mantenimiento", mantenimiento);
		model.put("edificio", edificio);
		model.put("titulo", "Editar Mantenimiento");

		return  RutasTemplates.MANTENIMIENTO_FORM;
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Mantenimiento mantenimiento = null;

		if (id > 0) {
			mantenimiento = mantenimientoService.findById(id);
			mantenimientoService.deleteById(id);
		}
		return "redirect:/" +  RutasTemplates.MANTENIMIENTO_LISTAR +"/"+ mantenimiento.getEdificio().getId().toString();
	}
}
