package com.springboot.data.app.models.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.data.entity.Edificio;

@Repository//("departamentoRepository")
public interface DepartamentoRepository extends CrudRepository<Departamento, Serializable>{

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
	
	@Query("SELECT d.edificio FROM Departamento d "
			+ "WHERE d.id = ?1")
	Edificio findEdificioById(Long departamentoId);
	
}
