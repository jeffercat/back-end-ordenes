package com.altioracorp.ordenes.models.services;

import java.util.List;

import com.altioracorp.ordenes.dto.DetalleOrdenDto;
import com.altioracorp.ordenes.models.entites.DetalleOrden;

public interface IDetalleOrdenService {
	public List<DetalleOrden> findAll();
	
	public DetalleOrden findById(Long id);
	
	public DetalleOrden save(DetalleOrdenDto detalleOrden);
	
	public void delete(Long id);
	
	public List<DetalleOrden> findAllByIdOrden(Long idOrden);
}
