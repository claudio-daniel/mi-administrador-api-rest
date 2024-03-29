package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.data.entity.Edificio;
import com.springboot.data.app.models.data.entity.Expensa;
import com.springboot.data.app.models.data.entity.Mantenimiento;
import com.springboot.data.app.models.data.entity.Servicio;
import com.springboot.data.app.models.data.view.DepartamentoView;


public interface IDepartamentoService {

	List<DepartamentoView> findAll();
	
	DepartamentoView save(DepartamentoView departamentoView);
	
	DepartamentoView findOne(Long id);
	
	void eliminar(Long id);
	
	List<Servicio> findServicioByNombre(String term);
	
	List<Mantenimiento> findMantenimientoByNombre(String term);

	void saveExpensa(Expensa expensa);
	
	Expensa crearExpensa(Long departamentoId);
	
	Servicio findServicioById(Long id);
	
	Mantenimiento findMantenimientoById(Long id);
	
	Expensa findExpensaById(Long idExpensa);
	
	void deleteExpensaById(Long idExpensa);
	
	List<Departamento> findByEdificioId(Long edificioId);

	Edificio findEdificioById(Long departamentoId);
}
