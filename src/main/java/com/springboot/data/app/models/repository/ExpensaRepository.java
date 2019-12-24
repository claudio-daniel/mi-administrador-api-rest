package com.springboot.data.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.data.entity.Expensa;

@Repository("expensaRepository")
public interface ExpensaRepository extends CrudRepository<Expensa, Long>{

	@Query("SELECT e FROM Expensa e "
			+ "JOIN FETCH e.departamento d "
			+ "WHERE e.id = ?1")
	Expensa findOne (Long idExpensa);
}
