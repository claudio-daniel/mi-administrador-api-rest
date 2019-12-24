package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.data.app.models.data.entity.Mantenimiento;
import com.springboot.data.app.models.repository.MantenimientoRepository;
import com.springboot.data.app.models.service.IMantenimientoService;

@Service("mantenimientoService")
public class MantenimientoServiceImp implements IMantenimientoService{

	@Autowired
	@Qualifier("mantenimientoRepository")
	private MantenimientoRepository mantenimientoRepository;

	@Override
	public Mantenimiento findById(Long id) {
		
		return mantenimientoRepository.findOneById(id);
	}

	@Override
	public List<Mantenimiento> findAll() {
		return mantenimientoRepository.findAll();
	}

	@Override
	public List<Mantenimiento> findByEdificioId(Long edificioId) {
		return mantenimientoRepository.findByEdificioId(edificioId);
	}

	@Override
	public void save(Mantenimiento mantenimiento) {
		mantenimientoRepository.save(mantenimiento);
	}

	@Override
	public void deleteById(Long id) {
		mantenimientoRepository.deleteById(id);
	}
}
