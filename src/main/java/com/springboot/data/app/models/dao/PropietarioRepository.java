package com.springboot.data.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.entity.Propietario;

@Repository("propietarioRepository")
public interface PropietarioRepository extends CrudRepository<Propietario, Long> {
	
	@Query("SELECT p FROM Propietario p "
			+ "WHERE p.id = ?1")
	Propietario findOneById(Long id);
	
	List<Propietario> findAll();
}
