package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.data.app.models.data.entity.Propietario;
import com.springboot.data.app.models.repository.PropietarioRepository;
import com.springboot.data.app.models.service.IPropietarioService;

@Service("propietarioService")
public class PropietarioServiceImp implements IPropietarioService
{
	@Autowired
	@Qualifier("propietarioRepository")
	PropietarioRepository propietarioRepository;
	
	@Override
	public List<Propietario> findAll() {
		return propietarioRepository.findAll();
	}

	@Override
	public void save(Propietario propietario) {
		propietarioRepository.save(propietario);		
	}

	@Override
	public Propietario findOne(Long id) {
		return propietarioRepository.findOneById(id);
	}

	@Override
	public void eliminar(Long id) {
		propietarioRepository.deleteById(id);
	}
}
