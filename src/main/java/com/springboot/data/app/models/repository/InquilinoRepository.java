package com.springboot.data.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.data.app.models.data.entity.Inquilino;

@Repository
public interface InquilinoRepository extends PagingAndSortingRepository<Inquilino, Long>{
	
	@Query("SELECT i FROM Inquilino i "
			+ "WHERE i.id = ?1")
	Inquilino findOneById(Long id);
}
