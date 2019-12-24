package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.data.entity.Edificio;
import com.springboot.data.app.models.data.entity.Expensa;
import com.springboot.data.app.models.data.entity.ExpensaItem;
import com.springboot.data.app.models.data.entity.Mantenimiento;
import com.springboot.data.app.models.data.entity.Servicio;
import com.springboot.data.app.models.repository.DepartamentoRepository;
import com.springboot.data.app.models.repository.ExpensaRepository;
import com.springboot.data.app.models.repository.MantenimientoRepository;
import com.springboot.data.app.models.repository.ServicioRepository;
import com.springboot.data.app.models.service.IDepartamentoService;

@Service("departamentoService")
public class DepartamentoServiceImp implements IDepartamentoService{

	@Autowired
	private EdificioService edificioService;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	@Qualifier("servicioRepository")
	private ServicioRepository servicioRepository;
	
	@Autowired
	@Qualifier("mantenimientoRepository")
	private MantenimientoRepository mantenimientoRepository;
	
	@Autowired
	@Qualifier("expensaRepository")
	private ExpensaRepository expensaRepository; 

	@Transactional(readOnly = true)
	@Override
	public List<Departamento> findAll() {
		
		List<Departamento> list = departamentoRepository.findAll() ;
		
		return list;
	}

	@Override
	public void save(Departamento departamento) {
		departamentoRepository.save(departamento);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Departamento findOne(Long id) {
		return departamentoRepository.findById(id);
	}

	@Override
	public void eliminar(Long id) {
		departamentoRepository.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> findServicioByNombre(String term) {
		return servicioRepository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Mantenimiento> findMantenimientoByNombre(String term) {
		return mantenimientoRepository.findByNombre(term);
	}

	@Override
	@Transactional
	public void saveExpensa(Expensa expensa) {
		expensaRepository.save(expensa);
	}
	
	@Override
	@Transactional
	public Expensa crearExpensa(Long departamentoId) {
		
		Expensa expensa = new Expensa();
		
		Edificio edificio = edificioService.findByDepartamentoId(departamentoId);;
		List<Mantenimiento> mantenimientos = edificio.getMantenimientos();
		
		List<Servicio> servicios =  servicioRepository.findByDepartamentoId(departamentoId);
		
		mantenimientos.forEach(m -> {
			expensa.addItemExpensa(new ExpensaItem(1, m.getPrecio()));
		});
		
		servicios.forEach(s -> {
			expensa.addItemExpensa(new ExpensaItem(1, s.getPrecio()));
		});
		
		System.out.println("Total : " + expensa.getTotal());
		
		return expensa;
	}	

	@Override
	@Transactional(readOnly = true)
	public Servicio findServicioById(Long id) {
		return servicioRepository.findOneById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Mantenimiento findMantenimientoById(Long id) {
		return mantenimientoRepository.findOneById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public Expensa findExpensaById(Long idExpensa) {
		return expensaRepository.findOne(idExpensa);
	}

	@Transactional
	@Override
	public void deleteExpensaById(Long idExpensa) {
		expensaRepository.deleteById(idExpensa);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Departamento> findByEdificioId(Long edificioId) {
		return departamentoRepository.findByEdificioId(edificioId);
	}

	@Transactional
	@Override
	public Edificio findEdificioById(Long departamentoId) {
		return departamentoRepository.findEdificioById(departamentoId);
	}
	
	
}
