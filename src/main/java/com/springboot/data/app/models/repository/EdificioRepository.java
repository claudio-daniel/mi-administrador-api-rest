package com.springboot.data.app.models.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.data.app.models.data.entity.Edificio;

public interface EdificioRepository extends CrudRepository<Edificio,  Serializable>{

	@Query("SELECT e FROM Edificio e "
			+ "WHERE e.id=?1")
	Edificio findById(Long id);
	
	@Query ("SELECT e FROM Edificio e ")
	List<Edificio> findAll();

}
