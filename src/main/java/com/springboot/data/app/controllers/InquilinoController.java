package com.springboot.data.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.data.app.models.entity.Inquilino;
import com.springboot.data.app.models.service.IInquilinoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@SessionAttributes("/inquilino")
@RequestMapping("inquilino")
@RestController
public class InquilinoController {

	@Autowired
	@Qualifier("inquilinoService")
	private IInquilinoService inquilinoService;

	@RequestMapping(value = "/listarInquilinos")
	public List<Inquilino> listar() {

		List<Inquilino> list = inquilinoService.findAll();

		return list;
	}

	@RequestMapping(value = "/verInquilino/{id}")
	public Inquilino verInquilino(@PathVariable(value = "id") Long id) {
		Inquilino inquilino = null;
		if (id > 0) {
			return inquilino = inquilinoService.findOne(id);
		}
		return inquilino;
	}

	@PostMapping(value = "/form")
	@ResponseStatus(HttpStatus.CREATED)
	public Inquilino crear(@RequestBody Inquilino inquilino) {

		return inquilinoService.save(inquilino);
	}

	@PutMapping(value = "/form/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Inquilino editar(@RequestBody Inquilino inquilino, @PathVariable Long id) {

		Inquilino inquilinoEditar = inquilinoService.findOne(id);

		if (inquilino != null) {
			// Actualizo los datos necesarios
			inquilinoEditar.setNombre(inquilino.getNombre());
			inquilinoEditar.setApellido(inquilino.getNombre());
			inquilinoEditar.setCreateAt(inquilino.getCreateAt());
		}
		return inquilinoService.save(inquilinoEditar);
	}

	@DeleteMapping(value = "/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable(value = "id") Long id) {

		inquilinoService.deleteById(id);
	}

//	@RequestMapping(value = "/form", method = RequestMethod.POST)
//	public String guardar(@Valid Inquilino inquilino, BindingResult result, RedirectAttributes flash,
//			SessionStatus status, Model model) {
//		if (result.hasErrors()) {
//			model.addAttribute("titulo", "Formulario de inquilino");
//			return RutasTemplates.INQUILINO_FORM;
//		}
//
//		String mensajeFlash = inquilino.getId() != null ? "Inquilino editado con éxito"
//				: "Inquilino registrado con éxito";
//
//		inquilinoService.save(inquilino);
//		status.setComplete();
//		flash.addFlashAttribute("success", mensajeFlash);
//		return "redirect:/" + RutasTemplates.INQUILINO_LISTAR;
//	}
}
