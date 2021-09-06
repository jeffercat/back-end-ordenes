package com.altioracorp.ordenes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.altioracorp.ordenes.models.entites.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
