package com.parrales.project.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.parrales.project.models.entity.Cliente;


@Repository("ClienteDaoJpa")
public class ClienteDaoImpl implements IClienteDao {
	@PersistenceContext
	private EntityManager em;
      
	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		if(cliente.getId()!=null && cliente.getId()>0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}		
	}

	@Override
	public Cliente finOne(Long id) {
		// TODO Auto-generated method stub
		
		return em.find(Cliente.class, id);
	}

}