package com.springboot.data.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.springboot.data.app.models.data.view.InquilinoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.data.app.models.data.entity.Factura;
import com.springboot.data.app.models.data.entity.Inquilino;
import com.springboot.data.app.models.data.entity.ItemFactura;
import com.springboot.data.app.models.data.entity.Producto;
import com.springboot.data.app.models.service.IInquilinoService;
import com.springboot.data.app.util.RutasTemplates;

@Controller
@CrossOrigin(origins = { "https://mi-administrador-front.herokuapp.com" })
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IInquilinoService inquilinoService;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/verFactura/{id}")
	public String ver(@PathVariable(value="id")Long id,
			Model model,
			RedirectAttributes flash) {
		
		Factura factura = inquilinoService.findFacturaById(id);
		
		if(factura==null) {
			flash.addFlashAttribute("error", "la factura no se encuentra registrada");
			
			return "redirect:/" +  RutasTemplates.INQUILINO_LISTAR;
		}
		
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "factura: " + factura.getDescripcion());
		
		return  RutasTemplates.FACTURA_VER;
	}
	
	@GetMapping("/form/{inquilinoId}")
	public String crear(@PathVariable(value = "inquilinoId") Long inquilinoId, Map<String, Object> model,
			RedirectAttributes flash) {

		InquilinoView inquilino = inquilinoService.findOne(inquilinoId);

		if (inquilino == null) {
			flash.addFlashAttribute("El inquilino no se encuentra registrado");
			return "redirect:/" +  RutasTemplates.INQUILINO_LISTAR;
		}

		Factura factura = new Factura();

		model.put("factura", factura);
		model.put("titulo", "Crear Factura");

		return  RutasTemplates.FACTURA_FORM;
	}

	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {

		List<Producto> prod = inquilinoService.findByNombre(term);
		return prod;
	}

	// Metodo encargado de guardar la factura junto con sus items y lineas
	// Los parametros se obtienen desde el formulario de factura
	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {

			model.addAttribute("titulo", "Crear Factura");

			return  RutasTemplates.FACTURA_FORM;

		}

		if (itemId == null || itemId.length == 0) {
			
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "la factura debe tener al menos una linea");
			return  RutasTemplates.FACTURA_FORM;
		}

		for (int i = 0; i < itemId.length; i++) {

			Producto producto = inquilinoService.findProductoById(itemId[i]);

			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);

			factura.addItemFactura(linea);

			log.info("Id: " + itemId[i].toString() + " Cantidad: " + cantidad[i].toString());

		}

		inquilinoService.saveFactura(factura);

		status.setComplete();

		flash.addFlashAttribute("succes", "Factura registrada con exito!");

		return "redirect:/"+  RutasTemplates.INQUILINO_VER +"/"+ factura.getInquilino().getId();
	}

	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long idFactura,
			RedirectAttributes flash) {
		
		Factura factura= inquilinoService.findFacturaById(idFactura);
		
		if(factura != null) {
			inquilinoService.deleteFacturaById(idFactura);
			flash.addFlashAttribute("success", "factura eliminada con exito");
			return "redirect:/" +  RutasTemplates.INQUILINO_VER+ factura.getInquilino().getId();
		}
		
		flash.addFlashAttribute("error", "la factura no se encuentra registrada, y no se puede eliminar");
		return "redirect:/" +  RutasTemplates.INQUILINO_LISTAR;
	}
}
