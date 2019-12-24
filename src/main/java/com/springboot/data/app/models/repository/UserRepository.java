package com.springboot.data.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.data.app.models.data.entity.Usuario;

public interface UserRepository extends CrudRepository<Usuario,Long>{

	public Usuario findByUsername(String username);
}
