package com.parrales.project.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.parrales.project.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente,Long> {
	
}
