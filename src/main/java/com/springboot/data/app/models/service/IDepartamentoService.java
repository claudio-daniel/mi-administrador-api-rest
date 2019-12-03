package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.entity.Departamento;
import com.springboot.data.app.models.entity.Expensa;
import com.springboot.data.app.models.entity.Mantenimiento;
import com.springboot.data.app.models.entity.Servicio;


public interface IDepartamentoService {

	List<Departamento> findAll();
	
	void save(Departamento departamento);
	
	Departamento findOne(Long id);
	
	void eliminar(Long id);
	
	List<Servicio> findServicioByNombre(String term);
	
	List<Mantenimiento> findMantenimientoByNombre(String term);

	void saveExpensa(Expensa expensa);
	
	Servicio findServicioById(Long id);
	
	Mantenimiento findMantenimientoById(Long id);
	
	Expensa findExpensaById(Long idExpensa);
	
	void deleteExpensaById(Long idExpensa);
	
	List<Departamento> findByEdificioId(Long edificioId);
}
