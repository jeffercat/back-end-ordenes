package com.altioracorp.ordenes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.altioracorp.ordenes.models.entites.Orden;


public interface IOrdenDao extends CrudRepository<Orden, Long>{

}
