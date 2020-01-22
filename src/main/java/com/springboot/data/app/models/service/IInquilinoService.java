package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.data.view.InquilinoView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.data.app.models.data.entity.Factura;
import com.springboot.data.app.models.data.entity.Inquilino;
import com.springboot.data.app.models.data.entity.Producto;

public interface IInquilinoService {

	public List<InquilinoView> findAll();
	
	public Page<Inquilino> findAll(Pageable pageable);
	
	public InquilinoView save(InquilinoView inquilino);
	
	public InquilinoView findOne(Long id);
	
	public void deleteById(Long id);

	public List<Producto> findByNombre(String term);

	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
	
	public Factura findFacturaById(Long idFactura);
	
	public void deleteFacturaById(Long idFactura);
	
}
