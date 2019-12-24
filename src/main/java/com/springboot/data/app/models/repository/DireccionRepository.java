package com.springboot.data.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.data.entity.Direccion;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Long>{
	
	@Query("SELECT d FROM Direccion d "
			+ "WHERE d.id = ?1")
	Direccion findOneById(Long id);
	

}
