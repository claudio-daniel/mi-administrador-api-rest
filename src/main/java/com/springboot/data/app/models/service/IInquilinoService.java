package com.springboot.data.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.data.app.models.entity.Factura;
import com.springboot.data.app.models.entity.Inquilino;
import com.springboot.data.app.models.entity.Producto;

public interface IInquilinoService {

	public List<Inquilino> findAll();
	
	public Page<Inquilino> findAll(Pageable pageable);
	
	public Inquilino save(Inquilino inquilino);
	
	public Inquilino findOne(Long id);
	
	public void deleteById(Long id);

	public List<Producto> findByNombre(String term);

	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
	
	public Factura findFacturaById(Long idFactura);
	
	public void deleteFacturaById(Long idFactura);
	
}
