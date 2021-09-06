package com.altioracorp.ordenes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altioracorp.ordenes.dto.DetalleOrdenDto;
import com.altioracorp.ordenes.models.dao.IDetalleOrdenDao;
import com.altioracorp.ordenes.models.dao.IOrdenDao;
import com.altioracorp.ordenes.models.entites.DetalleOrden;
import com.altioracorp.ordenes.models.entites.Orden;


@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	@Autowired
	private IDetalleOrdenDao detalleOrdenDao;

	@Autowired
	private IOrdenDao ordenDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DetalleOrden> findAll() {

		return (List<DetalleOrden>) detalleOrdenDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DetalleOrden findById(Long id) {
		return detalleOrdenDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public DetalleOrden save(DetalleOrdenDto detalleOrden) {
		Orden orden= ordenDao.findById(detalleOrden.getIdOrden()).get();
		DetalleOrden detalle= new DetalleOrden();
		detalle.setCodigo(detalleOrden.getCodigo());
		detalle.setNombre(detalleOrden.getNombre());
		detalle.setPrecioUnitario(detalleOrden.getPrecioUnitario());
		detalle.setOrden(orden);
		return detalleOrdenDao.save(detalle);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		detalleOrdenDao.deleteById(id);

	}

	@Override
	public List<DetalleOrden> findAllByIdOrden(Long idOrden) {
		return detalleOrdenDao.findAllByIdOrden(idOrden);
	}








}
