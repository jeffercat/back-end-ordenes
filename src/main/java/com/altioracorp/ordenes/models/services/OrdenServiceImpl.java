package com.altioracorp.ordenes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altioracorp.ordenes.models.dao.IOrdenDao;
import com.altioracorp.ordenes.models.entites.Orden;


@Service
public class OrdenServiceImpl implements IOrdenService {

	@Autowired
	private IOrdenDao ordenDao;

	@Override
	@Transactional(readOnly = true)
	public List<Orden> findAll() {

		return (List<Orden>) ordenDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Orden findById(Long id) {
		return ordenDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Orden save(Orden orden) {

		return ordenDao.save(orden);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		ordenDao.deleteById(id);

	}

}
