package com.altioracorp.ordenes.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.altioracorp.ordenes.models.entites.DetalleOrden;


public interface IDetalleOrdenDao extends CrudRepository<DetalleOrden, Long>{

	@Query("from DetalleOrden where orden.id =?1")
	public List<DetalleOrden> findAllByIdOrden(Long idOrden);
}
