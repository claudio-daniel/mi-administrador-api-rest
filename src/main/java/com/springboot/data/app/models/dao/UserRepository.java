package com.springboot.data.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.data.app.models.entity.Usuario;

public interface UserRepository extends CrudRepository<Usuario,Long>{

	public Usuario findByUsername(String username);
}
