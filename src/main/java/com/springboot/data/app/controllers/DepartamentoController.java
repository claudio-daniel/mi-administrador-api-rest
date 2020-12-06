package com.springboot.data.app.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.springboot.data.app.models.data.view.DepartamentoView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.service.IDepartamentoService;
import com.springboot.data.app.util.RutasTemplates;

@RestController
@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com" })
@RequestMapping({"/", "/my_administration"})
@SessionAttributes("departamento")
public class DepartamentoController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	@Qualifier("departamentoService")
	private IDepartamentoService departamentoService;

	@RequestMapping(value = {"/", "/departaments"})
	public ResponseEntity<?> listar() {

		Map<String, Object> response = new HashMap<>();

		List<DepartamentoView> departamentoViews;

		try {
			departamentoViews = departamentoService.findAll();
		}
		catch (Exception e){
			response.put("mensaje", "ha ocurrido un error al consultar el listado de departamentos");
			response.put("error", e.getMessage().concat(e.getLocalizedMessage()));

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("departamentos" , departamentoViews);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/listarDepartamentos/edificio/{edificioId}")
	public String listarDepartamentosEdificio(Model model, @PathVariable(value = "edificioId") Long edificioId) {
		List<Departamento> listDepartamentos = departamentoService.findByEdificioId(edificioId) != null ? departamentoService.findByEdificioId(edificioId) : new ArrayList<>();
		
		model.addAttribute("titulo", "Listado de Departamentos");
		model.addAttribute("departamentos", listDepartamentos);
		return RutasTemplates.DEPARTAMENTO_LISTAR;
	}
	
	@GetMapping(value = "/departaments/{id}")
	public ResponseEntity<?> verDepartamento(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = new HashMap<>();
		DepartamentoView departamentoView = null;

		if (id > 0) {
			try {
				departamentoView = departamentoService.findOne(id);
			} catch (DataAccessException e) {
				response.put("mensaje", "error al reaizar la consulta a la base de datos");
				response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		if (departamentoView == null) {
			response.put("mensaje", "El inquilino con el id " + id + " no se encuentra registrado");

			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(departamentoView, HttpStatus.OK);
	}

	@PostMapping(value = "/departaments")
	public ResponseEntity<?> crear(@Valid @RequestBody DepartamentoView departamentoView, BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			departamentoView = departamentoService.save(departamentoView);
		}
		catch(ConstraintViolationException e) {
			response.put("mensaje", "error al reaizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getLocalizedMessage()));

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "el inquilino se ha creado con éxito");
		response.put("departamento", departamentoView);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/departaments/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody DepartamentoView departamentoView,
						 @PathVariable(value = "id") Long id,
						 BindingResult result) {

		DepartamentoView departamentoActual = departamentoService.findOne(id);
		DepartamentoView departamentoEditado;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if(departamentoActual == null){
			response.put("mensaje", "Error : no es posible editar, El departamento con el id " + id + " no se encuentra registrado");

			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		try {
			departamentoActual.setNombre(departamentoView.getNombre());
			departamentoActual.setCantidadHabitaciones(departamentoView.getCantidadHabitaciones());

			departamentoEditado = departamentoService.save(departamentoActual);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "error al editar el inquilino en la base de datos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "el inquilino se ha editado con éxito");
		response.put("inquilino", departamentoEditado);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/departaments/{id}")
	public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			departamentoService.eliminar(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "error al eliminar inquilino en la base de datos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje ", "operación exitosa : el inquilino ha sido eliminado de la base de datos");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
