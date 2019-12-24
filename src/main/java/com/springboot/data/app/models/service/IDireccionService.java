package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.data.entity.Direccion;

public interface IDireccionService {

public List<Direccion> finAll();
	
	public void save(Direccion cliente);
	
	public Direccion findOne(Long id);
	
	public void eliminar(Long id);
}
