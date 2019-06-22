package com.parrales.project.models.dao;

import java.util.List;

import com.parrales.project.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente finOne(Long id);

}
