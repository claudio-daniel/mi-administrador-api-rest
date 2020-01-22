package com.springboot.data.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.springboot.data.app.models.data.view.DepartamentoView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.service.IDepartamentoService;
import com.springboot.data.app.util.RutasTemplates;

@RestController
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
	
	//@Secured("ROLE_ADMIN")//tambien se puede usar la anotacion @PreAuthorize(hasRole('ROLE_ADMIN'))
	@RequestMapping(value = "/listarDepartamentos/edificio/{edificioId}")
	public String listarDepartamentosEdificio(Model model, @PathVariable(value = "edificioId") Long edificioId) {
		List<Departamento> listDepartamentos = departamentoService.findByEdificioId(edificioId) != null ? departamentoService.findByEdificioId(edificioId) : new ArrayList<Departamento>(); 	
		
		model.addAttribute("titulo", "Listado de Departamentos");
		model.addAttribute("departamentos", listDepartamentos);
		return RutasTemplates.DEPARTAMENTO_LISTAR;
	}
	
	//@Secured("ROLE_USER")
	@RequestMapping(value = "/verDepartamento/{id}")
	public String verDepartamento(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Departamento departamento = null;
		if (id > 0) {
			departamento = departamentoService.findOne(id);
		} else {
			return "redirect:/"+ RutasTemplates.DEPARTAMENTO_LISTAR;
		}
		model.put("departamento", departamento);
		model.put("titulo", "Detalle de Departamento");

		return RutasTemplates.DEPARTAMENTO_VER;
	}
	
	//@Secured("ROLE_ADMIN")
	@GetMapping(value = "/formDepartamento")
	public String crear(Map<String, Object> model) {
		Departamento departamento = new Departamento();
		model.put("departamento", departamento);
		model.put("titulo", "Formulario de Departamento");

		return RutasTemplates.DEPARTAMENTO_FORM;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/formDepartamento", method = RequestMethod.POST)
	public String guardar(@Valid Departamento departamento, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return RutasTemplates.DEPARTAMENTO_FORM;
		}
		departamentoService.save(departamento);
		status.setComplete();
		return "redirect:/" + RutasTemplates.DEPARTAMENTO_LISTAR;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/formDepartamento/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Departamento departamento = null;
		if (id > 0) {
			departamento = departamentoService.findOne(id);
		} else {
			return "redirect:/" + RutasTemplates.DEPARTAMENTO_LISTAR;
		}
		model.put("departamento", departamento);
		model.put("titulo", "Editar Departamento");

		return  RutasTemplates.DEPARTAMENTO_FORM;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminarDepartamento/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		if (id > 0) {
			departamentoService.eliminar(id);
		}
		return "redirect:/" +  RutasTemplates.DEPARTAMENTO_LISTAR;
	}

	//private boolean hasRole(String role) {
		
		//SecurityContext context = SecurityContextHolder.getContext();
		
//		if(context == null) {
//			return false;
//		}
//		
//		Authentication auth = context.getAuthentication();
//		
//		if (auth == null) {
//			return false;
//		}
		
		//Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		//forma resumida no obtenemos nombre de rol
		
		//return authorities.contains(new SimpleGrantedAuthority(role));
			
//		for(GrantedAuthority authority : authorities) {
//			if(role.equals(authority.getAuthority())) {
//				logger.info("hola " + auth.getName() + " tu rol es "+ authority.getAuthority());
//				return true;
//			}
//		}
//		
//		return false;
	//}
}
