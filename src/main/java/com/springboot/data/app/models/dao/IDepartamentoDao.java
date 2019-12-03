package com.springboot.data.app.models.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.entity.Departamento;

@Repository//("departamentoRepository")
public interface IDepartamentoDao extends CrudRepository<Departamento, Serializable>{

	@Query("SELECT d FROM Departamento d "
//			+ "JOIN FETCH d.direccion dir "
			+ "JOIN FETCH d.estado e "
			+ "JOIN FETCH d.inquilino i "
			+ "JOIN FETCH d.propietario p "
			+ "WHERE d.id=?1")
	Departamento findById(Long id);
	
	@Query("SELECT d FROM Departamento d "
			+ " WHERE (?1 IS NULL OR ?1 = d.inquilino)")
    List<Departamento> findByInquilinoNombre(String nombre);
	
	@Query ("SELECT d FROM Departamento d ")
	List<Departamento> findAll();

	@Query("SELECT d FROM Departamento d "
			+ "WHERE d.edificio.id = ?1")
	List<Departamento> findByEdificioId(Long edificioId);
}
