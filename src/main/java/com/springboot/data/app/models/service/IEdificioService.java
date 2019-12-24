package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.data.entity.Edificio;
import com.springboot.data.app.models.data.entity.Mantenimiento;

public interface IEdificioService {
	
	public List<Edificio> findAll();
	
	public void save(Edificio edificio);
	
	public Edificio findOne(Long id);
	
	public void eliminar(Long id);
	
	public List<Mantenimiento> findMantenimientoByNombre(String term);

	public void saveDepartamento(Departamento departamento);
	
	public Mantenimiento findMantenimientoById(Long id);

}
