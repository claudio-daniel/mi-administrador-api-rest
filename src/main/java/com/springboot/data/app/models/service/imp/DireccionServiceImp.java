package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.data.app.models.dao.IDireccionDao;
import com.springboot.data.app.models.entity.Direccion;
import com.springboot.data.app.models.service.IDireccionService;

@Service("direccionService")
public class DireccionServiceImp implements IDireccionService {

	@Autowired
	private IDireccionDao direccionDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Direccion> finAll() {
		return (List<Direccion>) direccionDao.findAll();
	}
	
	@Transactional
	@Override
	public void save(Direccion direccion) {
		direccionDao.save(direccion);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Direccion findOne(Long id) {
		
		Direccion direccion = direccionDao.findOneById(id);
		return direccion;
	}
	
	@Transactional
	@Override
	public void eliminar(Long id) {
		direccionDao.deleteById(id);
	}

}
