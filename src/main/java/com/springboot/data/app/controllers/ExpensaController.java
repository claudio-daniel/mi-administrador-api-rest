package com.springboot.data.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.springboot.data.app.models.data.view.DepartamentoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.data.app.models.data.entity.Expensa;
import com.springboot.data.app.models.data.entity.ExpensaItem;
import com.springboot.data.app.models.data.entity.Mantenimiento;
import com.springboot.data.app.models.data.entity.MantenimientoPrueba;
import com.springboot.data.app.models.repository.MantenimientoPruebaRepository;
import com.springboot.data.app.models.service.IDepartamentoService;
import com.springboot.data.app.models.service.imp.InquilinoServiceImp;
import com.springboot.data.app.util.RutasTemplates;

@RequestMapping("/expensa")
@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com/" })
@SessionAttributes("expensa")
@Controller
public class ExpensaController {

	@Autowired
	@Qualifier("departamentoService")
	private IDepartamentoService departamentoService;
	
	@Autowired
	@Qualifier("mantenimientoPruebaRepository")
	private MantenimientoPruebaRepository mantenimientoPruebaRepository;

	@Autowired
	@Qualifier("inquilinoService")
	private InquilinoServiceImp inquilinoService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/verExpensa/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Expensa expensa = departamentoService.findExpensaById(id);

		if (expensa == null) {
			flash.addFlashAttribute("error", "la expensa no se encuentra registrada");

			return "redirect:/"+ RutasTemplates.DEPARTAMENTO_LISTAR;
		}

		model.addAttribute("expensa", expensa);
		model.addAttribute("titulo", "expensa: " + expensa.getDescripcion());

		return  RutasTemplates.EXPENSA_VER;
	}

	@GetMapping("/formExpensa/{departamentoId}")
	public String crear(@PathVariable(value = "departamentoId") Long departamentoId, Map<String, Object> model,
			RedirectAttributes flash) {

		DepartamentoView departamento = departamentoService.findOne(departamentoId);

		if (departamento == null) {
			flash.addFlashAttribute("El departamento no se encuentra registrado");
			return "redirect:/" +  RutasTemplates.DEPARTAMENTO_LISTAR;
		}

		Expensa expensa = new Expensa();
		//expensa.setDepartamento(departamento);

		model.put("expensa", expensa);
		model.put("titulo", "Crear Expensa");

		return  RutasTemplates.EXPENSA_FORM;
	}

//	@GetMapping(value = "/cargar-mantenimientos/{term}", produces = { "application/json" })
//	public @ResponseBody List<Mantenimiento> cargarMantenimiento(@PathVariable String term){
//		List<Mantenimiento> mant = departamentoService.findMantenimientoByNombre(term);
//		
//		return  mant;
//	}
	@GetMapping(value = "/cargar-mantenimientos/{term}", produces = { "application/json" })
	public @ResponseBody List<MantenimientoPrueba> cargarMantenimiento(@PathVariable String term){
		List<MantenimientoPrueba> mant = mantenimientoPruebaRepository.findByNombre(term);
		
		return  mant;
	}

	// Metodo encargado de guardar la factura junto con sus items y lineas
	// Los parametros se obtienen desde el formulario de factura
	@PostMapping("/formExpensa")
	public String guardar(@Valid Expensa expensa, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {

			model.addAttribute("titulo", "Crear Expensa");

			return  RutasTemplates.EXPENSA_FORM;
		}

		if (itemId == null || itemId.length == 0) {

			model.addAttribute("titulo", "Crear Expensa");
			model.addAttribute("error", "la expensa debe tener al menos una linea");
			return  RutasTemplates.EXPENSA_FORM;
		}

		for (int i = 0; i < itemId.length; i++) {

			Mantenimiento mantenimiento = departamentoService.findMantenimientoById(itemId[i]);

			ExpensaItem linea = new ExpensaItem();
			linea.setCantidad(cantidad[i]);
			linea.setMantenimiento(mantenimiento);

			expensa.addItemExpensa(linea);

			log.info("Id: " + itemId[i].toString() + " Cantidad: " + cantidad[i].toString());
		}

		departamentoService.saveExpensa(expensa);

		status.setComplete();

		flash.addFlashAttribute("succes", "Expensa registrada con exito!");

		return "redirect:/" +  RutasTemplates.INQUILINO_VER + "/"+ expensa.getDepartamento().getId();
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long idExpensa, RedirectAttributes flash) {

		Expensa expensa = departamentoService.findExpensaById(idExpensa);

		if (expensa != null) {
			departamentoService.deleteExpensaById(idExpensa);
			flash.addFlashAttribute("success", "expensa eliminada con exito");
			return "redirect:/" +  RutasTemplates.DEPARTAMENTO_VER + expensa.getDepartamento().getId();
		}

		flash.addFlashAttribute("error", "la expensa no se encuentra registrada, y no se puede eliminar");
		return "redirect:/" +  RutasTemplates.DEPARTAMENTO_LISTAR;
	}
}
