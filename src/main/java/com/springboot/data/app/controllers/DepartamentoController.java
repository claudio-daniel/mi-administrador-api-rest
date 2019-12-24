package com.springboot.data.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.service.IDepartamentoService;
import com.springboot.data.app.util.RutasTemplates;

@Controller
@RequestMapping({"/", "/departamento"})
@SessionAttributes("say")
public class DepartamentoController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	@Qualifier("departamentoService")
	private IDepartamentoService departamentoService;
	
	
	@RequestMapping(value = {"/", "/listarDepartamentos"})
	public String listar(Model model, Authentication authentication, HttpServletRequest request) {
		
		// Otra forma de obtener el autenticado de forma estatica
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
		//	logger.info("El usuario "+ authentication.getName() + " ha iniciado sesión");
		}
			
//		if(hasRole("ROLE_ADMIN")) {
//			//logger.info("hola " + auth.getName() + " tienes acceso al recurso.");
//		}
//		else {
			//logger.info("hola " + auth.getName() + " NO tienes acceso al recurso.");
//		}
		
		//SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		
		//if (securityContext.isUserInRole("ADMIN")) {
		//	logger.info("forma usando SecurityContextHolderAwareRequestWrapper " + auth.getName() + " tienes acceso al recurso.");
		//}
		//else {
		//	logger.info("forma usando SecurityContextHolderAwareRequestWrapper  " + auth.getName() + " NO tienes acceso al recurso.");
		//}
//		
//		if (request.isUserInRole("ROLE_ADMIN")) {
//			logger.info("forma usando HttpServletRequest  " + auth.getName() + " tienes acceso al recurso.");
//		}
//		else {
//			logger.info("forma usando HttpServletRequest   " + auth.getName() + " NO tienes acceso al recurso.");
//		}
		
		
		List<Departamento> listDepartamentos = departamentoService.findAll() != null ? departamentoService.findAll() : new ArrayList<Departamento>(); 	
		
		model.addAttribute("titulo", "Listado de Departamentos");
		model.addAttribute("departamentos", listDepartamentos);
		return RutasTemplates.DEPARTAMENTO_LISTAR;
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
