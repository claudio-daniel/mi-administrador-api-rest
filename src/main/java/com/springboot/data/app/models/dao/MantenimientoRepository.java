package com.springboot.data.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.entity.Mantenimiento;

@Repository("mantenimientoRepository")
public interface MantenimientoRepository extends CrudRepository<Mantenimiento, Long>
{
	@Query("SELECT m FROM Mantenimiento m "
			+ "WHERE m.id = ?1")
	Mantenimiento findOneById(Long id);
	
	@Query("SELECT m FROM Mantenimiento m "
			+ " WHERE (?1 IS NULL OR ?1 = m.edificio.id)")
	List<Mantenimiento> findByEdificioId(Long edificioId);
	
	List<Mantenimiento> findAll();
	
	@Query("SELECT m FROM Mantenimiento m "
			+ "WHERE m.nombre LIKE %?1%")
	public List<Mantenimiento> findByNombre(String term); 
	
	@Query("SELECT m FROM Mantenimiento m "
			+ "WHERE m.id = ?1")
	public Mantenimiento findOne(Long id);
}
