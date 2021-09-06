package com.altioracorp.ordenes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.altioracorp.ordenes.models.entites.Articulo;


public interface IArticuloDao extends CrudRepository<Articulo, Long>{

}
