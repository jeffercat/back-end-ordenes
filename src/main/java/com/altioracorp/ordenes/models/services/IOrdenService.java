package com.altioracorp.ordenes.models.services;

import java.util.List;

import com.altioracorp.ordenes.models.entites.Orden;

public interface IOrdenService {
	public List<Orden> findAll();
	
	public Orden findById(Long id);
	
	public Orden save(Orden orden);
	
	public void delete(Long id);
}
