package com.altioracorp.ordenes.models.services;

import java.util.List;

import com.altioracorp.ordenes.models.entites.Articulo;

public interface IArticuloService {
	public List<Articulo> findAll();
	
	public Articulo findById(Long id);
	
	public Articulo save(Articulo articulo);
	
	public void delete(Long id);
}
