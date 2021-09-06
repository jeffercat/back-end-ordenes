package com.altioracorp.ordenes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altioracorp.ordenes.models.dao.IArticuloDao;
import com.altioracorp.ordenes.models.entites.Articulo;


@Service
public class ArticuloServiceImpl implements IArticuloService {
 
	@Autowired
	private IArticuloDao articuloDao;

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {

		return (List<Articulo>) articuloDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		return articuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Articulo save(Articulo articulo) {

		return articuloDao.save(articulo);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		articuloDao.deleteById(id);

	}

}
