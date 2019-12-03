package com.springboot.data.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.entity.MantenimientoPrueba;

@Repository("mantenimientoPruebaRepository")
public interface MantenimientoPruebaRepository extends CrudRepository<MantenimientoPrueba, Long>
{
	@Query("SELECT m FROM MantenimientoPrueba m "
			+ "WHERE m.id = ?1")
	MantenimientoPrueba findOneById(Long id);
	
	List<MantenimientoPrueba> findAll();
	
	@Query("SELECT m FROM MantenimientoPrueba m "
			+ "WHERE m.nombre LIKE %?1%")
	public List<MantenimientoPrueba> findByNombre(String term); 
	
	@Query("SELECT m FROM MantenimientoPrueba m "
			+ "WHERE m.id = ?1")
	public MantenimientoPrueba findOne(Long id);
}

