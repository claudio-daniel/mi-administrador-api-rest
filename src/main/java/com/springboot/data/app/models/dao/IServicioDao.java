package com.springboot.data.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.entity.Servicio;

@Repository("servicioRepository")
public interface IServicioDao extends CrudRepository<Servicio, Long>{

	@Query("SELECT s FROM Servicio s "
			+ "WHERE s.id = ?1")
	Servicio findOneById(Long id);
	
	@Query("SELECT s FROM Servicio s "
			+ " WHERE (?1 IS NULL OR ?1 = s.departamento.id)")
    List<Servicio> findByDepartamento(Long idDepartamento);
	
	@Query ("SELECT s FROM Servicio s ")
	List<Servicio> findAll();
	
	@Query("SELECT s FROM Servicio s "
			+ "WHERE s.nombre LIKE %?1%")
	public List<Servicio> findByNombre(String term); 
	
	@Query("SELECT s FROM Servicio s "
			+ "WHERE s.id = ?1")
	public Servicio findOne(Long id);
}
