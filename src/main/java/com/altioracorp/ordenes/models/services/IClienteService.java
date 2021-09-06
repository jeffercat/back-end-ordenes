package com.altioracorp.ordenes.models.services;

import java.util.List;

import com.altioracorp.ordenes.models.entites.Cliente;


public interface IClienteService {
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
}
