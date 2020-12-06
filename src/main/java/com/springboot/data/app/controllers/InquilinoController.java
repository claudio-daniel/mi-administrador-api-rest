package com.springboot.data.app.controllers;

import com.springboot.data.app.models.data.view.InquilinoView;
import com.springboot.data.app.models.service.IInquilinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com/" })
@SessionAttributes("/inquilino")
@RequestMapping("inquilino")
@RestController
public class InquilinoController {

	@Autowired
	@Qualifier("inquilinoService")
	private IInquilinoService inquilinoService;

	@RequestMapping(value = "/listarInquilinos")
	public ResponseEntity<?> listar() {

		Map<String, Object> response = new HashMap<>();

		List<InquilinoView> inquilinosView;

		try{
			inquilinosView = inquilinoService.findAll();
		} catch (Exception e){
			response.put("mensaje", "Ha ocurrido un error al consultar la lista de inquilinos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMessage()));

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (! inquilinosView.isEmpty()){
			response.put("inquilinos" , inquilinosView);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/verInquilino/{id}")
	public ResponseEntity<?> verInquilino(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = new HashMap<>();
		InquilinoView inquilinoView = null;
		
		if (id > 0) {
			try {
				inquilinoView = inquilinoService.findOne(id);
			}
			catch(DataAccessException e) {
				response.put("mensaje", "error al reaizar la consulta a la base de datos");
				response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		if(inquilinoView == null){
			response.put("mensaje", "El inquilino con el id " + id + " no se encuentra registrado");
			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(inquilinoView, HttpStatus.OK);
	}

	@PostMapping(value = "/form")
	public ResponseEntity<?> crear(@Valid @RequestBody InquilinoView inquilinoView, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		InquilinoView inquilinoViewNew;
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			inquilinoViewNew = inquilinoService.save(inquilinoView);
		}
		catch(ConstraintViolationException e) {
			response.put("mensaje", "error al reaizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getLocalizedMessage()));
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el inquilino se ha creado con éxito");
		response.put("inquilino", inquilinoViewNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/form/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody InquilinoView inquilinoView, BindingResult result, @PathVariable Long id) {

		InquilinoView inquilinoActual = inquilinoService.findOne(id);
		InquilinoView inquilinoEditado;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(inquilinoActual == null){
			response.put("mensaje", "Error : no es posible editar, El inquilino con el id " + id + " no se encuentra registrado");
			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			inquilinoActual.setNombre(inquilinoView.getNombre());
			inquilinoActual.setApellido(inquilinoView.getApellido());
			inquilinoActual.setEmail(inquilinoView.getEmail());
			
			inquilinoEditado = inquilinoService.save(inquilinoActual);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "error al editar el inquilino en la base de datos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el inquilino se ha editado con éxito");
		response.put("inquilino", inquilinoEditado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			inquilinoService.deleteById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "error al eliminar inquilino en la base de datos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje ", "operación exitosa : el inquilino ha sido eliminado de la base de datos");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
