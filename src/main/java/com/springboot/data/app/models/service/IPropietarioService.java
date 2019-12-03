package com.springboot.data.app.models.service;

import java.util.List;

import com.springboot.data.app.models.entity.Propietario;

public interface IPropietarioService {
	
	public List<Propietario> findAll();
	
	public void save(Propietario propietario);

	public Propietario findOne(Long id);

	public void eliminar(Long id);
}
