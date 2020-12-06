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

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.data.entity.Servicio;
import com.springboot.data.app.models.service.IDepartamentoService;
import com.springboot.data.app.models.service.IServicioService;
import com.springboot.data.app.util.RutasTemplates;

@Controller
@RequestMapping("/servicio")
@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com" })
@SessionAttributes("servicio")
public class ServicioController {

	@Autowired
	@Qualifier("servicioService")
	IServicioService servicioService;
	
	@Autowired
	@Qualifier("departamentoService")
	IDepartamentoService departamentoService;
	
	@RequestMapping(value = "/listarServicios/{idDepartamento}")
	public String listarServicio(@PathVariable(value = "idDepartamento") Long idDepartamento, Map<String, Object> model) {
		List<Servicio> servicios;
		
		if (idDepartamento > 0) {
			servicios = servicioService.findByDepartamento(idDepartamento);
		} else {
			return "redirect:/" +  RutasTemplates.DEPARTAMENTO_LISTAR;
		}
		model.put("servicios", servicios);
		model.put("idDepartamento", idDepartamento);
		model.put("titulo", "Servicios Registrados");

		return  RutasTemplates.SERVICIO_LISTAR;
	}
	
	@RequestMapping(value = "/verServicio/{id}")
	public String verServicio(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Servicio servicio = null;
		if (id > 0) {
			servicio = servicioService.findOne(id);
		} else {
			return "redirect:/" +  RutasTemplates.SERVICIO_LISTAR;
		}
		model.put("servicio", servicio);
		model.put("titulo", "Detalle de Servicio");

		return  RutasTemplates.SERVICIO_VER;
	}
	
	@GetMapping("/formServicio/{departamentoId}")
	public String crear(@PathVariable(value="departamentoId")Long departamentoId, Map <String, Object> model, RedirectAttributes flash) 
	{
		
		//Departamento departamento = departamentoService.findOne(departamentoId);
		//if(departamento == null)
		//{
		//	flash.addFlashAttribute("El departamento no se encuentra registrado");
		//	return "redirect:/"+  RutasTemplates.DEPARTAMENTO_LISTAR;
		//}
		//Servicio servicio =  new Servicio();
		//servicio.setDepartamento(departamento);
		
		//model.put("servicio", servicio);
		//model.put("titulo", "Registrar Servicio");
		
		return  RutasTemplates.SERVICIO_FORM;
	}
	
	@RequestMapping(value = "/formServicio", method = RequestMethod.POST)
	public String guardar(@Valid Servicio servicio, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return  RutasTemplates.SERVICIO_FORM;
		}
		//DEPARTAMENTO VIENE NULL!!! 
		servicioService.save(servicio);
		status.setComplete();
		return "redirect:/" +  RutasTemplates.SERVICIO_LISTAR + "/" + servicio.getDepartamento().getId().toString();
	}
	
	@RequestMapping(value = "/formServicio/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Servicio servicio = null;
		Departamento departamento = null;
		if (id > 0) {
			servicio = servicioService.findOne(id);
			//departamento = departamentoService.findOne(servicio.getDepartamento().getId());
		} else {
			return "redirect:/" +  RutasTemplates.SERVICIO_LISTAR;
		}
		model.put("servicio", servicio);
		model.put("departamento", departamento);
		model.put("titulo", "Editar Servicio");

		return  RutasTemplates.SERVICIO_FORM;
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Servicio servicio = null;
		
		if (id > 0) {
			servicio = servicioService.findOne(id);
			servicioService.eliminar(id);
		}
		return "redirect:/" +  RutasTemplates.SERVICIO_LISTAR + "/" + servicio.getDepartamento().getId().toString();
	}
}
