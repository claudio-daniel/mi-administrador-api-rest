package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.data.app.models.dao.EdificioRepository;
import com.springboot.data.app.models.dao.MantenimientoRepository;
import com.springboot.data.app.models.entity.Departamento;
import com.springboot.data.app.models.entity.Edificio;
import com.springboot.data.app.models.entity.Mantenimiento;
import com.springboot.data.app.models.service.IEdificioService;

@Service
public class EdificioService implements IEdificioService{

	@Autowired
	@Qualifier("edificioRepository")
	private EdificioRepository edificioRepository;
	
	@Autowired
	@Qualifier("mantenimientoRepository")
	private MantenimientoRepository mantenimientoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Edificio> findAll() {
		return edificioRepository.findAll();
	}

	@Override
	public void save(Edificio edificio) {
		edificioRepository.save(edificio);
	}

	@Transactional(readOnly = true)
	@Override
	public Edificio findOne(Long id) {
		return edificioRepository.findById(id);
	}

	@Override
	public void eliminar(Long id) {
		edificioRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Mantenimiento> findMantenimientoByNombre(String term) {
		return mantenimientoRepository.findByNombre(term);
	}

	@Override
	public void saveDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public Mantenimiento findMantenimientoById(Long id) {
		return mantenimientoRepository.findOneById(id);
	}

}
