package com.springboot.data.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.data.entity.Factura;

@Repository("facturaRepository")
public interface FacturaRepository extends CrudRepository<Factura, Long>{

	@Query("SELECT f FROM Factura f "
			+ "WHERE f.id = ?1")
	Factura findOne (Long idFactura);

}
