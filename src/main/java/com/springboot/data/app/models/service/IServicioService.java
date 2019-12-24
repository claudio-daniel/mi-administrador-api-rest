package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.data.entity.Servicio;

public interface IServicioService {

	public List<Servicio> findAll();

	public List<Servicio> findByDepartamento(Long idDepartamento);
	
	public void save(Servicio servicio);

	public Servicio findOne(Long id);

	public void eliminar(Long id);

}
