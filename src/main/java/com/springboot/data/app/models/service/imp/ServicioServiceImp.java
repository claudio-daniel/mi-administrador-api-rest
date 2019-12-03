package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.data.app.models.dao.IServicioDao;
import com.springboot.data.app.models.entity.Servicio;
import com.springboot.data.app.models.service.IServicioService;

@Service("servicioService")
public class ServicioServiceImp implements IServicioService{

	@Autowired
	@Qualifier("servicioRepository")
	private IServicioDao servicioRepository;

	@Override
	public List<Servicio> findAll() {
		return servicioRepository.findAll(); 
	}
	
	@Override
	public List<Servicio> findByDepartamento(Long idDepartamento){
		return servicioRepository.findByDepartamento(idDepartamento);
	}

	@Override
	public void save(Servicio servicio) {
		servicioRepository.save(servicio);	
	}

	@Override
	public Servicio findOne(Long id) {
		return servicioRepository.findOneById(id);
	}

	@Override
	public void eliminar(Long id) {
		servicioRepository.deleteById(id);
	}

}
