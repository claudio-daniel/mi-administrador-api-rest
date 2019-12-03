package com.springboot.data.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.data.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	@Query("SELECT p FROM Producto p "
			+ "WHERE p.nombre LIKE %?1%")
	public List<Producto> findByNombre(String term); 
	
	@Query("SELECT p FROM Producto p "
			+ "WHERE p.id = ?1")
	public Producto findOne(Long id);
}
