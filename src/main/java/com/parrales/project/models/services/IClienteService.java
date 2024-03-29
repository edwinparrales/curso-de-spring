package com.parrales.project.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.parrales.project.models.entity.Cliente;

public interface IClienteService {
	
	
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable pageable);
	public void save(Cliente cliente);
	public Cliente finOne(Long id);
	public void delete(long id);

}
