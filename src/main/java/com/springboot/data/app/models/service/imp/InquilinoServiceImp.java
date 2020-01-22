package com.springboot.data.app.models.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.data.app.models.data.transformer.InquilinoTransformer;
import com.springboot.data.app.models.data.view.InquilinoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.data.app.models.data.entity.Factura;
import com.springboot.data.app.models.data.entity.Inquilino;
import com.springboot.data.app.models.data.entity.Producto;
import com.springboot.data.app.models.repository.FacturaRepository;
import com.springboot.data.app.models.repository.ProductoRepostory;
import com.springboot.data.app.models.repository.InquilinoRepository;
import com.springboot.data.app.models.service.IInquilinoService;

@Service("inquilinoService")
public class InquilinoServiceImp implements IInquilinoService {

	@Autowired
	private InquilinoRepository inquilinoRepository;

	@Autowired
	private InquilinoTransformer inquilinoTransformer;
	
	@Autowired
	private ProductoRepostory productoDao;
	
	@Autowired
	@Qualifier("facturaRepository")
	private FacturaRepository facturaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<InquilinoView> findAll() {

		List<Inquilino> inquilinos = (List<Inquilino>) inquilinoRepository.findAll();

		List<InquilinoView> inquilinoViews = inquilinos
				.stream()
				.map( inquilino -> inquilinoTransformer.convetToInquilinoView(inquilino) )
				.collect( Collectors.toList() );

		return  inquilinoViews;
	}
	
	@Transactional
	@Override
	public InquilinoView save(InquilinoView inquilinoView) {

		Inquilino inquilino = inquilinoTransformer.convetToInquilino(inquilinoView);

		return inquilinoTransformer.convetToInquilinoView(inquilinoRepository.save(inquilino));
	}

	@Transactional(readOnly = true)
	@Override
	public InquilinoView findOne(Long id) {

		return inquilinoTransformer.convetToInquilinoView(inquilinoRepository.findOneById(id));
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		inquilinoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}
	
	@Transactional
	@Override
	public void saveFactura(Factura factura) {
		facturaRepository.save(factura);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Producto findProductoById(Long id) {
		return productoDao.findOne(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Factura findFacturaById(Long idFactura) {
		return facturaRepository.findOne(idFactura);
	}

	@Transactional
	@Override
	public void deleteFacturaById(Long idFactura) {
		facturaRepository.deleteById(idFactura);
		
	}

	@Transactional
	@Override
	public Page<Inquilino> findAll(Pageable pageable) {
		return inquilinoRepository.findAll(pageable);
	}
	
}
