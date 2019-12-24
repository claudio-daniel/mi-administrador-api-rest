package com.springboot.data.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.data.app.models.data.entity.Inquilino;
import com.springboot.data.app.models.data.transformer.InquilinoTransformer;
import com.springboot.data.app.models.data.view.InquilinoView;
import com.springboot.data.app.models.service.IInquilinoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@SessionAttributes("/inquilino")
@RequestMapping("inquilino")
@RestController
public class InquilinoController {

	@Autowired
	@Qualifier("inquilinoService")
	private IInquilinoService inquilinoService;

	@Autowired
	private InquilinoTransformer inquilinoTransformer;
	
	@RequestMapping(value = "/listarInquilinos")
	public List<Inquilino> listar() {

		List<Inquilino> list = inquilinoService.findAll();

		return list;
	}

	@GetMapping(value = "/verInquilino/{id}")
	public ResponseEntity<?> verInquilino(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = new HashMap<>();
		InquilinoView inquilinoView = null;
		
		if (id > 0) {
			try {
				inquilinoView =inquilinoService.findOne(id) != null ? inquilinoTransformer.convetToInquilinoView(inquilinoService.findOne(id)) : null;
			}
			catch(DataAccessException e) {
				response.put("mensaje", "error al reaizar la consulta a la base de datos");
				response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
				
				return new ResponseEntity<Map<String, Object>>(response , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		if(inquilinoView == null){
			response.put("mensaje", "El inquilino con el id " + id + " no se encuentra registrado");
			
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(inquilinoView, HttpStatus.OK);
	}

	@PostMapping(value = "/form")
	public ResponseEntity<?> crear(@Valid @RequestBody InquilinoView inquilinoView, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Inquilino inquilinoNew = null;
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.BAD_REQUEST);	
		}
		try {
			inquilinoNew = inquilinoTransformer.convetToInquilino(inquilinoView);
			inquilinoNew = inquilinoService.save(inquilinoNew);
		}
		catch(ConstraintViolationException e) {
			response.put("mensaje", "error al reaizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getLocalizedMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		response.put("mensaje", "el inquilino se ha creado con éxito");
		response.put("inquilino", inquilinoTransformer.convetToInquilinoView(inquilinoNew));
		
		return new ResponseEntity<Map<String, Object>>(response , HttpStatus.CREATED ); 
	}

	@PutMapping(value = "/form/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody InquilinoView inquilinoView, BindingResult result, @PathVariable Long id) {

		Inquilino inquilinoActual = inquilinoService.findOne(id);
		Inquilino inquilinoEditado = null;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.BAD_REQUEST);	
		}
		
		if(inquilinoActual == null){
			response.put("mensaje", "Error : no es posible editar, El inquilino con el id " + id + " no se encuentra registrado");
			
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.NOT_FOUND);
		}
		
		try {
			inquilinoActual.setNombre(inquilinoView.getNombre());
			inquilinoActual.setApellido(inquilinoView.getApellido());
			inquilinoActual.setEmail(inquilinoView.getEmail());
			
			inquilinoEditado = inquilinoService.save(inquilinoEditado);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "error al editar el inquilino en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el inquilino se ha editado con éxito");
		response.put("inquilino", inquilinoTransformer.convetToInquilinoView(inquilinoEditado));
		
		return new ResponseEntity<Map<String, Object>>(response , HttpStatus.CREATED ); 
	}

	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			inquilinoService.deleteById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "error al eliminar inquilino en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje ", "operación exitosa : el inquilino ha sido eliminado de la base de datos");
		
		return new ResponseEntity<Map<String, Object>>(response , HttpStatus.OK ); 
	}
}
