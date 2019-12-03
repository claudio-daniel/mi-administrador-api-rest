package com.springboot.data.app.models.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.data.app.models.dao.ExpensaRepository;
import com.springboot.data.app.models.dao.IDepartamentoDao;
import com.springboot.data.app.models.dao.IServicioDao;
import com.springboot.data.app.models.dao.MantenimientoRepository;
import com.springboot.data.app.models.entity.Departamento;
import com.springboot.data.app.models.entity.Expensa;
import com.springboot.data.app.models.entity.Mantenimiento;
import com.springboot.data.app.models.entity.Servicio;
import com.springboot.data.app.models.service.IDepartamentoService;

@Service("departamentoService")
public class DepartamentoServiceImp implements IDepartamentoService{

	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Autowired
	@Qualifier("servicioRepository")
	private IServicioDao servicioRepository;
	
	@Autowired
	@Qualifier("mantenimientoRepository")
	private MantenimientoRepository mantenimientoRepository;
	
	@Autowired
	@Qualifier("expensaRepository")
	private ExpensaRepository expensaRepository; 

	@Transactional(readOnly = true)
	@Override
	public List<Departamento> findAll() {
		
		List<Departamento> list = departamentoDao.findAll() ;
		
		return list;
	}

	@Override
	public void save(Departamento departamento) {
		departamentoDao.save(departamento);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Departamento findOne(Long id) {
		return departamentoDao.findById(id);
	}

	@Override
	public void eliminar(Long id) {
		departamentoDao.deleteById(id);		
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
		return departamentoDao.findByEdificioId(edificioId);
	}	
}
