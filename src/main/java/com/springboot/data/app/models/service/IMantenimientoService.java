package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.entity.Mantenimiento;

public interface IMantenimientoService {

	public Mantenimiento findById(Long id);
	
	public List<Mantenimiento> findAll();	
	
	public List<Mantenimiento> findByEdificioId(Long departamentoId);
	
	public void save(Mantenimiento mantenimiento);
	
	public void deleteById(Long id);
	
}
