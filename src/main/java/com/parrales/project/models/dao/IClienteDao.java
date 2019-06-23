package com.parrales.project.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.parrales.project.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long> {
	
}
