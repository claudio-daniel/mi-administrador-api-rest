package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.entity.Departamento;
import com.springboot.data.app.models.entity.Expensa;
import com.springboot.data.app.models.entity.Mantenimiento;
import com.springboot.data.app.models.entity.Servicio;


public interface IDepartamentoService {

	public List<Departamento> findAll();
	
	public void save(Departamento departamento);
	
	public Departamento findOne(Long id);
	
	public void eliminar(Long id);
	
	public List<Servicio> findServicioByNombre(String term);
	
	public List<Mantenimiento> findMantenimientoByNombre(String term);

	public void saveExpensa(Expensa expensa);
	
	public Servicio findServicioById(Long id);
	
	public Mantenimiento findMantenimientoById(Long id);
	
	public Expensa findExpensaById(Long idExpensa);
	
	public void deleteExpensaById(Long idExpensa);
	
	public List<Departamento> findByEdificioId(Long edificioId);
}
